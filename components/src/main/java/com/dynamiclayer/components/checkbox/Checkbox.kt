package com.dynamiclayer.components.checkbox

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
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
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class CheckboxState {
    default, disabled;
}

@Preview(showBackground = true)
@Composable
private fun AppCheckPreview() {
    CheckBoxSample()
}

@Composable
fun CheckBoxSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Checkbox",
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
                                    val check1 = remember {
                                        mutableStateOf(false)
                                    }
                                    Checkbox(
                                        isActive = check1.value,
                                        state = CheckboxState.default,
                                        onCheckChange = {
                                            check1.value = it
                                        }
                                    )

                                    val check2 = remember {
                                        mutableStateOf(false)
                                    }
                                    Checkbox(
                                        isActive = check2.value,
                                        state = CheckboxState.disabled,
                                        onCheckChange = {
                                            check2.value = it
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
fun Checkbox(
    modifier: Modifier = Modifier,
    state: CheckboxState = CheckboxState.default,
    isActive: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    val backgroundColor = remember(isActive, state) {
        if (state == CheckboxState.disabled) {
            ColorPalette.Grey.grey300
        } else {
            if (isActive) {
                ColorPalette.Black
            } else {
                Color.Transparent
            }
        }
    }
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(RoundedCornerShape(AppRadius.rounded_sm))
            .border(
                if (!isActive && state == CheckboxState.default) {
                    1.dp
                } else {
                    0.dp
                },
                if (!isActive)
                    ColorPalette.Grey.grey200
                else
                    Color.Unspecified
            )
            .background(backgroundColor)
            .clickable(enabled = state == CheckboxState.default, interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                onCheckChange(!isActive)
            }, contentAlignment = Alignment.Center
    ) {

        if (isActive && state == CheckboxState.default) {
            Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = "",
                tint = ColorPalette.White,
                modifier = Modifier.padding(GeneralPaddings.p_4)
            )
        }
        if (state == CheckboxState.disabled) {
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(color = ColorPalette.White)
                    .padding(vertical = 1.dp)
            )
        }

    }
}