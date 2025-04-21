package kalbe.corp.genexsupabasepoc.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: String,
    val email: String,
    val created_at: String,
    val default_password: String,
)
