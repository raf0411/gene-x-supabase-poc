package com.dynamiclayer.components.badgeNotification

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.badgeNotification.util.BadgeNotificationSize
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@Composable
private fun BadgeNotificationLarge(
    modifier: Modifier = Modifier,
    text: String,
) {
    BadgeNotification(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = GeneralSpacing.p_8),
                text = text,
                style = AppTextWeight.text_xs_semibold.copy(
                    color = ColorPalette.OriginalWhite,
                    textAlign = TextAlign.Center
                ), maxLines = 1
            )
        }
    )
}

@Composable
private fun BadgeNotificationSmall(
    modifier: Modifier = Modifier
) {
    BadgeNotification(
        modifier = modifier.size(GeneralSpacing.p_8),
    )
}

@Composable
fun BadgeNotification(
    modifier: Modifier = Modifier,
    size: BadgeNotificationSize,
    text: String = ""
) {
    when (size) {
        BadgeNotificationSize.sm -> {
            BadgeNotificationSmall(
                modifier = modifier
            )
        }

        BadgeNotificationSize.md -> {
            BadgeNotificationLarge(
                modifier = modifier,
                text = text
            )
        }
    }
}

@Composable
private fun BadgeNotification(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(ColorPalette.Red.color500, RoundedCornerShape(AppRadius.rounded_full)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun PreviewComponents() {
    BadgeNotificationSample()
}

@ExperimentalMaterial3Api
@Composable
fun BadgeNotificationSample (navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Badge Notification",
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
                                   BadgeNotification(size = BadgeNotificationSize.md, text = "5")
                                   BadgeNotification(size = BadgeNotificationSize.sm)

                                }

                            }
                        })
                }

            }
        })
}


