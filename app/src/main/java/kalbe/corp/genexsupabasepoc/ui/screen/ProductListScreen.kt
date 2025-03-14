package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.models.Product

@Composable
fun ProductListScreen(
    productRepository: ProductRepository,
    onProductClick: (String) -> Unit,
){
    var productList by remember {
        mutableStateOf<List<Product>>(emptyList())
    }

    LaunchedEffect (key1 = true){
        try {
            productList = productRepository.getProductList()
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching products", e)
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(productList){
                product -> ProductItem(product = product, onClick = {
            onProductClick(product.id)
        })
        }
    }
}