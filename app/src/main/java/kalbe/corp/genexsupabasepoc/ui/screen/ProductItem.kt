package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.models.Product

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(20.dp)).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        AsyncImage(model = product.image, contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)

        Box(
            modifier = Modifier.matchParentSize().background(color = Color.Black.copy(alpha = 0.4f))
        )

        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.White,
        )
    }
}