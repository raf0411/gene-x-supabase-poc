package kalbe.corp.genexsupabasepoc.data

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

    suspend fun getCurrentUserProfiles_FORCE_API_CALL_FOR_TEST(): List<Profile> {
        Log.w("ProfileRepository_TEST", "*** USING TEST FUNCTION: Attempting API call regardless of initial user state ***")

        try {
            Log.i("ProfileRepository_TEST", "Attempting Supabase query on 'user_profile' table, selecting profiles(*), NO user filter...")
            val results = supabaseClient.from("user_profile")
                .select(columns = Columns.list("profiles(*)")) {
                }
                .decodeList<UserProfileWithNestedProfile>()

            Log.d("ProfileRepository_TEST", "TEST MODE: Supabase query finished successfully. Wrapper count: ${results.size}")
            val profiles = results.mapNotNull { it.profiles }
            Log.d("ProfileRepository_TEST", "TEST MODE: Extracted profile count: ${profiles.size}")
            return profiles

        } catch (e: Exception) {
            Log.e("ProfileRepository_TEST", "*** TEST MODE: Supabase query FAILED. ***", e)
            throw e
        }
    }
}