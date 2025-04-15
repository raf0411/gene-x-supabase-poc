package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    @SerialName("id") val id: String,
    @SerialName("account_id") val account_id: String,
    @SerialName("name") val name: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("is_first_time_login") val isFirstTimeLogin: Boolean = true,
)