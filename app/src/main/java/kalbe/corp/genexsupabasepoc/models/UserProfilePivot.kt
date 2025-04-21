package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProfilePivot(
    val id: String,
    val user_id: String,
    val profile_id: String,
    val created_at: String,
)
