package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarState
import com.dynamiclayer.components.avatar.util.models.AvatarState.Default
import com.dynamiclayer.components.avatar.util.models.AvatarState.Offline
import com.dynamiclayer.components.avatar.util.models.AvatarState.Online
import com.dynamiclayer.components.ui.theme.ColorPalette

@Composable
fun BoxScope.AvatarIndicatorBox(
    size: AvatarSize,
    indicatorContent: AvatarState
) {
    if (indicatorContent != Default) {
        val background = getIndicatorColors(indicatorContent)
        Box(
            modifier = Modifier
                .size(size.indicatorSize())
                .clip(CircleShape)
                .border(2.dp, color = ColorPalette.White, shape = CircleShape)
                .padding(1.dp)
                .background(background, CircleShape)
                .align(Alignment.BottomEnd)
        )
    }
}

private fun getIndicatorColors(status: AvatarState): Color {
    return when (status) {
        Online -> ColorPalette.Green.color500
        Offline -> ColorPalette.Grey.grey200
        Default -> ColorPalette.Grey.grey200

    }
}
