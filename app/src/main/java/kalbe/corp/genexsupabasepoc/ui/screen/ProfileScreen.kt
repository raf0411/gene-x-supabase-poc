package kalbe.corp.genexsupabasepoc.ui.screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.mfa.FactorType
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.repositories.AuthRepository
import kalbe.corp.genexsupabasepoc.repositories.UserRepository
import kalbe.corp.genexsupabasepoc.ui.components.ProfileBanner
import kalbe.corp.genexsupabasepoc.ui.components.ProfileButtonItem
import kalbe.corp.genexsupabasepoc.ui.components.ProfileHeaderToggle
import kalbe.corp.genexsupabasepoc.ui.components.ProfileInfoItem
import kalbe.corp.genexsupabasepoc.ui.components.ProfileSelectionContent
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModel
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    userRepository: UserRepository,
    authRepository: AuthRepository,
    profileViewModelFactory: ProfileViewModelFactory,
) {
    val tag = "ProfileScreen"

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf<String?>(null) }

    val navGraphRoute = Routes.DashboardScreen

    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(navGraphRoute)
    }

    val viewModel: ProfileViewModel = viewModel(
        viewModelStoreOwner = parentEntry, factory = profileViewModelFactory
    )

    val selectedProfile by viewModel.selectedProfile.collectAsState()
    val availableProfiles by viewModel.availableProfiles.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(key1 = userRepository) {
        try {
            email = userRepository.getUserAccount()?.email
        } catch (e: Exception) {
            Log.e(tag, "Failed to fetch email", e)
        }
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let { msg ->
            Log.d(tag, "Showing error snackbar: $msg")
            snackbarHostState.showSnackbar(message = msg, duration = SnackbarDuration.Short)
        }
    }

    LaunchedEffect(key1 = viewModel, key2 = navController) {
        viewModel.navigateToLogin.collectLatest {
            Log.i(tag, "NavigateToLogin event received from ViewModel. Navigating to Login.")
            Toast.makeText(context, "Session expired, logging out...", Toast.LENGTH_LONG).show()

            navController.navigate(Routes.LoginScreen) {
                popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    DisposableEffect(lifecycleOwner, viewModel) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                Log.d(tag, "Lifecycle ON_RESUME: Triggering ProfileViewModel.loadInitialData()")
                viewModel.loadInitialData()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            Log.d(tag, "DisposableEffect onDispose: Removing lifecycle observer.")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile", fontWeight = FontWeight.Normal) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        } else {
                            Log.w(tag, "No previous back stack entry to pop.")
                        }
                    }) {
                        Icon(
                            Icons.Filled.ChevronLeft,
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            Log.d(tag, "Manual logout button clicked.")
                            coroutineScope.launch {
                                try {
                                    authRepository.logout()
                                    Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show()

                                    navController.navigate(Routes.LoginScreen) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }

                                } catch (e: Exception) {
                                    Log.e(tag, "Error during manual logout", e)
                                    Toast.makeText(
                                        context, "Logout failed: ${e.message}", Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                        )
                    }
                })
        },
    ) { paddingValues ->

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                ProfileSelectionContent(
                    onProfileClick = { profileId ->
                        viewModel.switchProfile(profileId)
                        showBottomSheet = false
                    },
                    onCloseClick = { showBottomSheet = false },
                    onAddAccountClick = { /* TODO: Implement Add Account */ },
                    profiles = availableProfiles,
                    selectedProfileId = selectedProfile?.id.toString(),
                )
            }
        }

        // --- Main Screen Content ---
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Button(onClick = {
                Log.d("MfaApiTest", "--- Running Raw Enroll Test ---")
                // Use the coroutineScope you already have in ProfileScreen
                coroutineScope.launch {
                    try {
                        Log.d("MfaApiTest", "Attempting to call enroll()...")

                        // We call the function directly from the client
                        val factor = supabaseClient.auth.mfa.enroll(factorType = FactorType.TOTP)

                        Log.d("MfaApiTest", "✅ SUCCESS! Enroll returned a factor with ID: ${factor.id}")
                        Toast.makeText(context, "SUCCESS: Check MfaApiTest Log!", Toast.LENGTH_SHORT).show()

                    } catch (e: Throwable) { // Catching Throwable is safer for debugging
                        Log.e("MfaApiTest", "❌ FAILURE! The enroll() call crashed.", e)
                        Toast.makeText(context, "FAILURE: Check MfaApiTest Log!", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Run Raw Enroll Test")
            }

            Spacer(Modifier.height(16.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary, strokeWidth = 4.dp
                    )
                }
            } else if (selectedProfile != null) {
                ProfileBanner(
                    profileImage = selectedProfile?.profile_image.toString(),
                    profileName = selectedProfile?.name ?: "N/A",
                    profileEmail = email ?: "...",
                    profilePhone = "N/A",
                    profileGender = selectedProfile?.gender ?: "N/A",
                    profileAge = selectedProfile?.age ?: 0,
                    onSwitchAccountClick = {
                        if (availableProfiles.size > 1) {
                            showBottomSheet = true
                        } else {
                            Toast.makeText(
                                context, "No other profiles to switch to.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                )

                // --- Sections ---
                ProfileHeaderToggle(headerText = "Personal Information")
                ProfileInfoItem(
                    infoText = "Height (cm)",
                    infoValueText = "${selectedProfile?.height ?: "N/A"}",
                    icon = Icons.Outlined.Height
                )
                ProfileInfoItem(
                    infoText = "Weight (kg)",
                    infoValueText = "${selectedProfile?.weight ?: "N/A"}",
                    icon = Icons.Outlined.Scale
                )
                ProfileInfoItem(
                    infoText = "Calories (kkal)",
                    infoValueText = "${selectedProfile?.calories ?: "N/A"}",
                    icon = Icons.Outlined.Fastfood
                )
                ProfileInfoItem(
                    infoText = "Max Heart Rate (bpm)",
                    infoValueText = "${selectedProfile?.max_heart_rate ?: "N/A"}",
                    icon = Icons.Outlined.MonitorHeart
                )
                ProfileInfoItem(
                    infoText = "Protein Intake (g)",
                    infoValueText = "${selectedProfile?.protein_intake ?: "N/A"}",
                    icon = Icons.Outlined.LocalDrink
                )

                Spacer(Modifier.height(16.dp))

                ProfileHeaderToggle(headerText = "Account")
                ProfileButtonItem(text = "Profile", icon = Icons.Outlined.Person, onClick = {})
                ProfileButtonItem(
                    text = "Multi-Factor Authentication",
                    icon = Icons.Outlined.Security,
                    onClick = {
                        navController.navigate(Routes.MfaSetupScreen)
                    })
                ProfileButtonItem(
                    text = "Medical Profile", icon = Icons.Outlined.MedicalInformation, onClick = {

                    })
                ProfileButtonItem(text = "Reminder", icon = Icons.Outlined.Timer, onClick = {

                })
                ProfileButtonItem(text = "Language", icon = Icons.Outlined.TextFields, onClick = {

                })
                ProfileButtonItem(text = "Timezone", icon = Icons.Outlined.Language, onClick = {

                })
                ProfileButtonItem(
                    text = "Accessibility", icon = Icons.Outlined.Accessibility, onClick = {

                    })

                Spacer(Modifier.height(16.dp))

                ProfileHeaderToggle(headerText = "General")
                ProfileButtonItem(
                    text = "About AI", icon = Icons.Outlined.ChatBubbleOutline, onClick = {
                        // This will navigate to the new screen we are about to create
                        navController.navigate(Routes.MfaSetupScreen)
                    })
                ProfileButtonItem(text = "About Us", icon = Icons.Outlined.Info, onClick = {
                    // This will navigate to the new screen we are about to create
                    navController.navigate(Routes.MfaSetupScreen)
                })
                ProfileButtonItem(
                    text = "Terms of Services", icon = Icons.Outlined.Description, onClick = {
                        // This will navigate to the new screen we are about to create
                        navController.navigate(Routes.MfaSetupScreen)
                    })

                Spacer(Modifier.height(16.dp))

            } else {
                if (errorMessage == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No profile data available.")
                    }
                }
            }
        }
    }
}