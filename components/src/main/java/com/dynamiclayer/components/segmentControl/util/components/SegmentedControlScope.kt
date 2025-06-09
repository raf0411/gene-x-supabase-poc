package com.dynamiclayer.components.segmentControl.util.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dynamiclayer.components.bottomNavigation.util.components.BottomNavigationDsl
import com.dynamiclayer.components.ui.theme.styles.SegmentedControlItemStyles

@BottomNavigationDsl
class SegmentedControlScope(
    private val items: MutableList<@Composable (RowScope.(Boolean) -> Unit)>
) {
    fun item(
        modifier: Modifier = Modifier,
        state: SegmentedControlState = SegmentedControlState.default,
        label: String,
        onClick: () -> Unit
    ) {
        items.add {selected->
            val resolvedColors = SegmentedControlItemStyles.colors()
            val resolvedInteractionSource = remember { MutableInteractionSource() }

            SegmentedControlItem(
                modifier = modifier,
                selected = selected,
                enabled = state==SegmentedControlState.default,
                label = {
                    Text(label)
                },
                onClick = onClick,
                colors = resolvedColors,
                interactionSource = resolvedInteractionSource
            )
        }
    }
}

