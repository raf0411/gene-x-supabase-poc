package kalbe.corp.genexsupabasepoc.data

import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.models.Product

class ProductService {
    suspend fun insertProduct(product: Product){
        supabaseClient.from("products").insert(product)
    }

    suspend fun deleteProduct(product: Product){
        supabaseClient.from("products").delete{
            filter {
                Product::id eq product.id
            }
        }
    }

    suspend fun upsertProduct(product: Product){
        supabaseClient.from("products").upsert(product)
    }

    suspend fun updateProduct(product: Product){
        supabaseClient.from("products").update(
            {
                set("name", product.name)
                set("price", product.price)
                set("image", product.image)
                set("capsules", product.capsules)
                set("rating", product.rating)
                set("description", product.description)
                set("category", product.category)
            }
        ){
            filter {
                Product::id eq product.id
            }
        }
    }
}