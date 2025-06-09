package com.dynamiclayer.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.util.ButtonState
import com.dynamiclayer.components.button.util.components.ButtonBackground
import com.dynamiclayer.components.button.util.components.ButtonIcon
import com.dynamiclayer.components.button.util.components.ButtonLabel
import com.dynamiclayer.components.button.util.models.ButtonColors
import com.dynamiclayer.components.button.util.models.ButtonIcon
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.DefaultButtonStyles
import com.dynamiclayer.components.ui.theme.styles.button.ButtonStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun ButtonPreview() {
    ButtonsSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun ButtonsSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "button",
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
                        description = "You can edit the type with primary, secondary, tertiary or ghost parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary
                                    )
                                    Button(
                                        label = "Secondary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.secondary
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "Tertiary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.tertiary
                                    )
                                    Button(
                                        label = "Ghost",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.ghost
                                    )
                                }
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with xs, sm, md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary
                                    )
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.md,
                                        type = ButtonType.primary
                                    )


                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.sm,
                                        type = ButtonType.primary
                                    )
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.xs,
                                        type = ButtonType.primary
                                    )

                                }
                            }
                        })
                }
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
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary, state = ButtonState.default
                                    )
                                    Button(
                                        label = "Primary",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary, state = ButtonState.disabled
                                    )


                                }

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
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "iconLeft",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary,
                                        iconLeft = ButtonIcon.Drawable(R.drawable.ic_crop)
                                    )


                                }

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
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Button(
                                        label = "iconRight",
                                        onClick = { },
                                        size = ButtonSize.lg,
                                        type = ButtonType.primary,
                                        iconRight = ButtonIcon.Drawable(R.drawable.ic_crop)
                                    )


                                }

                            }
                        })
                }
            }
        })

}


@Composable
fun Button(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    state: ButtonState = ButtonState.default,
    iconLeft: ButtonIcon? = null,
    iconRight: ButtonIcon? = null,
    type: ButtonType = ButtonType.primary,
    size: ButtonSize = ButtonSize.lg,
) {
    val disable = state == ButtonState.disabled
    val buttonStyle =  when (type) {
        ButtonType.primary -> {
            when (size) {
                ButtonSize.lg -> DefaultButtonStyles.PrimaryButtons.lgStyle
                ButtonSize.md -> DefaultButtonStyles.PrimaryButtons.mdStyle
                ButtonSize.sm -> DefaultButtonStyles.PrimaryButtons.smStyle
                ButtonSize.xs -> DefaultButtonStyles.PrimaryButtons.xsStyle
            }
        }

        ButtonType.secondary -> {
            when (size) {
                ButtonSize.lg -> DefaultButtonStyles.SecondaryButtons.lgStyle
                ButtonSize.md -> DefaultButtonStyles.SecondaryButtons.mdStyle
                ButtonSize.sm -> DefaultButtonStyles.SecondaryButtons.smStyle
                ButtonSize.xs -> DefaultButtonStyles.SecondaryButtons.xsStyle
            }
        }

        ButtonType.tertiary -> {
            when (size) {
                ButtonSize.lg -> DefaultButtonStyles.TertiaryButtons.lgStyle
                ButtonSize.md -> DefaultButtonStyles.TertiaryButtons.mdStyle
                ButtonSize.sm -> DefaultButtonStyles.TertiaryButtons.smStyle
                ButtonSize.xs -> DefaultButtonStyles.TertiaryButtons.xsStyle
            }
        }

        ButtonType.ghost -> {
            when (size) {
                ButtonSize.lg -> DefaultButtonStyles.GhostButtons.lgStyle
                ButtonSize.md -> DefaultButtonStyles.GhostButtons.mdStyle
                ButtonSize.sm -> DefaultButtonStyles.GhostButtons.smStyle
                ButtonSize.xs -> DefaultButtonStyles.GhostButtons.xsStyle
            }
        }
    }

    ButtonStyle(
        label = label,
        onClick = onClick,
        leftIcon = iconLeft,
        rightIcon = iconRight,
        style = buttonStyle,
        disable = disable,
        modifier = modifier
    )
}

@Composable
private fun ButtonStyle(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    disable: Boolean,
    leftIcon: ButtonIcon?,
    rightIcon: ButtonIcon?,
    style: ButtonStyle
) {
    ButtonBackground(
        modifier = modifier
            .defaultMinSize(
                minWidth = style.width,
                minHeight = style.height
            ),
        enabled = !disable,
        shape = style.shape,
        border = style.border,
        colors = ButtonColors(
            containerColor = style.backgroundColor,
            contentColor = style.labelColor,
            disabledContainerColor = style.disableBackgroundColor,
            disabledContentColor = style.disableContentColor,
            rippleColor = style.rippleColor
        ),
        paddingValues = style.padding,
        onClick = onClick,
        content = {
            if (leftIcon != null) {
                ButtonIcon(
                    icon = leftIcon,
                    tint = style.labelColor
                )
                Spacer(Modifier.requiredWidth(style.gap))
            }

            ButtonLabel(
                label = label,
                labelColor = if (!disable) style.labelColor else style.disableContentColor,
                labelStyle = style.labelStyle, modifier  = Modifier.weight(1f, false)
            )

            if (rightIcon != null) {
                Spacer(Modifier.requiredWidth(style.gap))
                ButtonIcon(
                    icon = rightIcon,
                    tint = style.labelColor
                )
            }
        },
    )
}

