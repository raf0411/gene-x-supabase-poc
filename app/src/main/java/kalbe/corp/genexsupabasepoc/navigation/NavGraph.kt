package kalbe.corp.genexsupabasepoc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.jan.supabase.SupabaseClient
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.ui.screen.ProductDetailsScreen
import kalbe.corp.genexsupabasepoc.ui.screen.ProductListScreen

@Composable
fun NavGraph(supabaseClient: SupabaseClient) {
    val navController = rememberNavController()
    val productRepository = ProductRepository(supabaseClient)

    NavHost(navController = navController, startDestination = Routes.ProductListScreen) {
        composable<Routes.ProductListScreen> {
            ProductListScreen(
                productRepository = productRepository,
                onProductClick = { productId ->
                    navController.navigate(Routes.ProductDetailsScreen(productId))
                }
            )
        }

        composable<Routes.ProductDetailsScreen> {
            val args = it.toRoute<Routes.ProductDetailsScreen>()
            ProductDetailsScreen(productRepository, productID = args.productID)
        }
    }
}
