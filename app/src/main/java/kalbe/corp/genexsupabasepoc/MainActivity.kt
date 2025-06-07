package kalbe.corp.genexsupabasepoc

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.Coil
import com.google.firebase.messaging.FirebaseMessaging
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.repositories.AuthRepository
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.repositories.ProfilePreferencesRepositoryImpl
import kalbe.corp.genexsupabasepoc.repositories.ProfileRepository
import kalbe.corp.genexsupabasepoc.repositories.UserRepository
import kalbe.corp.genexsupabasepoc.repositories.WishlistRepository
import kalbe.corp.genexsupabasepoc.repositories.profileDataStore
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme
import kalbe.corp.genexsupabasepoc.utils.CoilLoader
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory

class MainActivity : ComponentActivity() {
    private val context = this
    private val dataStore by lazy { applicationContext.profileDataStore }
    private val profilePreferencesRepository by lazy { ProfilePreferencesRepositoryImpl(dataStore) }
    private val userRepository by lazy { UserRepository() }
    private val profileRepository by lazy { ProfileRepository() }
    private val productRepository by lazy { ProductRepository() }
    private val wishlistRepository by lazy { WishlistRepository() }
    private val authRepository by lazy { AuthRepository(context = context) }

    val TAG = "NotificationService"

    private val profileViewModelFactory by lazy {
        ProfileViewModelFactory(
            profileRepository = profileRepository,
            userRepository = userRepository,
            profilePreferencesRepository = profilePreferencesRepository,
            authRepository = authRepository,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepOn = true
        splashScreen.setKeepOnScreenCondition { keepOn }

        super.onCreate(savedInstanceState)

        Coil.setImageLoader(CoilLoader.get(applicationContext))

        askNotificationPermission()

        setContent {
            GeneXSupabasePOCTheme {
                val authReady = remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    val ok = authRepository.restoreSession()
                    keepOn = false
                    authReady.value = ok
                }

                if (authReady.value) {
                    getAndStoreFcmToken()

                    MyApp(
                        profileViewModelFactory = profileViewModelFactory,
                        productRepository = productRepository,
                        wishlistRepository = wishlistRepository,
                        authRepository = authRepository,
                        userRepository = userRepository,
                        startDestination = Routes.DashboardScreen,
                    )
                } else {
                    MyApp(
                        profileViewModelFactory = profileViewModelFactory,
                        productRepository = productRepository,
                        wishlistRepository = wishlistRepository,
                        authRepository = authRepository,
                        userRepository = userRepository,
                        startDestination = Routes.LoginScreen,
                    )
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Log.d(TAG, "Notification permission granted.")
                getAndStoreFcmToken()
            } else {
                Log.w(TAG, "Notification permission denied.")
            }
        }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                getAndStoreFcmToken()
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: Display an educational UI explaining why the permission is needed
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            getAndStoreFcmToken()
        }
    }

    private fun getAndStoreFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val fcmToken = task.result
            Log.d(TAG, "Current FCM Token: $fcmToken")

            if (fcmToken == null) {
                Log.w(TAG, "Fetched FCM token is null.")
                return@addOnCompleteListener
            }

//            lifecycleScope.launch {
//                val userId = supabaseClient.auth.currentUserOrNull()?.id
//
//                if (userId != null) {
//                    val success = userRepository.updateUserFcmToken(userId, fcmToken)
//                    if (success) {
//                        Log.i(TAG, "FCM Token sent to Supabase from MainActivity.")
//                    } else {
//                        Log.w(TAG, "Failed to send FCM Token to Supabase from MainActivity.")
//                    }
//                } else {
//                    Log.w(TAG, "User not authenticated, cannot send FCM token from MainActivity.")
//                }
//            }
        }
    }
}

@Composable
fun MyApp(
    profileViewModelFactory: ProfileViewModelFactory,
    productRepository: ProductRepository,
    wishlistRepository: WishlistRepository,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    startDestination: Routes
) {
    NavGraph(
        profileViewModelFactory = profileViewModelFactory,
        productRepository = productRepository,
        wishlistRepository = wishlistRepository,
        authRepository = authRepository,
        userRepository = userRepository,
        startDestination = startDestination,
    )
}