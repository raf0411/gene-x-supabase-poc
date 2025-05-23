package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.models.Product
import kalbe.corp.genexsupabasepoc.models.Wishlist

@Composable
fun WishlistItem(
    productRepository: ProductRepository,
    wishlist: Wishlist,
    onClick: () -> Unit,
    onDelete: () -> Unit,
){

    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(wishlist.product_id) {
        product = productRepository.getProductByID(wishlist.product_id)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .width(300.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .shadow(12.dp, MaterialTheme.shapes.medium)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        AsyncImage(
            model = product?.image,
            modifier = Modifier.size(64.dp),
            contentDescription = "Product Image",
        )

        Spacer(Modifier.width(32.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product?.name?.let { if (it.length > 20) it.take(20) + "..." else it } ?: "Unknown",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Row {
                Text(
                    text = "$${product?.price.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "$999.99",
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.LineThrough,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star", Modifier.size(16.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${product?.rating}",
                    fontSize = 16.sp
                )
            }
        }

        IconButton(onClick = {onDelete()}) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red
            )
        }
    }
}