package kalbe.corp.genexsupabasepoc.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object PhoneOrEmailScreen : Routes()

    @Serializable
    data object PhoneLoginScreen : Routes()

    @Serializable
    data object EmailLoginScreen : Routes()

    @Serializable
    data object ResetPasswordScreen : Routes()

    @Serializable
    data object ProductCatalogueScreen : Routes() 

    @Serializable
    data object ProductListScreen : Routes()

    @Serializable
    data object WishlistScreen : Routes()

    @Serializable
    data class ProductDetailsScreen(val productID: String) : Routes()
}
