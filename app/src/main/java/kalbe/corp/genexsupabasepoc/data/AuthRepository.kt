package kalbe.corp.genexsupabasepoc.data

import android.content.Context
import android.util.Log
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.models.UserProfile
import kalbe.corp.genexsupabasepoc.sessions.SecurePrefs

class AuthRepository(
    context: Context,
) {
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

    suspend fun loginUser(email: String, password: String): Boolean {
        try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val currentUser = supabaseClient.auth.currentUserOrNull()
            val currentSession = supabaseClient.auth.currentSessionOrNull()

            // Persisting tokens securely here
            securePrefs.putEncrypted("access_token", currentSession?.accessToken.toString())
            securePrefs.putEncrypted("refresh_token", currentSession?.refreshToken.toString())
            securePrefs.putEncrypted("expires_at", currentSession?.expiresAt.toString())

            val authId = currentUser?.id
                ?: throw Exception("User not logged in")

            // Getting user from table "users"
            val user = supabaseClient.from("users")
                .select {
                    filter {
                        eq("account_id", authId)
                    }
                }
                .decodeSingle<UserProfile>()

            return user.isFirstTimeLogin

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun restoreSession(): Boolean {
        val refreshToken = securePrefs.getDecrypted("refresh_token") ?: return false

        Log.d("RefreshToken", "Before Refresh Token: $refreshToken")

        return try {
            val newSession = supabaseClient.auth.refreshSession(refreshToken)
            Log.d("RefreshToken", "After Refresh Token: ${newSession.refreshToken}")

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

    suspend fun logout() {
        supabaseClient.auth.signOut()
        securePrefs.clear()
    }
}