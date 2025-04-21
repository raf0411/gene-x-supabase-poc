package kalbe.corp.genexsupabasepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import kalbe.corp.genexsupabasepoc.data.AuthRepository
import kalbe.corp.genexsupabasepoc.data.ProductRepository
import kalbe.corp.genexsupabasepoc.data.ProfilePreferencesRepositoryImpl
import kalbe.corp.genexsupabasepoc.data.ProfileRepository
import kalbe.corp.genexsupabasepoc.data.UserRepository
import kalbe.corp.genexsupabasepoc.data.WishlistRepository
import kalbe.corp.genexsupabasepoc.data.profileDataStore
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme
import kalbe.corp.genexsupabasepoc.viewModel.ProfileViewModelFactory

class MainActivity : ComponentActivity() {
    private val dataStore by lazy { applicationContext.profileDataStore }
    private val profilePreferencesRepository by lazy { ProfilePreferencesRepositoryImpl(dataStore) }
    private val userRepository by lazy { UserRepository() }
    private val profileRepository by lazy { ProfileRepository() }
    private val productRepository by lazy { ProductRepository() }
    private val wishlistRepository by lazy { WishlistRepository() }
    private val authRepository by lazy { AuthRepository() }

    private val profileViewModelFactory by lazy {
        ProfileViewModelFactory(
            profileRepository = profileRepository,
            userRepository = userRepository,
            profilePreferencesRepository = profilePreferencesRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneXSupabasePOCTheme {
                MyApp(
                    profileViewModelFactory,
                    productRepository = productRepository,
                    wishlistRepository = wishlistRepository,
                    authRepository = authRepository,
                    userRepository = userRepository,
                )
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
) {
    NavGraph(
        profileViewModelFactory = profileViewModelFactory,
        productRepository = productRepository,
        wishlistRepository = wishlistRepository,
        authRepository = authRepository,
        userRepository = userRepository,
    )
}