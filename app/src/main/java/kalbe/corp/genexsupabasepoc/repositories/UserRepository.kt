package kalbe.corp.genexsupabasepoc.repositories

import android.util.Log
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Account
import kalbe.corp.genexsupabasepoc.models.UserProfile

class UserRepository {
    suspend fun getCurrentUser(): UserProfile?{
        val accountId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw Exception("User not logged in")

        return try {
            supabaseClient.from("users").select(
                columns = Columns.list(
                    "id", "account_id", "name", "created_at", "is_first_time_login"
                )
            ){
                filter {
                    UserProfile::account_id eq accountId
                }
            }.decodeSingle<UserProfile>().also {
                Log.d("SupabaseSuccess", "Fetched user for current account_id: $accountId")
            }
        }catch (e: Exception){
            Log.e("SupabaseError", "Error get Current User", e)
            null
        }
    }

    suspend fun getUserAccount(): Account? {
        val accountId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw Exception("User not logged in")

        return try {
            supabaseClient.from("accounts").select(
                columns = Columns.list(
                    "id", "email", "created_at", "default_password"
                )
            ){
                filter {
                    Account::id eq accountId
                }
            }.decodeSingle<Account>().also {
                Log.d("SupabaseSuccess", "Fetched account for current account_id: $accountId")
            }
        }catch (e: Exception){
            Log.e("SupabaseError", "Error get Current User", e)
            null
        }
    }
}