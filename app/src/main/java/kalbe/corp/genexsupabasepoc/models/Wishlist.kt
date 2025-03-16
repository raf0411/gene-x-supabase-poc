package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Wishlist(
    val id: String? = null,
    val product_id: String,
    val created_at: String? = null,
    val session_id: String,
)
