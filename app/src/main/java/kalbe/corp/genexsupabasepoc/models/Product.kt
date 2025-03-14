package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val image: String,
    val capsules: Int,
    val rating: Float,
    val description: String,
    val category: String,
)