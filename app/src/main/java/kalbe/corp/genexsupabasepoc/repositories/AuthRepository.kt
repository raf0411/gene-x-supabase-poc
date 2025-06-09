package kalbe.corp.genexsupabasepoc.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.mfa.AuthenticatorAssuranceLevel
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.UserProfile
import kalbe.corp.genexsupabasepoc.utils.SecurePrefs
import kalbe.corp.genexsupabasepoc.utils.getCustomUserAgent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

data class LoginSuccessPayload(
    val eventType: String = "login_success",
    val userId: String
)

data class LoginFailedPayload(
    val eventType: String = "login_failed",
    val emailAttempted: String,
    val reason: String
)

sealed class LoginResult {
    data class Success(val isFirstLogin: Boolean) : LoginResult()
    data object MfaRequired : LoginResult()
    data class Failure(val errorMessage: String) : LoginResult()
}

class AuthRepository(
    context: Context,
) {
    val gson = Gson()
    val JSON: okhttp3.MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    val EDGE_FUNCTION_BASE_URL = "https://usmimjhxrbdofkbxwibv.supabase.co/functions/v1/log-auth-event"

    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val securePrefs = SecurePrefs(context)

    suspend fun sendOtpToPhone(phone: String): Boolean {
        try {
            val formattedPhone = if (phone.startsWith("+")) {
                phone
            } else {
                "+$phone"
            }

            Log.d("AuthRepo", "Attempting to send OTP to: $formattedPhone")

            supabaseClient.auth.signInWith(OTP) {
                this.phone = formattedPhone
            }

            Log.d("AuthRepo", "OTP Sent Successfully to $formattedPhone")
            return true

        } catch (e: Exception) {
            Log.e("AuthRepo", "Failed to send OTP to $phone: ${e.message}", e)
            return false
        }
    }

    suspend fun verifyOtp(phone: String, otp: String): Boolean {
        Log.d("AuthRepo", "Attempting to verify OTP for phone: $phone with OTP: $otp")

        val formattedPhone = phone

        try {
            supabaseClient.auth.verifyPhoneOtp(
                phone = formattedPhone,
                token = otp,
                type = OtpType.Phone.SMS
            )
            Log.d("AuthRepo", "OTP Verification successful for phone: $formattedPhone")
            return true
        } catch (e: Exception) {
            Log.e("AuthRepo", "OTP Verification failed for phone: $formattedPhone - Error: ${e.message}", e)
            return false
        }
    }

    suspend fun loginUser(email: String, password: String): LoginResult {
        val bypassMfaForDevelopment = true

        try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val (currentAal, _) = supabaseClient.auth.mfa.getAuthenticatorAssuranceLevel()

            if (currentAal == AuthenticatorAssuranceLevel.AAL1 && !bypassMfaForDevelopment) {
                val userId = supabaseClient.auth.currentUserOrNull()?.id ?: "Unknown User"
                Log.d("SecurityCheck", "MFA is required for user $userId. Current AAL is 'aal1'.")
                return LoginResult.MfaRequired
            } else {
                val currentSession = supabaseClient.auth.currentSessionOrNull()!!
                val currentUser = currentSession.user
                sendLoginSuccessEvent(currentUser?.id ?: "NONE")
                Log.d("SecurityCheck", "User ${currentUser?.id} logged in successfully (Current AAL: $currentAal).")

                checkAccessTokenDuration(currentSession)
                securePrefs.putEncrypted("access_token", currentSession.accessToken)
                securePrefs.putEncrypted("refresh_token", currentSession.refreshToken)
                securePrefs.putEncrypted("expires_at", currentSession.expiresAt.toString())

                val userProfile = supabaseClient.from("users")
                    .select { filter { eq("account_id", currentUser?.id ?: "NONE") } }
                    .decodeSingle<UserProfile>()

                return LoginResult.Success(userProfile.isFirstTimeLogin)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            val errorMessage = e.message ?: "Unknown login error"
            sendLoginFailedEvent(email, "Login failed: $errorMessage")
            Log.d("SecurityCheck", "Login failed: $errorMessage")
            return LoginResult.Failure(errorMessage)
        }
    }

    suspend fun verifyMfaChallenge(code: String): Boolean {
        try {
            val factors = supabaseClient.auth.mfa.verifiedFactors
            val totpFactor = factors.firstOrNull { it.factorType == "totp" }
                ?: throw Exception("No TOTP factor found for user.")

            val challenge = supabaseClient.auth.mfa.createChallenge(totpFactor.id)

            supabaseClient.auth.mfa.verifyChallenge(
                factorId = totpFactor.id,
                challengeId = challenge.id,
                code = code,
                saveSession = true
            )

            val newSession = supabaseClient.auth.currentSessionOrNull()
                ?: throw IllegalStateException("MFA verification succeeded but no session was established.")

            securePrefs.putEncrypted("access_token", newSession.accessToken)
            securePrefs.putEncrypted("refresh_token", newSession.refreshToken)
            securePrefs.putEncrypted("expires_at", newSession.expiresAt.toString())

            Log.d("SecurityCheck", "MFA verification successful. Session upgraded to AAL2.")
            return true

        } catch (e: Exception) {
            Log.e("SecurityCheck", "MFA verification failed: ${e.message}", e)
            return false
        }
    }

    suspend fun restoreSession(): Boolean {
        val refreshToken = securePrefs.getDecrypted("refresh_token") ?: return false

        Log.d("RefreshToken", "Before Refresh Token: $refreshToken")

        return try {
            val newSession = supabaseClient.auth.refreshSession(refreshToken)

            securePrefs.putEncrypted("access_token", newSession.accessToken)
            securePrefs.putEncrypted("refresh_token", newSession.refreshToken)
            securePrefs.putEncrypted("expires_at", newSession.expiresAt.toString())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            securePrefs.clear()
            false
        }
    }

    fun checkAccessTokenDuration(currentSession: UserSession?){
        val expiresAtInstant = currentSession?.expiresAt
        val now = Clock.System.now()
        val duration = expiresAtInstant?.minus(now)

        val timeZone: TimeZone = try {
            TimeZone.of("Asia/Jakarta")
        } catch (e: Exception) {
            Log.w("Timezone", "Could not get specific timezone, using system default.", e)
            TimeZone.currentSystemDefault()
        }

        val localNow = now.toLocalDateTime(timeZone)
        val localExpiresAt = expiresAtInstant?.toLocalDateTime(timeZone)

        Log.i("TokenDurationCheck", "New session received.")

        Log.i("TokenDurationCheck", "Current Time (${timeZone.id}): ${localNow.date} | ${localNow.time}")
        Log.i("TokenDurationCheck", "Token Expires At (${timeZone.id}): ${localExpiresAt?.date} | ${localExpiresAt?.time}")
        Log.i("TokenDurationCheck", "Calculated Duration from Now: $duration")
    }

    fun sendLoginSuccessEvent(userId: String) {
        val payload = LoginSuccessPayload(userId = userId)
        val requestBody = gson.toJson(payload).toRequestBody(JSON)

        val customUserAgent = getCustomUserAgent()

        val request = Request.Builder()
            .url(EDGE_FUNCTION_BASE_URL)
            .post(requestBody)
            .header("User-Agent", customUserAgent)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to log login success: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (it.isSuccessful) {
                        println("Login success logged successfully!")
                    } else {
                        println("Failed to log login success: ${it.code} ${it.message} - ${it.body?.string()}")
                    }
                }
            }
        })
    }

    fun sendLoginFailedEvent(email: String, reason: String) {
        val payload = LoginFailedPayload(emailAttempted = email, reason = reason)
        val requestBody = gson.toJson(payload).toRequestBody(JSON)

        val customUserAgent = getCustomUserAgent()

        val request = Request.Builder()
            .url(EDGE_FUNCTION_BASE_URL)
            .post(requestBody)
            .header("User-Agent", customUserAgent)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to log failed attempt: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (it.isSuccessful) {
                        println("Login failed attempt logged successfully!")
                    } else {
                        println("Failed to log failed attempt: ${it.code} ${it.message} - ${it.body?.string()}")
                    }
                }
            }
        })
    }

    suspend fun logout() {
        supabaseClient.auth.signOut()
        securePrefs.clear()
    }
}