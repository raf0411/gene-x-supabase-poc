package com.dynamiclayer.components.bottomNavigation.util.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.dynamiclayer.components.bottomNavigation.BottomBarBadge
import com.dynamiclayer.components.bottomNavigation.models.BottomNavigationIconType

@BottomNavigationDsl
class BottomNavigationScope(
    private val items: MutableList<@Composable RowScope.() -> Unit>
) {
    fun item(
        selected: Boolean,
        enabled: Boolean = true,
        icon: BottomNavigationIconType,
        label: @Composable (() -> Unit)? = null,
        onClick: () -> Unit,
        interactionSource: MutableInteractionSource? = null,
        badge: BottomBarBadge? = null
    ) {
        items.add {
            val resolvedInteractionSource =
                interactionSource ?: remember { MutableInteractionSource() }
            BottomNavigationItem(
                selected = selected,
                enabled = enabled,
                icon = icon,
                label = label,
                onClick = onClick,
                interactionSource = resolvedInteractionSource, badge = badge
            )
        }
    }
}

