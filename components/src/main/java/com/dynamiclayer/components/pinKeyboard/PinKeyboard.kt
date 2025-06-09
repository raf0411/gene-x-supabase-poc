package com.dynamiclayer.components.pinKeyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Immutable
sealed class PinKeyboardType {
    data class Text(val text: String) : PinKeyboardType()
    data class Icon(val icon: Int) : PinKeyboardType()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun PinKeyboardPreview() {
    PinKeyboardSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun PinKeyboardSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Pin Keyboard",
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
                        title = "Type",
                        description = "You can edit the type with text or icon parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    PinKeyboard(
                                        type = PinKeyboardType.Text("1"),
                                        description = "ABC", onClick = {}
                                    )
                                    PinKeyboard(
                                        type = PinKeyboardType.Icon(R.drawable.chevron_left),
                                        onClick = {}
                                    )
                                }
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Description",
                        description = "You can edit the description with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    PinKeyboard(
                                        type = PinKeyboardType.Text("1"),
                                        description = "ABC",
                                        onClick = {}
                                    )
                                    PinKeyboard(
                                        type = PinKeyboardType.Text("1"), onClick = {}
                                    )


                                }
                            }
                        })
                }
            }
        })


}

@Composable
fun PinKeyboard(
    modifier: Modifier = Modifier,
    type: PinKeyboardType,
    description: String = "",
    onClick: () -> Unit
) {
    val interaction = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = modifier
            .size(80.dp)
            .clip(RoundedCornerShape(AppRadius.rounded_full))
            .border(1.dp, ColorPalette.Grey.grey200, RoundedCornerShape(AppRadius.rounded_full))
            .background(ColorPalette.White)
            .clickable(
                interaction,
                indication = rememberRipple(color = ColorPalette.Grey.grey50),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (type is PinKeyboardType.Text) {
                Text(type.text, style = AppTextWeight.text_3xl_regular, color = ColorPalette.Black)
            } else if (type is PinKeyboardType.Icon) {
                Icon(
                    painter = painterResource(type.icon),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp), tint = ColorPalette.Black
                )
            }
            if (description.isNotEmpty()) {
                Text(
                    description,
                    style = AppTextWeight.text_sm_regular,
                    color = ColorPalette.Grey.grey500
                )
            }
        }
    }
}