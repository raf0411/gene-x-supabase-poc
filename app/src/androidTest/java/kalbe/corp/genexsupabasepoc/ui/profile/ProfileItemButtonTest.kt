package kalbe.corp.genexsupabasepoc.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import kalbe.corp.genexsupabasepoc.ui.components.ProfileButtonItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class ProfileButtonItemTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun profileButtonItem_whenGivenText_displaysTextCorrectly() {
        composeTestRule.setContent {
            ProfileButtonItem(
                text = "Edit Profile",
                icon = Icons.Default.Person,
                onClick = { }
            )
        }

        composeTestRule.onNodeWithText("Edit Profile").assertIsDisplayed()
    }

    @Test
    fun profileButtonItem_whenClicked_invokesOnClickLambda() {
        var isClicked = false

        composeTestRule.setContent {
            ProfileButtonItem(
                text = "Notifications",
                icon = Icons.Default.Person,
                onClick = { isClicked = true }
            )
        }

        composeTestRule.onNodeWithTag("ProfileButtonItem_IconButton").performClick()

        assertTrue(isClicked, "The onClick lambda was not invoked.")
    }

    @Test
    fun profileButtonItem_whenTextIsLanguage_displaysLanguageLabel() {
        composeTestRule.setContent {
            ProfileButtonItem(
                text = "Language",
                icon = Icons.Default.Language,
                onClick = { }
            )
        }

        composeTestRule.onNodeWithText("English (UK)").assertIsDisplayed()
    }

    @Test
    fun profileButtonItem_whenTextIsNotLanguage_doesNotDisplayLanguageLabel() {
        composeTestRule.setContent {
            ProfileButtonItem(
                text = "Edit Profile",
                icon = Icons.Default.Person,
                onClick = { }
            )
        }

        composeTestRule.onNodeWithText("English (UK)").assertDoesNotExist()
    }
}