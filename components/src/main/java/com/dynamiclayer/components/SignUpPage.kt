package com.dynamiclayer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.inputField.InputField
import com.dynamiclayer.components.inputField.util.InputFieldSize
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun SignUpPage() {
    Scaffold(topBar = {
        TopNavigation(
            title = "SignUp",
            iconLeft = TopNavigationIconType.Vector(Icons.Outlined.KeyboardArrowLeft)
        )
    }, bottomBar = {
        Button(
            label = "Create Account",
            onClick = {
                //Action
            },
            type = ButtonType.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_16)
        )
    }, containerColor = ColorPalette.White) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_12),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
        ) {
            InputField(
                size = InputFieldSize.md,
                text = TextFieldValue(),
                onValueChange = {},
                placeholder = "FirstName"
            )
            InputField(
                size = InputFieldSize.md,
                text = TextFieldValue(),
                onValueChange = {},
                placeholder = "LastName"
            )
            InputField(
                size = InputFieldSize.md,
                text = TextFieldValue(""),
                onValueChange = {},
                placeholder = "Email"
            )
            InputField(
                size = InputFieldSize.md,
                text = TextFieldValue("1234"),
                onValueChange = {},
                placeholder = "Password", visualTransformation = PasswordVisualTransformation('*')
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SignUpPagePreview() {
    SignUpPage()
}