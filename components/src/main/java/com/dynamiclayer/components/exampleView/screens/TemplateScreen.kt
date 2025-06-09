package com.dynamiclayer.components.exampleView.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.card.Card
import com.dynamiclayer.components.card.util.models.CardSize
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun TemplateScreen(homeNavController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
        ) {
            item(span = {
                GridItemSpan(2)
            }) {
                TopNavigation(
                    title = "Template",
                    iconLeft = null,
                    iconRight = TopNavigationIconType.Drawable(drawable = R.drawable.dark_mode, tint = ColorPalette.Black, onClick = {
                        onDarkButtonClick?.invoke()
                    }),
                    size = TopNavigationSize.lg,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                Card(
                    title = "Login",
                    icon = R.drawable.lock_close,
                    description = "Template",
                    size = CardSize.lg,
                    onClick = {
                    },
                    modifier = Modifier
                        .then(
                            Modifier.padding(start = GeneralSpacing.p_12)
                        )
                        .fillMaxWidth(),iconTint = ColorPalette.Black
                )
            }
            item {
                Card(
                    title = "Sign up",
                    icon = R.drawable.lock_close,
                    description = "Template",
                    size = CardSize.lg,
                    onClick = {
                    },
                    modifier = Modifier
                        .then(
                            Modifier.padding(end = GeneralSpacing.p_12)
                        )
                        .fillMaxWidth(),iconTint = ColorPalette.Black
                )
            }
            item {
                Card(
                    title = "Forgot password",
                    icon = R.drawable.lock_close,
                    description = "Template",
                    size = CardSize.lg,
                    onClick = {
                    },
                    modifier = Modifier
                        .then(
                            Modifier.padding(start = GeneralSpacing.p_12)
                        )
                        .fillMaxWidth(),iconTint = ColorPalette.Black
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewContent() {
    TemplateScreen(null)
}