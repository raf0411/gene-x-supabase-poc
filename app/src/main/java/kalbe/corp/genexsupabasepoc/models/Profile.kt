package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val name: String,
    val nutrition: String,
    val calories: Int,
    val max_heart_rate: Int,
    val protein_take: Float,
    val weight: Float,
    val height: Float,
)