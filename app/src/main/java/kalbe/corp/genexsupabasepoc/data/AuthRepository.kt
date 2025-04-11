package kalbe.corp.genexsupabasepoc.data

import android.util.Log
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from

class AuthRepository {
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
            val session = supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            val userId = supabaseClient.auth.currentUserOrNull()?.id
                ?: throw Exception("User not logged in")

            val profile = supabaseClient.from("auth")
                .select {
                    filter {
                        eq("user_id", userId)
                    }
                }
                .decodeSingle<UserProfile>()

            return profile.isFirstTimeLogin

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}