package kalbe.corp.genexsupabasepoc.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.gotrue.auth
import kalbe.corp.genexsupabasepoc.data.WishlistService
import kalbe.corp.genexsupabasepoc.data.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Wishlist
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    productID: String,
){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var userID by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val session = supabaseClient.auth.currentSessionOrNull()
            userID = session?.user?.id
        }
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )

        BottomAppBar(
            containerColor = Color.White
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray ),
                    onClick = {
                        /* Handle Wishlists */

                        coroutineScope.launch {
                            val alreadyExists =
                                userID?.let { WishlistService().isProductInWishlist(productID, it) }

                            if (userID == null) {
                                Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
                                return@launch
                            }

                            if(alreadyExists == true){
                                Toast.makeText(context, "Product already in Wishlist", Toast.LENGTH_LONG).show()
                            } else{
                                val newWishlist = Wishlist(product_id = productID, session_id = userID!!)
                                val success = WishlistService().insertWishlist(newWishlist)

                                if(success){
                                    Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show()
                                } else{
                                    Toast.makeText(context, "Failed to add Wishlist", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Wishlist Icon",
                        tint = Color.Black,
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray ),
                    onClick = { /* Handle Add to Cart */ }) {
                    Text("Add to Cart", color = Color.Black)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /* Handle Buy Now */ }
                ) {
                    Text("Buy Now")
                }
            }
        }
    }
}