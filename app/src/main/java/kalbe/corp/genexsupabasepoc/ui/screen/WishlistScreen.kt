package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.jan.supabase.auth.auth
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.repositories.WishlistRepository
import kalbe.corp.genexsupabasepoc.data.WishlistService
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.models.Wishlist
import kalbe.corp.genexsupabasepoc.ui.components.NavBar
import kalbe.corp.genexsupabasepoc.ui.components.WishlistItem
import kotlinx.coroutines.launch

@Composable
fun WishlistScreen(
    productRepository: ProductRepository,
    wishlistRepository: WishlistRepository,
    navController: NavController,
    onWishlistClick: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var wishlists by remember { mutableStateOf<List<Wishlist>>(emptyList()) }
    var userID by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val session = supabaseClient.auth.currentSessionOrNull()
        userID = session?.user?.id
        Log.d("UserID", "UserID : $userID")
    }

    LaunchedEffect(userID) {
        userID?.let {
            try {
                wishlists = wishlistRepository.getWishlists(it)
            } catch (e: Exception) {
                Log.e("SupabaseError", "Error fetching wishlists", e)
            }
        }
    }

    Scaffold(
        topBar = {
            NavBar(navController)
        }) { paddingValues ->
        when {
            wishlists.isEmpty() -> {
                Text(
                    text = "Wishlist is empty!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(wishlists) { wishlist ->
                        WishlistItem(
                            productRepository = productRepository,
                            wishlist = wishlist,
                            onClick = {
                                onWishlistClick(wishlist.product_id)
                            },
                            onDelete = {
                                coroutineScope.launch {
                                    val success = WishlistService().deleteWishlist(wishlist)

                                    if (success) {
                                        Toast.makeText(context, "Product deleted from Wishlist", Toast.LENGTH_LONG).show()
                                        wishlists = userID?.let { wishlistRepository.getWishlists(it) }!!
                                    } else {
                                        Toast.makeText(context, "Product failed to delete from Wishlist", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}