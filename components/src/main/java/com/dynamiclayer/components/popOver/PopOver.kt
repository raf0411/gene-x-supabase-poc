package com.dynamiclayer.components.popOver

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing


enum class PopOverDirection {
    vertical, horizontal;
}

@Stable
data class PopOverItem(val title: String, var isActive: Boolean = false)

@Stable
data class PopOverButton(
    val btnTertiaryTitle: String,
    val onTertiaryClick: () -> Unit,
    val btnSecondaryTitle: String,
    val onSecondaryClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun PopOverPreview() {
    PopOverSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun PopOverSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Pop Over",
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
                        title = "Icon",
                        description = "You can edit the icon with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                PopOver(
                                    headline = "Title",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    icon = R.drawable.ic_crop,
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }
                                )
                                PopOver(
                                    headline = "Title",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    iconEnable = false,
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Direction",
                        description = "You can change the direction with PopOverDirection.vertical or PopOverDirection.horizontal parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                PopOver(
                                    headline = "Title",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    icon = R.drawable.ic_crop,
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }, direction = PopOverDirection.horizontal
                                )
                                PopOver(
                                    headline = "Title",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    icon = R.drawable.ic_crop,
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }, direction = PopOverDirection.vertical
                                )
                            }
                        })
                }


            }
        })


}

@Composable
fun PopOver(
    modifier: Modifier = Modifier,
    headline: String,
    icon: Int? = null,
    iconEnable: Boolean = true,
    description: String,
    buttons: PopOverButton,
    direction: PopOverDirection = PopOverDirection.horizontal,
    onDismiss: () -> Unit
) {

    Column(
        modifier = modifier
            .width(343.dp)
            .clip(RoundedCornerShape(AppRadius.rounded_xl))
            .border(
                1.dp, color = ColorPalette.Grey.grey200, shape = RoundedCornerShape(
                    AppRadius.rounded_xl
                )
            )
            .background(color = ColorPalette.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clickable(null, null, onClick = onDismiss), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.cross),
                    tint = ColorPalette.Black,
                    modifier = Modifier
                        .size(24.dp),
                    contentDescription = "Close",
                )
            }
            Text(
                text = headline,
                style = AppTextWeight.text_base_semiBold,
                modifier = Modifier.align(
                    Alignment.Center
                ), color = ColorPalette.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorPalette.Grey.grey200)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_40),
            verticalArrangement = Arrangement.spacedBy(
                GeneralSpacing.p_32
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (iconEnable)
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(AppRadius.rounded_full))
                        .border(
                            1.dp,
                            color = ColorPalette.Grey.grey200,
                            shape = RoundedCornerShape(AppRadius.rounded_full)
                        ), contentAlignment = Alignment.Center
                ) {
                    if (icon != null)
                        Icon(
                            painter = painterResource(icon),
                            modifier = Modifier.size(48.dp),
                            contentDescription = headline, tint = ColorPalette.Black
                        )
                }
            Text(
                description,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = AppTextWeight.text_base_regular, color = ColorPalette.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorPalette.Grey.grey200)
        )
        if (direction == PopOverDirection.horizontal) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GeneralPaddings.p_16),
                horizontalArrangement = Arrangement.spacedBy(
                    GeneralSpacing.p_16
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    label = buttons.btnTertiaryTitle,
                    onClick = buttons.onTertiaryClick,
                    type = ButtonType.tertiary
                )
                Button(
                    modifier = Modifier.weight(1f),
                    label = buttons.btnSecondaryTitle,
                    onClick = buttons.onSecondaryClick,
                    type = ButtonType.secondary
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GeneralPaddings.p_16),
                verticalArrangement = Arrangement.spacedBy(
                    GeneralSpacing.p_16
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    label = buttons.btnSecondaryTitle,
                    onClick = buttons.onSecondaryClick,
                    type = ButtonType.secondary
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    label = buttons.btnTertiaryTitle,
                    onClick = buttons.onTertiaryClick,
                    type = ButtonType.tertiary
                )
            }
        }
    }
}