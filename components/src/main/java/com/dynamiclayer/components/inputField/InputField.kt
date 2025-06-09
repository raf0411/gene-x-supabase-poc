package com.dynamiclayer.components.inputField

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.inputField.util.InputFieldIconType
import com.dynamiclayer.components.inputField.util.InputFieldSize
import com.dynamiclayer.components.inputField.util.InputFieldState
import com.dynamiclayer.components.inputField.util.InputFieldType
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.InputFieldColors
import com.dynamiclayer.components.ui.theme.InputFieldColors.customInputFieldColors
import com.dynamiclayer.components.ui.theme.InputFieldDimensions
import com.dynamiclayer.components.ui.theme.InputFieldDimensions.dimensionsHeight
import com.dynamiclayer.components.ui.theme.InputFieldPaddings


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun InputPreview() {
    InputFieldsSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun InputFieldsSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Input Field",
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
                        title = stringResource(R.string.type),
                        description = "You can edit the type with default, success, disabled or error parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val normalText = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    placeholder = "Input Field",
                                    text = normalText.value,
                                    onValueChange = {
                                        normalText.value = it
                                    }
                                )

                                val successText = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = successText.value,
                                    onValueChange = {
                                        successText.value = it
                                    },
                                    type = InputFieldType.success,
                                    placeholder = "Input Field",
                                    iconRight = true
                                )

                                val disableText = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    state = InputFieldState.disabled,
                                    text = disableText.value,
                                    onValueChange = {
                                        disableText.value = it
                                    },
                                    placeholder = "Input Field",
                                    type = InputFieldType.warning,
                                    iconRight = true
                                )

                                val errorText = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = errorText.value,
                                    onValueChange = {
                                        errorText.value = it
                                    },
                                    type = InputFieldType.error,
                                    errorText = "* Description",
                                    placeholder = "Input Field",
                                    iconRight = true
                                )

                            }

                        })

                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with sm, md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val normalTextLg = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextLg.value,
                                    onValueChange = {
                                        normalTextLg.value = it
                                    }, placeholder = "Input Field"
                                )

                                val normalTextMd = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.md,
                                    text = normalTextMd.value,
                                    onValueChange = {
                                        normalTextMd.value = it
                                    }, placeholder = "Input Field"
                                )

                                val normalTextSm = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.sm,
                                    text = normalTextSm.value,
                                    onValueChange = {
                                        normalTextSm.value = it
                                    }, placeholder = "Input Field"
                                )


                            }

                        })

                }
                item {
                    DetailContainer(
                        title = stringResource(R.string.state),
                        description = "You can edit the state with default, active or filled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val normalTextDefault = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextDefault.value,
                                    onValueChange = {
                                        normalTextDefault.value = it
                                    },
                                    placeholder = "Input Field"
                                )


                                val normalTextActive = remember {
                                    mutableStateOf(TextFieldValue("Input Field"))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextActive.value,
                                    onValueChange = {
                                        normalTextActive.value = it
                                    },
                                    placeholder = "Input Field"
                                )

                                val normalTextFilled = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextFilled.value,
                                    onValueChange = {
                                        normalTextFilled.value = it
                                    },
                                    placeholder = "Input Field"
                                )


                            }

                        })

                }
                item {
                    DetailContainer(
                        title = "iconLeft",
                        description = "You can edit the iconLeft with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val normalTextDefault = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextDefault.value,
                                    onValueChange = {
                                        normalTextDefault.value = it
                                    },
                                    placeholder = "Input Field",
                                    iconLeft = true,
                                    leftIcon = InputFieldIconType.Drawable(drawable = R.drawable.ic_crop)
                                )


                            }

                        })

                }

                item {
                    DetailContainer(
                        title = "iconRight",
                        description = "You can edit the iconRight with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val normalTextDefault = remember {
                                    mutableStateOf(TextFieldValue(""))
                                }
                                InputField(
                                    size = InputFieldSize.lg,
                                    text = normalTextDefault.value,
                                    onValueChange = {
                                        normalTextDefault.value = it
                                    },
                                    placeholder = "Input Field",
                                    iconRight = true,
                                    rightIcon = InputFieldIconType.Drawable(drawable = R.drawable.ic_crop)
                                )


                            }

                        })

                }

            }
        })


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    size: InputFieldSize,
    state: InputFieldState = InputFieldState.default,
    type: InputFieldType = InputFieldType.default,
    placeholder: String? = null,
    text: TextFieldValue,
    errorText: String? = "Error",
    iconRight: Boolean = true,
    rightIcon: InputFieldIconType? = null,
    iconLeft: Boolean = false,
    leftIcon: InputFieldIconType? = null,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1
) {
  val  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val isFocused = interactionSource.collectIsFocusedAsState().value

    val padding = InputFieldPaddings.inputFieldPadding(
        isFocused = isFocused || text.text.isNotEmpty(),
        size = size
    )
    val enabled = remember {
        state == InputFieldState.default
    }
    val textColor = InputFieldColors.textColor(
        enabled,
        type == InputFieldType.success,
        text.text.isEmpty(),
        interactionSource
    ).value
    val selectionColors = InputFieldColors.textSelectionColors(
        enabled,
        type == InputFieldType.error,
        interactionSource
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
        Column(
            modifier = Modifier
                .wrapContentSize(), verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
        ) {
            BasicTextField(
                value = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = if (isFocused || text.text.isNotEmpty()) 2.dp else 1.dp,
                        color = InputFieldColors.borderColor(
                            text.text.isNotEmpty(),
                            enabled, interactionSource
                        ).value,
                        shape = RoundedCornerShape(AppRadius.rounded_md)
                    )
                    .background(
                        color = ColorPalette.White,
                        shape = RoundedCornerShape(AppRadius.rounded_md)
                    )
                    .height(dimensionsHeight(size = size).value),
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = false,
                textStyle = AppTextWeight.text_base_regular.merge(color = textColor),
                cursorBrush = SolidColor(InputFieldColors.cursorColor(type == InputFieldType.error).value),
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                interactionSource = interactionSource,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                decorationBox = @Composable { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = text.text,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        label = placeholder?.let {
                            {
                                Label(
                                    label = placeholder,
                                    isNotEmpty = isFocused || text.text.isNotEmpty(),
                                    isError = type == InputFieldType.error,
                                    enabled = enabled
                                )
                            }
                        },
                        trailingIcon = if (iconRight ||
                            rightIcon != null
                        ) {
                            {
                                TrailingIcon(
                                    trailingIcon = rightIcon,
                                    type = type
                                )
                            }
                        } else null,
                        leadingIcon = if (iconLeft ||
                            leftIcon != null
                        ) {
                            {
                                LeadingIcon(
                                    leadingIcon = leftIcon,
                                    type = type
                                )
                            }
                        } else null,
                        supportingText = null,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = type == InputFieldType.error,
                        contentPadding = padding,
                        interactionSource = interactionSource,
                        colors = customInputFieldColors(
                            enabled = enabled,
                            isError = type == InputFieldType.error,
                            isSuccess = type == InputFieldType.success,
                            isEmpty = text.text.isEmpty(),
                            interactionSource = interactionSource
                        ),
                        container = {
                            Box(
                                modifier = Modifier
                            )
                        }
                    )
                }
            )
            if (type == InputFieldType.error) {
                SupportingText(
                    supportingText = errorText ?: "Error",
                    size = size
                )
            }
        }
    }
}

