package kalbe.corp.genexsupabasepoc.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val user_id: String,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("is_first_time_login") val isFirstTimeLogin: Boolean = true,
    @SerialName("created_at") val createdAt: String? = null
)