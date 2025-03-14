package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.models.Product

@Composable
fun ProductDetailsScreen(
    productRepository: ProductRepository,
    productID: String,
){
    var product by remember {
        mutableStateOf<Product?>(null)
    }

    LaunchedEffect (key1 = true){
        try {
            product = productRepository.getProductByID(id = productID)
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching product details", e)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = product?.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = product?.name ?: "No Name",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Text(
                text = product?.description ?: "...",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
        }
    }
}