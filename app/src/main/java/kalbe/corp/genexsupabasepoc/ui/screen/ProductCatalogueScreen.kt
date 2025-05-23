package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.ui.components.NavBar
import kalbe.corp.genexsupabasepoc.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCatalogueScreen(
    productRepository: ProductRepository,
    navController: NavController,
    onProductClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            NavBar(navController)
        }
    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {
            SearchBar("", {})
            ProductListScreen(productRepository, navController, onProductClick)
        }
    }
}
