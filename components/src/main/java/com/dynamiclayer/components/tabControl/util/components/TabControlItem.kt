package com.dynamiclayer.components.tabControl.util.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.badgeNotification.BadgeNotification
import com.dynamiclayer.components.badgeNotification.util.BadgeNotificationSize
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.TabControlItemStyles
import com.dynamiclayer.components.ui.theme.styles.tabControl.TabControlItemColors

@Composable
fun RowScope.TabControlItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    badge: String? = null,
    label: @Composable (() -> Unit),
    onClick: () -> Unit,
    colors: TabControlItemColors = TabControlItemStyles.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val animatedBorderColor by animateColorAsState(
        targetValue = if (selected) ColorPalette.Black
        else ColorPalette.Grey.grey200,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val borderModifier = modifier.drawBehind {
        val strokeWidth = 2.dp.toPx()
        val y = size.height - strokeWidth / 2
        drawLine(
            color = animatedBorderColor,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidth
        )
    }

    val styledLabel: @Composable () -> Unit = @Composable {
        val style = if (enabled) AppTextWeight.text_base_semiBold else AppTextStrike.text_base
        val textColor by colors.textColor(selected = selected, enabled = enabled)
        CompositionLocalProvider(LocalContentColor provides textColor) {
            ProvideTextStyle(style, content = label)
        }
    }

    Box(
        modifier = borderModifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { if (enabled) onClick() }
            .fillMaxSize()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = GeneralSpacing.p_8,
                vertical = GeneralSpacing.p_16
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
        ) {
            styledLabel()
            if(badge!=null){
                BadgeNotification(size = BadgeNotificationSize.md, text  = badge)
            }
        }
    }
}