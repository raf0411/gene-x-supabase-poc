package com.dynamiclayer.components.radioButton

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.radioButton.utils.RadioButtonState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@Preview(showBackground = true)
@Composable
private fun AppRadioPreview() {
    RadioButtonSample()
}

@Composable
fun RadioButtonSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Radio Button",
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
                        title = "State",
                        description = "You can edit the state with default or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    val radio1 = remember {
                                        mutableStateOf(false)
                                    }
                                    RadioButton(
                                        isSelected = radio1.value,
                                        state = RadioButtonState.default,
                                        onClick = {
                                            radio1.value = !radio1.value
                                        }
                                    )

                                    val radio2 = remember {
                                        mutableStateOf(false)
                                    }
                                    RadioButton(
                                        isSelected = radio2.value,
                                        state = RadioButtonState.disabled,
                                        onClick = {
                                            radio2.value = !radio2.value
                                        }
                                    )


                                }

                            }
                        })
                }


            }
        })
}

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    state: RadioButtonState = RadioButtonState.default,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = remember(isSelected, state) {
        if (state == RadioButtonState.disabled) {
            ColorPalette.Grey.grey300
        } else {
            if (isSelected) {
                ColorPalette.Black
            } else {
                Color.Transparent
            }
        }
    }
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .border(
                if (!isSelected && state == RadioButtonState.default) {
                    1.dp
                } else {
                    0.dp
                },
                if (!isSelected)
                    ColorPalette.Grey.grey200
                else
                    Color.Unspecified, shape = CircleShape
            )
            .background(backgroundColor)
            .clickable(enabled = state != RadioButtonState.disabled, interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        if (isSelected || state == RadioButtonState.disabled) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(ColorPalette.White)
            )
        }
    }
}