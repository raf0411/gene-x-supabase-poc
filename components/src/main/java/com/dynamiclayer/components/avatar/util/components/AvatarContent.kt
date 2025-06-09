package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.dynamiclayer.components.avatar.util.models.AvatarSize
import com.dynamiclayer.components.avatar.util.models.AvatarType

@Composable
fun BoxScope.AvatarContent(mainContent: AvatarType, size: AvatarSize) {
    when (mainContent) {
        is AvatarType.Image -> AvatarUserImage(mainContent)
        is AvatarType.Initials -> AvatarText(mainContent, size)
        is AvatarType.Icon -> AvatarIcon(mainContent)
    }
}
