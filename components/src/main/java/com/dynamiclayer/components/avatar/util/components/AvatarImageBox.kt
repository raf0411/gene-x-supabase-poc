package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarType

@Composable
fun AvatarImageBox(
    size: AvatarSize,
    mainContent: AvatarType,
    onClick: (() -> Unit)?,
    backgroundColor: Color,
    borderColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size.avatarSize())
            .clip(CircleShape)
            .background(backgroundColor)
            .border(1.dp, borderColor, CircleShape)
            .clickable(onClick != null, onClick = onClick ?: {})
    ){

        AvatarContent(mainContent, size)
    }
}