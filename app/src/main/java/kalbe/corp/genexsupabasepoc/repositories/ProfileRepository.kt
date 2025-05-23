package kalbe.corp.genexsupabasepoc.repositories

import android.util.Log
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Profile
import kalbe.corp.genexsupabasepoc.models.UserProfileWithNestedProfile

class ProfileRepository {
    suspend fun getCurrentUserProfiles(userRepository: UserRepository): List<Profile> {
        val userId = userRepository.getCurrentUser()?.id ?: run {
            Log.w("ProfileRepository", "[Original] No current user found, cannot fetch profiles.")
            return emptyList()
        }
        Log.d("ProfileRepository", "[Original] Fetching profiles for user_id: $userId")
        try {
            val results = supabaseClient.from("user_profile")
                .select(columns = Columns.list("profiles(*)")) {
                    filter { eq("user_id", userId) }
                }
                .decodeList<UserProfileWithNestedProfile>()
            Log.d("ProfileRepository", "[Original] Fetched ${results.size} wrappers.")
            val profiles = results.mapNotNull { it.profiles }
            Log.d("ProfileRepository", "[Original] Extracted ${profiles.size} profiles.")
            return profiles
        } catch (e: Exception) {
            Log.e("ProfileRepository", "[Original] Error fetching profiles", e)
            throw e
        }
    }
}