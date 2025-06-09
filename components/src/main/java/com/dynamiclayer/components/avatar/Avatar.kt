package com.dynamiclayer.components.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.avatar.util.components.AvatarImageBox
import com.dynamiclayer.components.avatar.util.components.AvatarIndicatorBox
import com.dynamiclayer.components.avatar.util.models.AvatarImage
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarState
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AvatarPreview() {
    AvatarsSample()
}

@ExperimentalMaterial3Api
@Composable
fun AvatarsSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Avatar",
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
                        description = "You can edit the type with the icon, image or initials parameter.",
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
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Icon(R.drawable.profile),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Initials("Aa"),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Default
                                    )
                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with the xs, sm, md or lg parameter.",
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
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.md,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.sm,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.xs,
                                        state = AvatarState.Default

                                    )

                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with the default, offline or online parameter.",
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
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Default

                                    )
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Offline

                                    )
                                    Avatar(
                                        type = AvatarType.Image(image = AvatarImage.Drawable(R.drawable.img_man)),
                                        size = AvatarSize.lg,
                                        state = AvatarState.Online

                                    )
                                }

                            }
                        })
                }

            }
        })
}

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    type: AvatarType,
    size: AvatarSize,
    state: AvatarState,
    backgroundColor: Color = ColorPalette.White,
    borderColor: Color = ColorPalette.Grey.grey200,
    onClick: (() -> Unit)? = null
) {
    Box(modifier = modifier) {
        AvatarImageBox(
            size,
            type,
            onClick,
            backgroundColor,
            borderColor = borderColor
        )
        AvatarIndicatorBox(size, state)
    }
}