@Composable
fun SupportingText(
    supportingText: String,
    size: InputFieldSize
) {
    val color = InputFieldColors.supportingErrorColor
    val mergedTextStyle = AppTextWeight.text_sm_medium.merge(color = color)

    Text(
        modifier = Modifier
            .wrapContentSize(),
        text = supportingText,
        style = mergedTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Label(
    label: String,
    isNotEmpty: Boolean,
    isError: Boolean,
    enabled: Boolean
) {
    val color = InputFieldColors.labelColor(isError, enabled).value

    val mergedTextStyle = if (isNotEmpty) {
        AppTextWeight.text_xs_regular.copy(color = color)
    } else {
        if (enabled) {
            AppTextWeight.text_base_regular.merge(color = color)
        } else {
            AppTextStrike.text_base
        }
    }

    Text(
        text = label,
        style = mergedTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
    )
}


@Composable
fun TrailingIcon(
    trailingIcon: InputFieldIconType? = null,
    type: InputFieldType
) {

    val icon = when (type) {
        InputFieldType.warning -> InputFieldIconType.Drawable(drawable = R.drawable.ic_input_disabled)
        InputFieldType.error -> InputFieldIconType.Drawable(drawable = R.drawable.ic_error)
        InputFieldType.success -> InputFieldIconType.Drawable(drawable = R.drawable.ic_input_success)
        else -> trailingIcon
    }
    InputFieldIcon(
        icon = icon
    )
}

@Composable
fun LeadingIcon(
    leadingIcon: InputFieldIconType? = null,
    type: InputFieldType
) {

    val icon = when (type) {
        InputFieldType.warning -> InputFieldIconType.Drawable(drawable = R.drawable.ic_input_disabled)
        InputFieldType.error -> InputFieldIconType.Drawable(drawable = R.drawable.ic_error)
        InputFieldType.success -> InputFieldIconType.Drawable(drawable = R.drawable.ic_input_success)
        else -> leadingIcon
    }

    InputFieldIcon(
        icon = icon
    )
}

@Composable
fun InputFieldIcon(
    modifier: Modifier = Modifier,
    icon: InputFieldIconType? = InputFieldIconType.Drawable(drawable = R.drawable.ic_default),
    contentDescription: String? = null
) {
    when (icon) {
        is InputFieldIconType.Vector -> IconButton(modifier = modifier.size(
            InputFieldDimensions.inputFieldIconsSize
        ),
            onClick = { icon.onClick?.invoke() }) {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = contentDescription
            )
        }

        is InputFieldIconType.Drawable -> IconButton(modifier = modifier.size(
            InputFieldDimensions.inputFieldIconsSize
        ),
            onClick = { icon.onClick?.invoke() }) {
            Image(
                painter = painterResource(icon.drawable),
                contentDescription = contentDescription
            )
        }

        else -> {}
    }

}


