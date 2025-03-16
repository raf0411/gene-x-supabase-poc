package kalbe.corp.genexsupabasepoc.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.models.Product
import kalbe.corp.genexsupabasepoc.ui.components.BottomBar
import kalbe.corp.genexsupabasepoc.ui.components.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(
    productRepository: ProductRepository,
    navController: NavController,
    productID: String,
    sessionID: String,
) {
    var product by remember {
        mutableStateOf<Product?>(null)
    }

    var quantity by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        try {
            product = productRepository.getProductByID(id = productID)
        } catch (e: Exception) {
            Log.e("SupabaseError", "Error fetching product details", e)
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            NavBar(navController)
        },
        bottomBar = { BottomBar(
            productID = productID,
            sessionID = sessionID,
        ) }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "$${(product?.price).toString()}" ?: "$0.00",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "$999.99",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.width(32.dp).height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                            onClick = {
                            quantity--

                            if(quantity < 0){
                                quantity = 0
                            }
                        }) {
                            Icon(Icons.Filled.Remove, contentDescription = "Minus Icon", tint = Color.Black)
                        }

                        Spacer(Modifier.width(20.dp))

                        Text(
                            text = quantity.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                        )

                        Spacer(Modifier.width(20.dp))

                        Button(
                            modifier = Modifier.width(32.dp).height(32.dp),
                            shape = RoundedCornerShape(50.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            onClick = {quantity++}
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Plus Icon")
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Product Detail",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Category",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${product?.category}",
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Description",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = product?.description ?: "...",
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}