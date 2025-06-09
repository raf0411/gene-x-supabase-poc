package com.dynamiclayer.components.tabControl.util.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dynamiclayer.components.bottomNavigation.util.components.BottomNavigationDsl
import com.dynamiclayer.components.ui.theme.styles.TabControlItemStyles

@BottomNavigationDsl
class TabControlScope(
    private val items: MutableList<@Composable RowScope.(Boolean) -> Unit>
) {
    fun item(
        label:String,
        state: TabControlState = TabControlState.default,
        badge: String? = null,
        onClick: () -> Unit
    ) {
        items.add {selected->
            val resolvedColors = TabControlItemStyles.colors()
            val resolvedInteractionSource =
               remember { MutableInteractionSource() }

            TabControlItem(
                modifier = Modifier,
                selected = selected,
                enabled = state==TabControlState.default,
                label = {
                    Text(label)
                },
                badge = badge,
                onClick = onClick,
                colors = resolvedColors,
                interactionSource = resolvedInteractionSource
            )
        }
    }
}

