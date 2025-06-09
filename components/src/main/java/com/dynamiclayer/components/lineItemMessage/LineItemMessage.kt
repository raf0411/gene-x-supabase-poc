package com.dynamiclayer.components.lineItemMessage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.avatar.util.models.AvatarImage
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.avatarGroup.AvatarGroup
import com.dynamiclayer.components.avatarGroup.utils.AvatarGroupSize
import com.dynamiclayer.components.badgeNotification.BadgeNotification
import com.dynamiclayer.components.badgeNotification.util.BadgeNotificationSize
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

sealed class LineItemMessageType {
    data class single(val avatarType: AvatarType) : LineItemMessageType()
    data class group(val avatars: List<AvatarType>) : LineItemMessageType()
}

sealed class LineItemMessageState {
    data object default : LineItemMessageState()
    data object disabled : LineItemMessageState()
    data class new(val badgeSize: BadgeNotificationSize, val count: Int) : LineItemMessageState()

    val headerStyle: TextStyle
        get() = when (this) {
            is disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
            else -> AppTextWeight.text_sm_semiBold.copy(color = ColorPalette.Black)
        }
    val timeStyle: TextStyle
        get() = when (this) {
            is disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
            else -> AppTextWeight.text_sm_regular.copy(color = ColorPalette.Grey.grey500)
        }
    val messageStyle: TextStyle
        get() = when (this) {
            is disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
            is default -> AppTextWeight.text_sm_regular.copy(color = ColorPalette.Grey.grey500)
            is new -> AppTextWeight.text_sm_semiBold.copy(color = ColorPalette.Black)
        }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LineItemMessagePreview() {
    LineItemMessageSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun LineItemMessageSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Line Item Message",
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
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                LineItemMessage(
                                    name = "Name",
                                    message = "There is a new update: Your Reservation has been canceled.",
                                    time = "17:32",
                                    type = LineItemMessageType.single(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ), state = LineItemMessageState.default
                                )
                                LineItemMessage(
                                    name = "Name",
                                    message = "There is a new update: Your Reservation has been canceled.",
                                    time = "17:32",
                                    type = LineItemMessageType.group(
                                        listOf(
                                            AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man)),
                                            AvatarType.Image(AvatarImage.Drawable(R.drawable.img_girls))
                                        )
                                    ), state = LineItemMessageState.default
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default, new or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                LineItemMessage(
                                    name = "Name",
                                    message = "There is a new update: Your Reservation has been canceled.",
                                    time = "17:32",
                                    type = LineItemMessageType.single(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ), state = LineItemMessageState.default
                                )
                                LineItemMessage(
                                    name = "Name",
                                    message = "Hello Thomas, we are happy to meet you! Meeting Point See you in Lungard Plaza",
                                    time = "17:32",
                                    type = LineItemMessageType.single(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ), state = LineItemMessageState.new(badgeSize = BadgeNotificationSize.md,5)
                                )
                                LineItemMessage(
                                    name = "Name",
                                    message = "Hello Thomas, we are happy to meet you! Meeting Point See you in Lungard Plaza",
                                    time = "17:32",
                                    type = LineItemMessageType.single(
                                        AvatarType.Image(AvatarImage.Drawable(R.drawable.img_man))
                                    ), state = LineItemMessageState.disabled
                                )
                            }
                        })
                }


            }
        })

}


@Composable
fun LineItemMessage(
    modifier: Modifier = Modifier,
    name: String,
    message: String,
    time: String,
    type: LineItemMessageType,
    state: LineItemMessageState = LineItemMessageState.default
) {
    Box(
        modifier = modifier
            .background(ColorPalette.White)
            .wrapContentHeight()

    ) {
        Column(verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorPalette.Grey.grey200)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = GeneralSpacing.p_16)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
            ) {
                val avatarItems = if (type is LineItemMessageType.single) {
                    listOf(type.avatarType)
                } else {
                    type as LineItemMessageType.group
                    type.avatars
                }
                AvatarGroup(
                    items = avatarItems,
                    size = AvatarGroupSize.lg,
                    modifier = if (state is LineItemMessageState.disabled) Modifier.alpha(0.5f) else Modifier
                )
                Column(
                    modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                        GeneralSpacing.p_4
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = name, style = state.headerStyle,
                            modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = time, style = state.timeStyle)
                    }
                    Row {
                        Text(
                            text = message,
                            style = state.messageStyle,
                            modifier = Modifier.weight(1f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (state is LineItemMessageState.new) {
                            BadgeNotification(size = state.badgeSize, text = "${state.count}")
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorPalette.Grey.grey200)
            )

        }

    }
}

