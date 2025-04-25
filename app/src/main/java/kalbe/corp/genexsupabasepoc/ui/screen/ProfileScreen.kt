package kalbe.corp.genexsupabasepoc.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.MedicalInformation
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.data.UserRepository
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.ui.components.ProfileBanner
import kalbe.corp.genexsupabasepoc.ui.components.ProfileButtonItem
import kalbe.corp.genexsupabasepoc.ui.components.ProfileHeaderToggle
import kalbe.corp.genexsupabasepoc.ui.components.ProfileInfoItem
import kalbe.corp.genexsupabasepoc.ui.components.ProfileSelectionContent
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModel
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    userRepository: UserRepository,
    authRepository: AuthRepository,
    profileViewModelFactory: ProfileViewModelFactory,
) {
    val coroutineScope = rememberCoroutineScope()
    val context =LocalContext.current
    val viewModel: ProfileViewModel = viewModel(factory = profileViewModelFactory)

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val selectedProfile by viewModel.selectedProfile.collectAsState()
    val availableProfiles by viewModel.availableProfiles.collectAsState()

    LaunchedEffect(key1 = Unit) {
        try {
            val currentUserEmail = userRepository.getUserAccount()?.email
            email = currentUserEmail.toString()
        } catch (e: Exception) {
            Log.e("ProfileScreenError", "Failed to fetch email", e)
            errorMessage = "Failed to load profiles: ${e.message}"
        }
    }

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
                            modifier = Modifier.size(32.dp),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                authRepository.logout()
                                Toast.makeText(context, "User Logged Out", Toast.LENGTH_SHORT).show()
                                navController.navigate(Routes.LoginScreen)
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Exit",
                        )
                    }
                })
        },
    ) { paddingValues ->

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
            ) {
                ProfileSelectionContent(
                    onProfileClick = { profileId ->
                        viewModel.switchProfile(profileId)
                        showBottomSheet = false
                    },
                    onCloseClick = { showBottomSheet = false },
                    onAddAccountClick = {},
                    profiles = availableProfiles,
                    selectedProfileId = selectedProfile?.id.toString(),
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            when (selectedProfile?.name){
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
                    ProfileBanner(
                        profileImage = selectedProfile?.profile_image.toString(),
                        profileName = selectedProfile?.name ?: "No Name",
                        profileEmail = email,
                        profilePhone = "No Phone",
                        profileGender = selectedProfile?.gender ?: "Unknown",
                        profileAge = selectedProfile?.age ?: 0,
                        onSwitchAccountClick = {
                            showBottomSheet = true
                        },
                    )
                }
            }

            ProfileHeaderToggle(
                headerText = "Personal Information",
            )

            ProfileInfoItem(
                infoText = "Height (cm)",
                infoValueText = "${selectedProfile?.height}",
                icon = Icons.Outlined.Height,
            )

            ProfileInfoItem(
                infoText = "Weight (kg)",
                infoValueText = "${selectedProfile?.weight}",
                icon = Icons.Outlined.Scale,
            )

            ProfileInfoItem(
                infoText = "Calories (kkal)",
                infoValueText = "${selectedProfile?.calories}",
                icon = Icons.Outlined.Fastfood,
            )

            ProfileInfoItem(
                infoText = "Max Heart Rate (bpm)",
                infoValueText = "${selectedProfile?.max_heart_rate}",
                icon = Icons.Outlined.MonitorHeart,
            )

            ProfileInfoItem(
                infoText = "Protein Intake (g)",
                infoValueText = "${selectedProfile?.protein_intake}",
                icon = Icons.Outlined.LocalDrink,
            )

            Spacer(Modifier.height(16.dp))

            ProfileHeaderToggle(
                headerText = "Account",
            )

            ProfileButtonItem(
                text = "Profile",
                icon = Icons.Outlined.Person,
            )

            ProfileButtonItem(
                text = "Medical Profile",
                icon = Icons.Outlined.MedicalInformation,
            )

            ProfileButtonItem(
                text = "Reminder",
                icon = Icons.Outlined.Timer,
            )

            ProfileButtonItem(
                text = "Language",
                icon = Icons.Outlined.TextFields,
            )

            ProfileButtonItem(
                text = "Timezone",
                icon = Icons.Outlined.Language,
            )

            ProfileButtonItem(
                text = "Accessibility",
                icon = Icons.Outlined.Accessibility,
            )

            Spacer(Modifier.height(16.dp))

            ProfileHeaderToggle(
                headerText = "General",
            )

            ProfileButtonItem(
                text = "About AI",
                icon = Icons.Outlined.ChatBubbleOutline,
            )

            ProfileButtonItem(
                text = "About Us",
                icon = Icons.Outlined.Info,
            )

            ProfileButtonItem(
                text = "Terms of Services",
                icon = Icons.Outlined.Description,
            )
        }
    }
}