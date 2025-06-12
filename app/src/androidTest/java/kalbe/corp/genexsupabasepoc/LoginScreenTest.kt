package kalbe.corp.genexsupabasepoc

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Fake Navigation Controller
class FakeNavController {
    var navigatedRoute: String? = null
    fun navigate(route: String) {
        navigatedRoute = route
    }
}

// Fake Navigation Routes
object Routes {
    fun EmailLoginScreen(email: String) = "email_login/$email"
    fun OtpScreen(phone: String) = "otp/$phone"
}

// Fake AuthRepository
interface AuthRepository {
    suspend fun sendOtpToPhone(phone: String): Boolean
}

class FakeSuccessAuthRepository : AuthRepository {
    override suspend fun sendOtpToPhone(phone: String): Boolean = true
}

class FakeFailureAuthRepository : AuthRepository {
    override suspend fun sendOtpToPhone(phone: String): Boolean = false
}


// A simplified InputField Composable for the purpose of this test.
@Composable
fun InputField(
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    errorText: String? = null
) {
    Column {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifier,
            placeholder = { Text(placeholder) },
            isError = errorText != null
        )
        if (errorText != null) {
            Text(
                text = errorText,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.testTag("ErrorText") // Tag for the error message
            )
        }
    }
}


// --- Composable Under Test with testTag Modifiers ---

@Composable
fun LoginScreen(
    authRepository: AuthRepository,
    navController: FakeNavController, // Using the fake for testability
){
    var loginField by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    fun isEmail(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isPhone(input: String): Boolean {
        // A simple regex for demonstration
        val phoneRegex = Regex("^\\+?\\d{10,15}$")
        return phoneRegex.matches(input)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.grey)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .testTag("LoginImage"), // Added test tag
            painter = painterResource(id = R.drawable.dna),
            contentDescription = "DNA Image",
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(color = colorResource(id = R.color.white)),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Email/Phone", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))

                InputField(
                    onValueChange = { loginField = it },
                    errorText = errorMessage,
                    placeholder = "Enter your email/phone",
                    text = loginField,
                    modifier = Modifier.testTag("LoginInputField") // Prioritized test tag
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val loginString = loginField.text
                            try {
                                if (isEmail(loginString)) {
                                    navController.navigate(Routes.EmailLoginScreen(email = loginString))
                                } else if (isPhone(loginString)) {
                                    val success = authRepository.sendOtpToPhone(loginString)
                                    if (success) {
                                        navController.navigate(Routes.OtpScreen(phone = loginString))
                                    } else {
                                        errorMessage = "Failed to send OTP. Please check the number or try again."
                                    }
                                } else {
                                    errorMessage = "Invalid email or phone format."
                                }
                            } catch (e: Exception) {
                                errorMessage = "Login failed: ${e.message}"
                            }
                        }
                    },
                    // Prioritized test tag
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("LoginButton"),
                ) {
                    Text("Login")
                }
            }
        }
    }
}

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: FakeNavController

    @Test
    fun loginScreen_whenDisplayed_rendersInitialStateCorrectly() {
        // Arrange
        composeTestRule.setContent {
            navController = FakeNavController()
            LoginScreen(
                authRepository = FakeSuccessAuthRepository(),
                navController = navController
            )
        }

        // Act & Assert
        composeTestRule.onNodeWithTag("LoginImage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LoginInputField").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LoginButton").assertIsDisplayed().assertIsEnabled()
        composeTestRule.onNodeWithTag("ErrorText").assertDoesNotExist()
    }

    @Test
    fun loginScreen_whenValidEmailEntered_navigatesToEmailLoginScreen() {
        // Arrange
        val testEmail = "test@example.com"
        composeTestRule.setContent {
            navController = FakeNavController()
            LoginScreen(
                authRepository = FakeSuccessAuthRepository(),
                navController = navController
            )
        }

        // Act
        composeTestRule.onNodeWithTag("LoginInputField")
            .performTextInput(testEmail)
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Assert
        composeTestRule.waitForIdle() // Wait for coroutine and navigation
        val expectedRoute = Routes.EmailLoginScreen(email = testEmail)
        assertEquals(expectedRoute, navController.navigatedRoute)
    }

    @Test
    fun loginScreen_whenValidPhoneAndOtpSuccess_navigatesToOtpScreen() {
        // Arrange
        val testPhone = "+12345678901"
        composeTestRule.setContent {
            navController = FakeNavController()
            LoginScreen(
                authRepository = FakeSuccessAuthRepository(), // OTP send will succeed
                navController = navController
            )
        }

        // Act
        composeTestRule.onNodeWithTag("LoginInputField")
            .performTextInput(testPhone)
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Assert
        composeTestRule.waitForIdle() // Wait for coroutine and navigation
        val expectedRoute = Routes.OtpScreen(phone = testPhone)
        assertEquals(expectedRoute, navController.navigatedRoute)
    }

    @Test
    fun loginScreen_whenInvalidInput_showsInvalidFormatError() {
        // Arrange
        val invalidInput = "invalid-input"
        composeTestRule.setContent {
            navController = FakeNavController()
            LoginScreen(
                authRepository = FakeSuccessAuthRepository(),
                navController = navController
            )
        }

        // Act
        composeTestRule.onNodeWithTag("LoginInputField")
            .performTextInput(invalidInput)
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Assert
        composeTestRule.onNodeWithTag("ErrorText")
            .assertIsDisplayed()
            .assertTextEquals("Invalid email or phone format.")
        assertEquals(null, navController.navigatedRoute) // Ensure no navigation occurred
    }

    @Test
    fun loginScreen_whenValidPhoneAndOtpFails_showsOtpFailedError() {
        // Arrange
        val testPhone = "+12345678901"
        composeTestRule.setContent {
            navController = FakeNavController()
            LoginScreen(
                authRepository = FakeFailureAuthRepository(), // OTP send will fail
                navController = navController
            )
        }

        // Act
        composeTestRule.onNodeWithTag("LoginInputField")
            .performTextInput(testPhone)
        composeTestRule.onNodeWithTag("LoginButton")
            .performClick()

        // Assert
        composeTestRule.onNodeWithTag("ErrorText")
            .assertIsDisplayed()
            .assertTextEquals("Failed to send OTP. Please check the number or try again.")
        assertEquals(null, navController.navigatedRoute) // Ensure no navigation occurred
    }
}