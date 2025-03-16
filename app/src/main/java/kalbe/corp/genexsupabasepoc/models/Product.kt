package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val image: String,
    val capsules: Int,
    val rating: Double,
    val description: String,
    val category: String,
)