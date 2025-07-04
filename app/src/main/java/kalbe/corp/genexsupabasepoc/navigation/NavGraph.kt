package kalbe.corp.genexsupabasepoc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.repositories.AuthRepository
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.repositories.UserRepository
import kalbe.corp.genexsupabasepoc.repositories.WishlistRepository
import kalbe.corp.genexsupabasepoc.ui.screen.DashboardScreen
import kalbe.corp.genexsupabasepoc.ui.screen.EmailLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.LoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.MfaChallengeScreen
import kalbe.corp.genexsupabasepoc.ui.screen.MfaSetupScreen
import kalbe.corp.genexsupabasepoc.ui.screen.OtpScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneLoginScreen
import kalbe.corp.genexsupabasepoc.ui.screen.PhoneOrEmailScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductCatalogueScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductDetailsScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductListScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProfileScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ResetPasswordScreen
import kalbe.corp.genexsupabasepoc.ui.screen.WishlistScreen
import kalbe.corp.genexsupabasepoc.viewModel.MfaSetupViewModel
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory

@Composable
fun NavGraph(
    profileViewModelFactory: ProfileViewModelFactory,
    productRepository: ProductRepository,
    wishlistRepository: WishlistRepository,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    startDestination: Routes
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Routes.DashboardScreen> {
            DashboardScreen(
                navController = navController,
                profileViewModelFactory = profileViewModelFactory,
            )
        }

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

        composable<Routes.MfaChallengeScreen> {
            MfaChallengeScreen(
                authRepository = authRepository,
                onVerificationSuccess = {
                    navController.navigate(Routes.DashboardScreen) {
                        popUpTo(Routes.LoginScreen) { inclusive = true }
                    }
                }
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
                        navController.navigate(Routes.DashboardScreen)
                    }
                },
                email = screen.email,
                onMfaRequired = {
                    navController.navigate(Routes.MfaChallengeScreen)
                }
            )
        }

        composable<Routes.MfaSetupScreen> {
            val parentEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry(Routes.DashboardScreen)
            }

            val mfaSetupViewModel: MfaSetupViewModel = viewModel(viewModelStoreOwner = parentEntry)

            MfaSetupScreen(
                navController = navController,
                mfaSetupViewModel = mfaSetupViewModel
            )
        }

        composable<Routes.ResetPasswordScreen> {
            ResetPasswordScreen(
                supabaseClient = supabaseClient,
                onResetSuccess = {
                    navController.navigate(Routes.DashboardScreen)
                },
            )
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(
                navController = navController,
                userRepository = userRepository,
                authRepository = authRepository,
                profileViewModelFactory = profileViewModelFactory,
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
                }
            )
        }
    }
}