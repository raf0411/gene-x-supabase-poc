package com.dynamiclayer.components.topNavigationMessage

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.dynamiclayer.components.avatarGroup.AvatarGroup
import com.dynamiclayer.components.avatarGroup.utils.AvatarGroupSize
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.components.TopNavigationIcon
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

sealed class TopNavigationMessageIconType {
    data class Vector(
        val imageVector: ImageVector,
        val tint: Color = Color.Unspecified,
        val onClick: () -> Unit = {}
    ) : TopNavigationMessageIconType()

    data class Drawable(
        @DrawableRes val drawable: Int,
        val tint: Color = Color.Unspecified,
        val onClick: () -> Unit = {}
    ) :
        TopNavigationMessageIconType()
}

enum class TopNavigationMessageType {
    single, group;
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopNavigationMessagePreview() {
    TopNavigationMessageSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun TopNavigationMessageSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Top Navigation Message",
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
                        description = "You can edit the type with single or group parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                TopNavigationMessage(
                                    title = "Chat name",
                                    type = TopNavigationMessageType.single,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ),
                                    iconLeft = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black),
                                    iconRight = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)
                                )
                                TopNavigationMessage(
                                    title = "Chat name",
                                    description = "Andrew Doe, Matthew Black, Lisa Smith",
                                    type = TopNavigationMessageType.group,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man)),
                                        AvatarType.Initials("2")
                                    ),
                                    iconLeft = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black),
                                    iconRight = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)
                                )
                            }
                        })
                }

                item {
                    DetailContainer(
                        title = "iconRight",
                        description = "You can edit the iconRight by removing the parameter",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                TopNavigationMessage(
                                    title = "Chat name",
                                    type = TopNavigationMessageType.single,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ),
                                    iconLeft = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop, tint = ColorPalette.Black),
                                    iconRight = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)
                                )
                                TopNavigationMessage(
                                    title = "Chat name",
                                    type = TopNavigationMessageType.single,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ),
                                    iconLeft = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)

                                )
                            }
                        })
                }

                item {
                    DetailContainer(
                        title = "iconLeft",
                        description = "You can edit the iconLeft by removing the parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                TopNavigationMessage(
                                    title = "Chat name",
                                    type = TopNavigationMessageType.single,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ),
                                    iconLeft = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black),

                                    iconRight = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)
                                )
                                TopNavigationMessage(
                                    title = "Chat name",
                                    type = TopNavigationMessageType.single,
                                    avatars = listOf(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ),
                                            iconRight = TopNavigationMessageIconType.Drawable(R.drawable.ic_crop,tint = ColorPalette.Black)
                                )
                            }
                        })
                }
            }
        })

}


@Composable
fun TopNavigationMessage(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    type: TopNavigationMessageType,
    avatars: List<AvatarType> = listOf(),
    iconLeft: TopNavigationMessageIconType? = null,
    iconRight: TopNavigationMessageIconType? = null,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets
) {
    Surface(
        modifier = modifier
            .sizeIn(minWidth = 375.dp)
            .windowInsetsPadding(windowInsets),
        color = ColorPalette.White,
        tonalElevation = 3.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (iconLeft != null)
                    TopNavigationIcon(icon = iconLeft.toNavigationIcon())

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                ) {
                    if (type == TopNavigationMessageType.group)
                        AvatarGroup(size = AvatarGroupSize.xs, items = avatars)
                    else
                        Avatar(
                            type = avatars.first(),
                            size = AvatarSize.xs,
                            state = AvatarState.Default
                        )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = title,
                            style = AppTextWeight.text_base_semiBold,
                            color = ColorPalette.Black,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (type == TopNavigationMessageType.group)
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = description,
                                style = AppTextWeight.text_xs_regular,
                                color = ColorPalette.Grey.grey500,
                                textAlign = TextAlign.Start,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                    }

                }
                if (iconRight != null)
                    TopNavigationIcon(icon = iconRight.toNavigationIcon())

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorPalette.Grey.grey200)
                    .align(Alignment.BottomCenter)
            )
        }

    }
}

private fun TopNavigationMessageIconType.toNavigationIcon(): TopNavigationIconType {
    return if (this is TopNavigationMessageIconType.Drawable) {
        TopNavigationIconType.Drawable(drawable, tint, onClick)
    } else {
        this as TopNavigationMessageIconType.Vector
        TopNavigationIconType.Vector(imageVector, tint, onClick)
    }
}