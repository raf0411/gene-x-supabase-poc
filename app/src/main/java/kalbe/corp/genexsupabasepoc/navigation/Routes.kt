package kalbe.corp.genexsupabasepoc.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object ProductListScreen : Routes()

    @Serializable
    data class ProductDetailsScreen(val productID: String) : Routes()
}
