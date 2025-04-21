package kalbe.corp.genexsupabasepoc.data

import android.util.Log
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kalbe.corp.genexsupabasepoc.models.Profile
import kalbe.corp.genexsupabasepoc.models.UserProfilePivot
import kalbe.corp.genexsupabasepoc.models.UserProfileWithNestedProfile

class ProfileRepository {
    suspend fun getCurrentUserProfiles(userRepository: UserRepository): List<Profile> {
        val userId = userRepository.getCurrentUser()?.id ?: run {
            Log.w("SupabaseAuth", "No current user found, cannot fetch profiles.")
            return emptyList()
        }

        return try {
            val results = supabaseClient.from("user_profile").select(
                columns = Columns.list("profiles(*)")
            ) {
                filter {
                    UserProfilePivot::user_id eq userId
                }
            }.decodeList<UserProfileWithNestedProfile>().also {
                Log.d("SupabaseSuccess", "Fetched ${it.size} profiles for current user_id: $userId")
            }

            results.mapNotNull { wrapper -> wrapper.profiles }
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching profiles list", e)
            emptyList()
        }
    }

    suspend fun getCurrentUserProfile(id: String): Profile?{
        return try {
            supabaseClient.from("profiles").select() {
                filter {
                    Profile::id eq id
                }
            }.decodeSingle<Profile>()
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching Profile by ID: $id", e)
            null
        }
    }
}