package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    navController: NavController,
){
    TopAppBar(
        title = { Text("Shop", fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }) {
                Icon(Icons.Filled.ChevronLeft, contentDescription = "Left Arrow", modifier = Modifier.size(32.dp))
            }
        },
        actions = {
            IconButton(onClick = {navController.navigate(Routes.WishlistScreen)}) {
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