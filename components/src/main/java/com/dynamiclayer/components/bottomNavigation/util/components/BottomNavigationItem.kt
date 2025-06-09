package com.dynamiclayer.components.bottomNavigation.util.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dynamiclayer.components.badgeNotification.BadgeNotification
import com.dynamiclayer.components.badgeNotification.util.BadgeNotificationSize
import com.dynamiclayer.components.bottomNavigation.BottomBarBadge
import com.dynamiclayer.components.bottomNavigation.models.BottomNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    enabled: Boolean = true,
    icon: BottomNavigationIconType,
    label: @Composable() (() -> Unit)? = null,
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    badge: BottomBarBadge?
) {
    val currentColor by remember(selected) {
        derivedStateOf {
            if (selected) ColorPalette.Black else ColorPalette.Grey.grey400
        }
    }
    val styledIcon = @Composable {
        Box {
            CompositionLocalProvider(
                LocalContentColor provides currentColor, content = {
                    BottomNavigationIcon(
                        icon = icon
                    )
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, enabled = enabled
            ) { onClick() }
            .fillMaxSize()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(GeneralPaddings.p_8),
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                ConstraintLayout {
                    val (iconRef, badgeRef) = createRefs()
                    Box(modifier = Modifier.constrainAs(iconRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                        styledIcon()
                    }
                    Box(modifier = Modifier.constrainAs(badgeRef) {
                        top.linkTo(iconRef.top)
                        start.linkTo(iconRef.start)
                        this.translationX = 15.dp
                        if (badge?.badgeSize == BadgeNotificationSize.md)
                            this.translationY = (-2).dp
                    }) {
                        if (badge != null) {
                            if (badge.badgeSize == BadgeNotificationSize.md) {
                                BadgeNotification(
                                    size = BadgeNotificationSize.md,
                                    text = badge.number.toString()
                                )
                            } else {
                                BadgeNotification(
                                    size = BadgeNotificationSize.sm
                                )
                            }
                        }
                    }
                }

            }
            if (label != null) {
                CompositionLocalProvider(LocalContentColor provides currentColor) {
                    ProvideTextStyle(AppTextWeight.text_xs_bold, content = label)
                }
            }
        }


    }
}