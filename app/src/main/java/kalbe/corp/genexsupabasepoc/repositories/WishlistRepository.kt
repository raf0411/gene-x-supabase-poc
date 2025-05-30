package kalbe.corp.genexsupabasepoc.repositories

import android.util.Log
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Wishlist

class WishlistRepository() {
    suspend fun getWishlists(sessionID: String): List<Wishlist> {
        return try {
            val result = supabaseClient
                .from("wishlists")
                .select {
                    filter { Wishlist::session_id eq sessionID }
                }
                .decodeList<Wishlist>()

            Log.d("SupabaseSuccess", "Fetched ${result.size} wishlists")
            result
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching wishlists", e)
            emptyList()
        }
    }
}