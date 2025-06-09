package com.dynamiclayer.components.exampleView.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material3.Text
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun SettingScreen(homeNavController: NavController?) {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        TopNavigation(
            title = "Settings",
            iconLeft = null,
            iconRight = TopNavigationIconType.Drawable(drawable = R.drawable.dark_mode, tint = ColorPalette.Black, onClick = {
                onDarkButtonClick?.invoke()
            }),
            size = TopNavigationSize.lg,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_32),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dynamiclayer),
                contentDescription = "",
                modifier = Modifier.size(240.dp), colorFilter = ColorFilter.tint(ColorPalette.Black)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
            ) {

                Text(
                    text = "Dynamic Layer",
                    style = AppTextWeight.text_2xl_semiBold,
                    color = ColorPalette.Black
                )
                Text(
                    text = "Dynamic Layer is a component library for Figma, Swift, Kotlin and React Native. Not only do you have the option to install the library, you also get the complete files to make changes to the components yourself.",
                    style = AppTextWeight.text_sm_regular,
                    color = ColorPalette.Grey.grey500, textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContent() {
    SettingScreen(null)
}