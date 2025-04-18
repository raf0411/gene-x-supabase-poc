package kalbe.corp.genexsupabasepoc.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.data.WishlistRepository
import kalbe.corp.genexsupabasepoc.data.supabaseClient
import kalbe.corp.genexsupabasepoc.ui.screen.EmailLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.LoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.OtpScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneOrEmailScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductCatalogueScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductDetailsScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductListScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProfileScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProfileSelectionScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ResetPasswordScreen
import kalbe.corp.genexsupabasepoc.ui.screen.WishlistScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val productRepository = ProductRepository()
    val wishlistRepository = WishlistRepository()
    val authRepository = AuthRepository()

    NavHost(navController = navController, startDestination = Routes.LoginScreen) {

        composable<Routes.LoginScreen> {
            LoginScreen(
                authRepository = authRepository,
                navController = navController,
            )
        }

        composable<Routes.PhoneOrEmailScreen> {
            PhoneOrEmailScreen(
                navController = navController,
            )
        }

        composable<Routes.PhoneLoginScreen> {
            PhoneLoginScreen(
                authRepository = authRepository,
                navController = navController,
            )
        }

        composable<Routes.OtpScreen> { backStackEntry ->
            val screen = backStackEntry.toRoute<Routes.OtpScreen>()

            OtpScreen(
                authRepository = authRepository,
                navController = navController,
                phone = screen.phone,
            )
        }

        composable<Routes.EmailLoginScreen> { backStackEntry ->
            val screen = backStackEntry.toRoute<Routes.EmailLoginScreen>()

            EmailLoginScreen(
                authRepository = authRepository,
                onLoginSuccess = { isFirstTimeLogin ->
                    if (isFirstTimeLogin) {
                        navController.navigate(Routes.ResetPasswordScreen)
                    } else {
                        navController.navigate(Routes.ProfileSelectionScreen)
                    }
                },
                email = screen.email,
            )
        }

        composable<Routes.ResetPasswordScreen> {
            ResetPasswordScreen(
                supabaseClient = supabaseClient,
                onResetSuccess = {
                    Log.d("SuccessLogin", "Login Successful!")
                    navController.navigate(Routes.ProfileSelectionScreen)
                },
            )
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(
                navController = navController,
            )
        }

        composable<Routes.ProfileSelectionScreen> {
            ProfileSelectionScreen(
                navController = navController,
            )
        }

        composable<Routes.ProductCatalogueScreen> {
            ProductCatalogueScreen(
                productRepository = productRepository,
                navController,
                onProductClick = { productId ->
                    navController.navigate(Routes.ProductDetailsScreen(productId))
                })
        }

        composable<Routes.ProductListScreen> {
            ProductListScreen(
                productRepository = productRepository,
                navController,
                onProductClick = { productId ->
                    navController.navigate(Routes.ProductDetailsScreen(productId))
                })
        }

        composable<Routes.ProductDetailsScreen> {
            val args = it.toRoute<Routes.ProductDetailsScreen>()
            ProductDetailsScreen(
                productRepository,
                navController,
                productID = args.productID,
            )
        }

        composable<Routes.WishlistScreen> {
            WishlistScreen(
                productRepository = productRepository,
                wishlistRepository = wishlistRepository,
                navController,
                onWishlistClick = { productId ->
                    navController.navigate(Routes.ProductDetailsScreen(productId))
                })
        }
    }
}

