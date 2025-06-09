package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.avatar.util.models.AvatarType
import com.dynamiclayer.components.ui.theme.ColorPalette

@Composable
fun BoxScope.AvatarIcon(mainContent: AvatarType.Icon) {
    Icon(
        painter = painterResource(mainContent.icon),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.Center)
            .size(24.dp), tint =  ColorPalette.Black
    )
}
