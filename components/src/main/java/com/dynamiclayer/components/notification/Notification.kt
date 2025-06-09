package com.dynamiclayer.components.notification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.notification.NotificationType

@Preview
@Composable
private fun NotificationPreview() {
    NotificationsSample()
}

@Composable
fun NotificationsSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Notification",
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
                        description = "You can edit the type with success, error, warning or information parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Notification(
                                    title = "Success",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.success
                                )
                                Notification(
                                    title = "Error",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.error
                                )
                                Notification(
                                    title = "Warning",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.warning
                                )
                                Notification(
                                    title = "Information",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.info
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Icon",
                        description = "You can edit the icon with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Notification(
                                    title = "Success",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.success, icon = true
                                )
                                Notification(
                                    title = "Success",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
                                    type = NotificationType.success, icon = false
                                )

                            }
                        })
                }

            }
        })

}

@Composable
fun Notification(
    title: String,
    description: String,
    type: NotificationType = NotificationType.info,
    icon: Boolean = false,
    action: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = ColorPalette.White,
            shape = RoundedCornerShape(AppRadius.rounded_lg),
            border = BorderStroke(1.dp, ColorPalette.Grey.grey200)
        ) {
            Row(
                modifier = Modifier
                    .padding(GeneralPaddings.p_16)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = type.leadingIcon),
                    contentDescription = "", modifier = Modifier.size(24.dp).drawBehind {
                        if(type!=NotificationType.warning){
                            drawCircle(color = Color.White, radius = size.minDimension/4f)
                        }
                    }
                )

                Spacer(modifier = Modifier.requiredWidth(GeneralSpacing.p_8))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = title,
                            style = AppTextWeight.text_base_semiBold,
                            color = ColorPalette.Black
                        )
                    }
                    Spacer(modifier = Modifier.requiredHeight(GeneralSpacing.p_4))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = description,
                        style = AppTextWeight.text_base_regular,
                        color = ColorPalette.Black
                    )
                }
                Spacer(modifier = Modifier.requiredWidth(GeneralSpacing.p_8))
                if (icon)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification_close),
                        contentDescription = "",
                        modifier = Modifier.clickable(interactionSource = remember {
                            MutableInteractionSource()
                        }, indication = null) { action() }, tint = ColorPalette.Black
                    )
            }
        }
    }
}