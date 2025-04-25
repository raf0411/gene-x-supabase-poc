package kalbe.corp.genexsupabasepoc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModel
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    profileViewModelFactory: ProfileViewModelFactory,
) {
    val navGraphRoute = Routes.DashboardScreen

    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(navGraphRoute)
    }

    val viewModel: ProfileViewModel = viewModel(
        viewModelStoreOwner = parentEntry,
        factory = profileViewModelFactory
    )

    val selectedProfile by viewModel.selectedProfile.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dashboard Gene-X", fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                actions = {
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                        )
                    }


                    IconButton(
                        onClick = {
                            navController.navigate(Routes.ProfileScreen)
                        }) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                        )
                    }
                })
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(Icons.Outlined.Home, contentDescription = "Mark as done")
                        }

                        Text(
                            text = "Home",
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(Icons.AutoMirrored.Outlined.Chat, contentDescription = "Mark as done")
                        }

                        Text(
                            text = "AI Chat",
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(onClick = { }) {
                            Icon(Icons.AutoMirrored.Outlined.Assignment, contentDescription = "Mark as done")
                        }

                        Text(
                            text = "Lab Result",
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.green_500))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Nutrition",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        IconButton(
                            onClick = {},
                        ) {
                            Icon(Icons.Filled.Close, contentDescription = null)
                        }
                    }

                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black)
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        text = "Increased Requirement",
                        color = Color.White,
                    )

                    Spacer(Modifier.height(24.dp))

                    Text(
                        text = "${selectedProfile?.nutrition}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.green_100)
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Outlined.AdsClick,
                            contentDescription = null,
                            tint = colorResource(id = R.color.light_grey)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Suggestion for you",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.light_grey),
                        )
                    }

                    Spacer(Modifier.height(24.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = 1.5.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(12.dp),
                            )
                            .padding(12.dp),
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.dna_icon),
                            contentDescription = null
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(text = "Genomic Test", fontWeight = FontWeight.Bold)
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Daily Tracking Statistics",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp, shape = RoundedCornerShape(8.dp), clip = false
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        when (selectedProfile?.calories) {
                            null -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                         color = Color.Black,
                                         strokeWidth = 4.dp
                                    )
                                }
                            }
                            else -> {
                                Text(
                                    text = "${selectedProfile?.calories}",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = "KKal",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = null,
                                modifier = Modifier.size(8.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("Carbs", fontSize = 12.sp)
                        }

                        Spacer(Modifier.width(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = null,
                                modifier = Modifier.size(8.dp),
                                tint = colorResource(id = R.color.grey)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("Protein", fontSize = 12.sp)
                        }

                        Spacer(Modifier.width(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                Icons.Filled.Circle,
                                contentDescription = null,
                                modifier = Modifier.size(8.dp),
                                tint = colorResource(id = R.color.light_grey)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("Fat", fontSize = 12.sp)
                        }
                    }
                }

                Spacer(Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp, shape = RoundedCornerShape(8.dp), clip = false
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Filled.WbSunny,
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Morning", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Exercise", fontWeight = FontWeight.Normal, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Filled.MonitorHeart,
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("${selectedProfile?.max_heart_rate}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Max. Heart Rate", fontWeight = FontWeight.Normal, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.DirectionsRun,
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Running", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Exercise", fontWeight = FontWeight.Normal, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider(modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Filled.Fastfood,
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("${selectedProfile?.protein_intake}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Protein Intake", fontWeight = FontWeight.Normal, fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}