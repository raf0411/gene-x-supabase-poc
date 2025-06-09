package com.dynamiclayer.components.segmentControl.util.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.SegmentedControlItemStyles
import com.dynamiclayer.components.ui.theme.styles.segmentedControl.SegmentedControlItemColors

@Composable
fun RowScope.SegmentedControlItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    label: @Composable (() -> Unit),
    onClick: () -> Unit,
    colors: SegmentedControlItemColors = SegmentedControlItemStyles.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val backgroundColor by colors.backgroundColor(selected = selected)


    val styledLabel: @Composable () -> Unit = @Composable {
        val textColor by colors.textColor(selected = selected, enabled = enabled)
        CompositionLocalProvider(LocalContentColor provides textColor) {
            if (enabled) {
                ProvideTextStyle(AppTextWeight.text_base_regular, content = label)
            } else {
                ProvideTextStyle(AppTextStrike.text_base, content = label)
            }
        }
    }

    Surface(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, enabled = enabled
            ) { onClick() }
            .padding(GeneralPaddings.p_4)
            .fillMaxSize()
            .weight(1f),
        shape = RoundedCornerShape(AppRadius.rounded_full),
        color = backgroundColor,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxSize()
                .padding(vertical = GeneralSpacing.p_4, horizontal = GeneralSpacing.p_16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            styledLabel()
        }
    }
}