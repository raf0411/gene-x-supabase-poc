package kalbe.corp.genexsupabasepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kalbe.corp.genexsupabasepoc.repositories.AuthRepository
import kalbe.corp.genexsupabasepoc.repositories.ProductRepository
import kalbe.corp.genexsupabasepoc.repositories.ProfilePreferencesRepositoryImpl
import kalbe.corp.genexsupabasepoc.repositories.ProfileRepository
import kalbe.corp.genexsupabasepoc.repositories.UserRepository
import kalbe.corp.genexsupabasepoc.repositories.WishlistRepository
import kalbe.corp.genexsupabasepoc.repositories.profileDataStore
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.navigation.Routes
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme
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
        setContent {
            GeneXSupabasePOCTheme {
                val authReady = remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    val ok = authRepository.restoreSession()
                    keepOn = false
                    authReady.value = ok
                }

                if (authReady.value) {
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