package kalbe.corp.genexsupabasepoc.data

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kalbe.corp.genexsupabasepoc.models.Product

class ProductRepository(private val supabaseClient: SupabaseClient) {
    suspend fun getProductList(): List<Product> {
        return try {
            supabaseClient.from("products").select().decodeList<Product>().also {
                Log.d("SupabaseSuccess", "Fetched ${it.size} products")
            }
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching product list", e)
            emptyList()
        }
    }

    suspend fun getProductByID(id: String): Product? {
        return try {
            supabaseClient.from("products").select(
                columns = Columns.list(
                    "id", "name", "price", "image", "capsules", "rating", "description", "category"
                )
            ) {
                filter {
                    Product::id eq id
                }
            }.decodeSingle<Product>()
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching product by ID", e)
            null
        }
    }
}
