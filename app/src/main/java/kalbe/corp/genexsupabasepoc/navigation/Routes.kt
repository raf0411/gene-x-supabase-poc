package kalbe.corp.genexsupabasepoc.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object LoginScreen : Routes()

    @Serializable
    data object PhoneOrEmailScreen : Routes()

    @Serializable
    data object PhoneLoginScreen : Routes()

    @Serializable
    data class OtpScreen(val phone: String) : Routes()

    @Serializable
    data class EmailLoginScreen(val email: String) : Routes()

    @Serializable
    data object ResetPasswordScreen : Routes()

    @Serializable
    data object DashboardScreen : Routes()

    @Serializable
    data object ProfileScreen : Routes()

    @Serializable
    data object ProductCatalogueScreen : Routes() 

    @Serializable
    data object ProductListScreen : Routes()

    @Serializable
    data object WishlistScreen : Routes()

    @Serializable
    data class ProductDetailsScreen(val productID: String) : Routes()

    @Serializable
    data object MfaSetupScreen : Routes()

    @Serializable
    data object MfaChallengeScreen : Routes()
}
