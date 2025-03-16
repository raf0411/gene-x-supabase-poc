package kalbe.corp.genexsupabasepoc.data

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.models.Wishlist

class WishlistRepository(private val supabaseClient: SupabaseClient) {
    suspend fun getWishlists(): List<Wishlist> {
        return try {
            supabaseClient.from("wishlists").select().decodeList<Wishlist>().also {
                Log.d("SupabaseSuccess", "Fetched ${it.size} wishlists")
            }
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching wishlists", e)
            emptyList()
        }
    }
}