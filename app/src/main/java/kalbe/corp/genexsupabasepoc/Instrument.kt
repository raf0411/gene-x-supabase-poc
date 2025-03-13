package kalbe.corp.genexsupabasepoc

import kotlinx.serialization.Serializable

@Serializable
data class Instrument(
    val id: Int,
    val name: String,
)