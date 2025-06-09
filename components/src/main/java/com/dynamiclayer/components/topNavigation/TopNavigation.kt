package com.dynamiclayer.components.topNavigation

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.avatar.Avatar
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarState
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.components.EmptyBox
import com.dynamiclayer.components.topNavigation.utils.components.TopNavigationIcon
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TopNavigationPreview() {
    TopNavigationSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun TopNavigationSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "top Navigation",
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
                        description = "You can edit the type with md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                TopNavigation(
                                    title = "Title",
                                    size = TopNavigationSize.md,
                                    iconLeft = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.chevron_left,
                                        onClick = {
                                            navController?.popBackStack()
                                        },  tint = ColorPalette.Black),
                                    iconRight = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.ic_camera,
                                        onClick = {},  tint = ColorPalette.Black)
                                )
                                TopNavigation(
                                    title = "Title",
                                    size = TopNavigationSize.lg,
                                    iconLeft = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.ic_chat,
                                        onClick = {},  tint = ColorPalette.Black),
                                    iconRight = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.ic_camera,
                                        onClick = {},  tint = ColorPalette.Black)
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
                                TopNavigation(
                                    title = "Title",
                                    size = TopNavigationSize.md,
                                    iconLeft = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.chevron_left,
                                        onClick = {
                                            navController?.popBackStack()
                                        },  tint = ColorPalette.Black)
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
                                TopNavigation(
                                    title = "Title",
                                    size = TopNavigationSize.md,
                                    iconRight = TopNavigationIconType.Drawable(
                                        drawable = R.drawable.ic_camera,
                                        onClick = {},  tint = ColorPalette.Black)
                                )

                            }
                        })
                }
            }
        })

}


@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    size: TopNavigationSize = TopNavigationSize.md,
    title: String,
    iconLeft: TopNavigationIconType? = null,
    iconRight: TopNavigationIconType? = null
) {
    if (size == TopNavigationSize.md) {
        TopNavigationTitleMd(
            modifier = modifier,
            title = title,
            leadingIcon = iconLeft,
            trailingIcon = iconRight
        )
    } else {
        TopNavigationTitleLg(
            modifier = modifier,
            title = title,
            leadingIcon = iconLeft,
            trailingIcon = iconRight
        )
    }
}

@Composable
private fun TopNavigationTitleMd(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: TopNavigationIconType?,
    trailingIcon: TopNavigationIconType?
) {
    Surface(
        modifier = modifier
            .sizeIn(minWidth = 375.dp),
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
                horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
            ) {
                if (leadingIcon != null)
                    TopNavigationIcon(icon = leadingIcon)
                else
                    EmptyBox()
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = AppTextWeight.text_base_semiBold,
                    color = ColorPalette.Black,
                    textAlign = TextAlign.Center, maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                if (trailingIcon != null)
                    TopNavigationIcon(icon = trailingIcon)
                else
                    EmptyBox()

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

@Composable
private fun TopNavigationTitleLg(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: TopNavigationIconType?,
    trailingIcon: TopNavigationIconType?
) {
    Surface(
        modifier = modifier
            .sizeIn(minWidth = 375.dp),
        color = ColorPalette.White,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(vertical = GeneralSpacing.p_16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = GeneralSpacing.p_16),
                text = title,
                style = AppTextWeight.text_4xl_bold,
                color = ColorPalette.Black, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            if (leadingIcon != null)
                TopNavigationIcon(icon = leadingIcon)

            if (trailingIcon != null)
                TopNavigationIcon(icon = trailingIcon)
        }
    }
}


@Composable
fun TopNavigation(
    modifier: Modifier = Modifier,
    title: String,
    avatar: AvatarType,
    iconLeft: TopNavigationIconType?,
    iconRight: TopNavigationIconType?,
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
                .sizeIn(minHeight = 56.dp), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (iconLeft != null)
                    TopNavigationIcon(icon = iconLeft)

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                ) {
                    Avatar(
                        size = AvatarSize.xs,
                        type = avatar,
                        state = AvatarState.Default,
                        onClick = null
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = title,
                        style = AppTextWeight.text_base_semiBold,
                        color = ColorPalette.Black,
                        textAlign = TextAlign.Start, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
                if (iconRight != null)
                    TopNavigationIcon(icon = iconRight)

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

