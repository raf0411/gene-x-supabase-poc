package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.models.Profile
import kalbe.corp.genexsupabasepoc.ui.components.ProfileItem
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSelectionScreen(
    navController: NavController,
) {
    val profileList: List<Profile> = listOf(
        Profile(
            id = UUID.randomUUID().toString(),
            name = "Alice Wonderland",
            nutrition = "Balanced",
            calories = 2000,
            max_heart_rate = 190,
            protein_take = 100.5f,
            weight = 65.2f,
            height = 168.0f
        ),
        Profile(
            id = UUID.randomUUID().toString(),
            name = "Bob The Builder",
            nutrition = "High Protein",
            calories = 2500,
            max_heart_rate = 185,
            protein_take = 150.0f,
            weight = 85.5f,
            height = 180.5f
        ),
        Profile(
            id = UUID.randomUUID().toString(),
            name = "Charlie Chaplin",
            nutrition = "Low Carb",
            calories = 1800,
            max_heart_rate = 195,
            protein_take = 110.8f,
            weight = 72.0f,
            height = 175.0f
        ),
        Profile(
            id = UUID.randomUUID().toString(),
            name = "Diana Prince",
            nutrition = "Vegan",
            calories = 1900,
            max_heart_rate = 188,
            protein_take = 90.0f,
            weight = 60.8f,
            height = 177.8f
        ),
        Profile(
            id = UUID.randomUUID().toString(),
            name = "Ethan Hunt",
            nutrition = "Maintenance",
            calories = 2200,
            max_heart_rate = 182,
            protein_take = 130.5f,
            weight = 78.3f,
            height = 179.0f
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile", fontWeight = FontWeight.Normal) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            Icons.Filled.ChevronLeft,
                            contentDescription = "Left Arrow",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.light_grey)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(120.dp),
                    painter = painterResource(id = R.drawable.user_profile),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text("John Doe", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("JohnDoe@gmail.com")
                Spacer(modifier = Modifier.height(8.dp))
                Text("+6200000000")
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorResource(id = R.color.grey))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("Switch User", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        items(profileList) {  profile ->
                            ProfileItem(
                                profile = profile,
                                onProfileClick = {},
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileSelectionScreenPreview() {
    val fakeNavController = rememberNavController()

    ProfileSelectionScreen(
        navController = fakeNavController,
    )
}