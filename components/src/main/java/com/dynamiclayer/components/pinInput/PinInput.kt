package com.dynamiclayer.components.pinInput

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.pinInput.utils.PinInputState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun PinInputPreview() {
    PinInputSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun PinInputSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Pin Input",
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
                        title = stringResource(id = R.string.state),
                        description = "You can edit the state with default, active, filled, disabled, error or success parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    PinInput(value = remember {
                                        mutableStateOf("")
                                    })
                                    PinInput(value = remember {
                                        mutableStateOf("3")
                                    }, state = PinInputState.active)
                                    PinInput(value = remember {
                                        mutableStateOf("3")
                                    }, state = PinInputState.filled)
                                    PinInput(value = remember {
                                        mutableStateOf("-")
                                    }, state = PinInputState.disabled)
                                    PinInput(value = remember {
                                        mutableStateOf("3")
                                    }, state = PinInputState.error)
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = GeneralSpacing.p_16),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {

                                    PinInput(value = remember {
                                        mutableStateOf("3")
                                    }, state = PinInputState.success)
                                }
                            }
                        })
                }
            }
        })

}


@Composable
fun PinInput(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    state: PinInputState = PinInputState.default
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val shape = remember {
        RoundedCornerShape(AppRadius.rounded_md)
    }
    var mutableState by remember {
        mutableStateOf(state)
    }
    LaunchedEffect(isFocused) {
        if (isFocused &&
            mutableState != PinInputState.success &&
            mutableState != PinInputState.error &&
            mutableState != PinInputState.disabled
        ) {
            mutableState = PinInputState.active
        }
    }
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(shape)
            .border(mutableState.border, shape = shape)
            .background(color = mutableState.containerColor, shape = shape),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = if (state == PinInputState.disabled) "-" else value.value,
            onValueChange = {
                if (it.length <= 1) {
                    value.value = it
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = mutableState.textStyle.copy(
                textAlign = TextAlign.Center,
                color = mutableState.contentColor
            ),
            maxLines = 1,
            interactionSource = interactionSource,
           cursorBrush = SolidColor(ColorPalette.Black),
            enabled = state != PinInputState.disabled, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

}
