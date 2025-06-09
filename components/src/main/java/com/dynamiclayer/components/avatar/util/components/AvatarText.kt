package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.ui.theme.ColorPalette

@Composable
fun BoxScope.AvatarText(contentMain: AvatarType.Initials, size: AvatarSize) {
    Text(
        text = if (contentMain.text.length > 2) contentMain.text.substring(
            0,
            2
        ) else contentMain.text,
        style = size.textStyle(),
        modifier = Modifier
            .align(Alignment.Center), color = ColorPalette.Grey.grey800
    )
}
