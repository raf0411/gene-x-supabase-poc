package com.dynamiclayer.components.buttonDock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.buttonDock.utils.ButtonCount
import com.dynamiclayer.components.buttonDock.utils.ButtonDockDirection
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun ButtonDockPreview() {
    ButtonsDockSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun ButtonsDockSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "button Dock",
                size = TopNavigationSize.md,
                iconLeft = TopNavigationIconType.Vector(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    onClick = {
                        navController?.popBackStack()
                    }, tint = ColorPalette.Black
                ),
                iconRight = TopNavigationIconType.Drawable(
                    drawable = R.drawable.dark_mode,
                    onClick = {
                        onDarkButtonClick?.invoke()
                    },
                    tint = ColorPalette.Black
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
            ) {
                item {
                    DetailContainer(
                        title = "Count",
                        description = "You can edit the count with 1 or 2 parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                ButtonDock(
                                    count = ButtonCount.one,
                                    firstButtonTitle = "button",
                                    firstButtonClick = {})
                                ButtonDock(
                                    count = ButtonCount.two,
                                    firstButtonTitle = "button",
                                    direction = ButtonDockDirection.vertical,
                                    firstButtonClick = {},
                                    secondButtonTitle = "button",
                                    secondButtonClick = {})
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Direction",
                        description = "You can edit the direction with vertical or horizontal parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                ButtonDock(
                                    count = ButtonCount.two,
                                    firstButtonTitle = "button",
                                    direction = ButtonDockDirection.horizontal,
                                    firstButtonClick = {},
                                    secondButtonTitle = "button",
                                    secondButtonClick = {})
                            }
                        })
                }
            }
        })

}

@Composable
fun ButtonDock(
    modifier: Modifier = Modifier,
    count: ButtonCount = ButtonCount.one,
    direction: ButtonDockDirection = ButtonDockDirection.vertical,
    firstButtonTitle: String = "",
    secondButtonTitle: String = "",
    firstButtonClick: () -> Unit = {},
    secondButtonClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_16)
        ) {
            if (direction == ButtonDockDirection.vertical) {
                if (count == ButtonCount.one) {
                    Button(
                        label = firstButtonTitle,
                        onClick = firstButtonClick,
                        size = ButtonSize.lg,
                        type = ButtonType.secondary, modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                    ) {
                        Button(
                            label = firstButtonTitle,
                            onClick = firstButtonClick,
                            size = ButtonSize.lg,
                            type = ButtonType.secondary, modifier = Modifier.fillMaxWidth()
                        )
                        Button(
                            label = secondButtonTitle,
                            onClick = secondButtonClick,
                            size = ButtonSize.lg,
                            type = ButtonType.tertiary, modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
            } else {
                if (count == ButtonCount.one) {
                    Button(
                        label = firstButtonTitle,
                        onClick = firstButtonClick,
                        size = ButtonSize.lg,
                        type = ButtonType.secondary, modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                    ) {
                        Button(
                            label = firstButtonTitle,
                            onClick = firstButtonClick,
                            size = ButtonSize.lg,
                            type = ButtonType.tertiary, modifier = Modifier.weight(1f)
                        )
                        Button(
                            label = secondButtonTitle,
                            onClick = secondButtonClick,
                            size = ButtonSize.lg,
                            type = ButtonType.secondary, modifier = Modifier.weight(1f)
                        )


                    }
                }
            }
        }
    }
}