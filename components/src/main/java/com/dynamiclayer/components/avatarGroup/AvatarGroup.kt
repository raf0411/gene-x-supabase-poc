package com.dynamiclayer.components.avatarGroup

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.avatar.Avatar
import com.dynamiclayer.components.avatar.util.models.AvatarImage
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarState
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.avatarGroup.utils.AvatarGroupSize
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AvatarGroupPreview() {
    AvatarGroupSample()
}

@ExperimentalMaterial3Api
@Composable
fun AvatarGroupSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Avatar Group",
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
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_man
                                                )
                                            ),AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_girls
                                                )
                                            )
                                        ), size = AvatarGroupSize.lg
                                    )
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Initials(
                                               "Aa"
                                            ),AvatarType.Initials(
                                                "Aa"
                                            )
                                        ), size = AvatarGroupSize.lg
                                    )
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Icon(
                                                R.drawable.profile
                                            ),AvatarType.Icon(
                                                R.drawable.profile
                                            )
                                        ), size = AvatarGroupSize.lg,
                                    )
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_man
                                                )
                                            ),AvatarType.Initials("2")
                                        ), size = AvatarGroupSize.lg
                                    )

                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with the xs or lg parameter.",
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
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_man
                                                )
                                            ),AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_girls
                                                )
                                            )
                                        ), size = AvatarGroupSize.lg
                                    )
                                    AvatarGroup(
                                        items = listOf(
                                            AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_man
                                                )
                                            ),AvatarType.Image(
                                                image = AvatarImage.Drawable(
                                                    R.drawable.img_girls
                                                )
                                            )
                                        ), size = AvatarGroupSize.xs
                                    )

                                }

                            }
                        })
                }

            }
        })
}

@Composable
fun AvatarGroup(
    modifier: Modifier = Modifier,
    items: List<AvatarType>,
    size: AvatarGroupSize
) {
    Box {
        items.forEachIndexed { index, avatarMainContent ->
            val avatarStyle = remember {
                if (index == items.lastIndex && items.size > 2) {
                    avatarStyle(
                        size = size.avatarSize(),
                        mainContent = AvatarType.Initials("${items.size - 1}"),
                        status = AvatarState.Default,
                        onClick = null,
                        backgroundColor = ColorPalette.Grey.grey200,
                        borderColor = ColorPalette.White
                    )
                } else {
                    avatarStyle(
                        size = size.avatarSize(),
                        mainContent = avatarMainContent,
                        status = AvatarState.Default,
                        onClick = null,
                        backgroundColor = ColorPalette.White
                    )
                }
            }
            Avatar(
                state = avatarStyle.status,
                size = avatarStyle.size,
                type = avatarStyle.mainContent,
                backgroundColor = avatarStyle.backgroundColor,
                borderColor = avatarStyle.borderColor,
                onClick = avatarStyle.onClick,
                modifier = modifier
                    .then(
                        if (index > 0) {
                            if (size == AvatarGroupSize.lg) {
                                Modifier.padding(
                                    start = GeneralSpacing.p_16,
                                    top = GeneralSpacing.p_16
                                )
                            } else {
                                Modifier.padding(
                                    start = GeneralSpacing.p_8,
                                    top = GeneralSpacing.p_8
                                )
                            }
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }

}

private data class AvatarGroupStyle(
    val size: AvatarSize,
    val backgroundColor: Color = ColorPalette.White,
    val borderColor: Color = ColorPalette.Grey.grey200,
    val mainContent: AvatarType,
    val status: AvatarState,
    val onClick: (() -> Unit)? = null
)

private fun avatarStyle(
    size: AvatarSize,
    backgroundColor: Color = ColorPalette.White,
    borderColor: Color = ColorPalette.Grey.grey200,
    mainContent: AvatarType,
    status: AvatarState,
    onClick: (() -> Unit)? = null
): AvatarGroupStyle {
    return AvatarGroupStyle(
        size = size,
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        mainContent = mainContent,
        status = status,
        onClick = onClick
    )
}
