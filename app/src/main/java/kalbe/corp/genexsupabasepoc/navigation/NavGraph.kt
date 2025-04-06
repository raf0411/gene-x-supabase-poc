package kalbe.corp.genexsupabasepoc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.jan.supabase.SupabaseClient
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.data.WishlistRepository
import kalbe.corp.genexsupabasepoc.ui.screen.EmailLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneOrEmailScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductCatalogueScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductDetailsScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductListScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ResetPasswordScreen
import kalbe.corp.genexsupabasepoc.ui.screen.WishlistScreen

@Composable
fun NavGraph(
    supabaseClient: SupabaseClient,
) {
    val navController = rememberNavController()
    val productRepository = ProductRepository(supabaseClient)
    val wishlistRepository = WishlistRepository(supabaseClient)

    NavHost(navController = navController, startDestination = Routes.PhoneOrEmailScreen) {

        composable<Routes.PhoneOrEmailScreen> {
            PhoneOrEmailScreen(
                navController = navController,
            )
        }

        composable<Routes.PhoneLoginScreen> {
            PhoneLoginScreen()
        }

        composable<Routes.EmailLoginScreen> {
            EmailLoginScreen(
                authRepository = AuthRepository(),
                onLoginSuccess = { isFirstTimeLogin ->
                    if (isFirstTimeLogin) {
                        navController.navigate(Routes.ResetPasswordScreen)
                    } else {
                        navController.navigate(Routes.ProductCatalogueScreen)
                    }
                }
            )
        }

        composable<Routes.ResetPasswordScreen> {
            ResetPasswordScreen(
                supabaseClient = supabaseClient,
                onResetSuccess = {
                    navController.navigate(Routes.ProductCatalogueScreen)
                },
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

