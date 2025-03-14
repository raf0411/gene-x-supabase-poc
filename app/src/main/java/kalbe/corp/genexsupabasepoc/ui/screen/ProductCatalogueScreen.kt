package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kalbe.corp.genexsupabasepoc.data.ProductRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCatalogueScreen(
    productRepository: ProductRepository,
    onProductClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shop", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation */ }) {
                        Icon(Icons.Filled.ChevronLeft, contentDescription = "Left Arrow")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle wishlist */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Wishlist")
                    }
                    IconButton(onClick = { /* Handle Delivery */ }) {
                        Icon(Icons.Outlined.LocalShipping, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* Handle Cart */ }) {
                        Icon(Icons.Outlined.ShoppingCart, contentDescription = "Shopping Cart")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {
            SearchBar("", {})
            ProductListScreen(productRepository, onProductClick)
        }
    }
}
