package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.models.Product
import kalbe.corp.genexsupabasepoc.ui.components.ProductItem

@Composable
fun ProductListScreen(
    productRepository: ProductRepository,
    navController: NavController,
    onProductClick: (String) -> Unit,
) {
    var productList by remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    LaunchedEffect(key1 = true) {
        try {
            productList = productRepository.getProductList()
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching products", e)
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(productList) { product ->
            ProductItem(
                product = product,
                onClick = { onProductClick(product.id) },
            )
        }
    }
}