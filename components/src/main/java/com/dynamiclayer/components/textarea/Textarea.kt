package com.dynamiclayer.components.textarea

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
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
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class TextareaState {
    default, disabled;
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TextareaPreview() {
    TextareaSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun TextareaSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Textarea",
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
                        description = "You can edit the state with default, active, filled or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val inputValue1 = remember {
                                    mutableStateOf("")
                                }
                                Textarea(
                                    value = inputValue1.value,
                                    label = "Tell us something about you",
                                    state = TextareaState.default, onValueChange = {
                                        inputValue1.value = it
                                    })

                                val inputValue2 = remember {
                                    mutableStateOf("")
                                }
                                Textarea(
                                    value = inputValue2.value,
                                    label = "",
                                    state = TextareaState.default, onValueChange = {
                                        inputValue2.value = it
                                    })

                                val inputValue3 = remember {
                                    mutableStateOf("May I know what is the noise level like in the area?")
                                }
                                Textarea(
                                    value = inputValue3.value,
                                    label = "Tell us something about you",
                                    state = TextareaState.default, onValueChange = {
                                        inputValue3.value = it
                                    })

                                val inputValue4 = remember {
                                    mutableStateOf("")
                                }
                                Textarea(
                                    value = inputValue4.value,
                                    label = "Tell us something about you",
                                    state = TextareaState.disabled, onValueChange = {
                                        inputValue4.value = it
                                    })
                            }
                        })
                }

            }
        })


}

@Composable
fun Textarea(
    modifier: Modifier = Modifier,
    value: String,
    label: String = "",
    state: TextareaState = TextareaState.default,
    onValueChange: (String) -> Unit
) {
    val mutableInteraction = remember {
        MutableInteractionSource()
    }
    val isFocused = mutableInteraction.collectIsFocusedAsState()
    val borderColor by remember(isFocused.value, state) {
        derivedStateOf {
            when (state) {
                TextareaState.default -> if (isFocused.value) ColorPalette.Black else ColorPalette.Grey.grey200
                TextareaState.disabled -> ColorPalette.Grey.grey200

            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(AppRadius.rounded_md))
            .border(
                if (isFocused.value) 2.dp else 1.dp,
                borderColor,
                RoundedCornerShape(AppRadius.rounded_md)
            )
            .background(color = ColorPalette.White)
            .padding(GeneralPaddings.p_16)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            decorationBox = {
                if (value.isEmpty()) {
                    Text(
                        text = label,
                        style = if (state == TextareaState.disabled) AppTextStrike.text_base else AppTextWeight.text_base_regular,
                        color = ColorPalette.Grey.grey500
                    )
                }
                it()
            },
            modifier = Modifier.fillMaxSize(),
            cursorBrush = SolidColor(ColorPalette.Black),
            textStyle = AppTextWeight.text_base_regular.copy(color = ColorPalette.Black),
            enabled = state == TextareaState.default, interactionSource = mutableInteraction
        )
    }
}