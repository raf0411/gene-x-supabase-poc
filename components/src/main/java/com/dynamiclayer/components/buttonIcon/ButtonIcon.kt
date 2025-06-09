package com.dynamiclayer.components.buttonIcon

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.buttonIcon.util.components.IconButtonLayout
import com.dynamiclayer.components.ButtonIcon.util.models.IconButtonIconType
import com.dynamiclayer.components.buttonIcon.util.models.ButtonIconState
import com.dynamiclayer.components.buttonIcon.util.models.IconButtonSize
import com.dynamiclayer.components.buttonIcon.util.models.IconButtonType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.DefaultIconButtonStyles

@Preview(showBackground = true)
@Composable
private fun IconButtonPreview() {
    IconButtonSample()
}


@Composable
fun IconButtonSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Button Icon",
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
                        description = "You can edit the type with primary, secondary or tertiary parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.secondary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.tertiary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )

                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with the sm or md parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.md,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.sm,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.xs,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop)
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
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop),
                                        state = ButtonIconState.default
                                    )
                                    ButtonIcon(
                                        onClick = { },
                                        size = IconButtonSize.lg,
                                        type = IconButtonType.primary,
                                        icon = IconButtonIconType.Drawable(R.drawable.ic_crop),
                                        state = ButtonIconState.disabled
                                    )
                                }

                            }
                        })
                }

            }
        })
}


@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    size: IconButtonSize,
    type: IconButtonType,
    state: ButtonIconState = ButtonIconState.default,
    icon: IconButtonIconType
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val style = remember {

        when (type) {
            IconButtonType.primary -> {
                when (size) {
                    IconButtonSize.lg -> DefaultIconButtonStyles.PrimaryIconButton.lgStyle
                    IconButtonSize.md -> DefaultIconButtonStyles.PrimaryIconButton.mdStyle
                    IconButtonSize.sm -> DefaultIconButtonStyles.PrimaryIconButton.smStyle
                    IconButtonSize.xs -> DefaultIconButtonStyles.PrimaryIconButton.xsStyle
                }
            }

            IconButtonType.secondary -> {
                when (size) {
                    IconButtonSize.lg -> DefaultIconButtonStyles.SecondaryIconButton.lgStyle
                    IconButtonSize.md -> DefaultIconButtonStyles.SecondaryIconButton.mdStyle
                    IconButtonSize.sm -> DefaultIconButtonStyles.SecondaryIconButton.smStyle
                    IconButtonSize.xs -> DefaultIconButtonStyles.SecondaryIconButton.xsStyle
                }
            }

            IconButtonType.tertiary -> {
                when (size) {
                    IconButtonSize.lg -> DefaultIconButtonStyles.TertiaryIconButton.lgStyle
                    IconButtonSize.md -> DefaultIconButtonStyles.TertiaryIconButton.mdStyle
                    IconButtonSize.sm -> DefaultIconButtonStyles.TertiaryIconButton.smStyle
                    IconButtonSize.xs -> DefaultIconButtonStyles.TertiaryIconButton.xsStyle
                }
            }
        }
    }
    IconButtonLayout(
        modifier = modifier,
        onClick = onClick,
        enabled = state== ButtonIconState.default,
        icon = icon,
        style = style,
        interactionSource = interactionSource
    )
}


