package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val name: String,
    val nutrition: String,
    val calories: Int,
    val max_heart_rate: Int,
    val protein_intake: Float,
    val weight: Float,
    val height: Float,
    val createdAt: String? = null,
    val gender: String,
    val age: Int,
    val profile_image: String
)

@Serializable
data class UserProfileWithNestedProfile(
    val profiles: Profile?
)