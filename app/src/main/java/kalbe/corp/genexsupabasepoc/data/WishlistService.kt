package kalbe.corp.genexsupabasepoc.data

import android.util.Log
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Wishlist

class WishlistService {
    suspend fun isProductInWishlist(productID: String, sessionID: String): Boolean {
        return try {
            val result = supabaseClient.from("wishlists").select {
                filter { Wishlist::product_id eq productID }
                filter { Wishlist::session_id eq sessionID }
            }.decodeList<Wishlist>()

            result.isNotEmpty()
        } catch (e: Exception) {
            Log.e("SupabaseWishlists", "Error adding wishlists", e)
            false
        }
    }

    suspend fun insertWishlist(wishlist: Wishlist): Boolean {
        return try {
            supabaseClient.from("wishlists").insert(wishlist)
            true
        } catch (e: Exception) {
            Log.e("SupabaseWishlists", "Error adding wishlists", e)
            false
        }
    }

    suspend fun deleteWishlist(wishlist: Wishlist): Boolean {
        return try {
            supabaseClient.from("wishlists").delete {
                filter { Wishlist::id eq wishlist.id }
                filter { Wishlist::session_id eq wishlist.session_id }
            }
            true
        } catch (e: Exception) {
            Log.e("SupabaseWishlists", "Error deleting wishlist", e)
            false
        }
    }
}