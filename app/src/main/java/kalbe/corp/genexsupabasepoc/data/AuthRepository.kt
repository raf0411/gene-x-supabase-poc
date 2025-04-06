package kalbe.corp.genexsupabasepoc.data

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from

class AuthRepository {
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
                        eq("id", userId)
                    }
                }
                .decodeSingle<UserProfile>()

            return profile.isFirstTimeLogin

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun resetPassword(newPassword: String) {
        supabaseClient.auth.updateUser {
            this.password = newPassword
        }

        // Update the flag
        val userId = supabaseClient.auth.currentUserOrNull()?.id
        supabaseClient.from("user_profiles").update(
            mapOf("is_first_time_login" to false),
            request = {
                filter {
                    eq("id", userId!!)
                }
            }
        )
    }
}