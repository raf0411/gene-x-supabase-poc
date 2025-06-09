package com.dynamiclayer.components.buttonIcon.util.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.R
import com.dynamiclayer.components.ButtonIcon.util.models.IconButtonIconType

@Composable
fun IconButtonIcon(
    modifier: Modifier = Modifier,
    icon: IconButtonIconType = IconButtonIconType.Drawable(drawable = R.drawable.ic_default),
    contentDescription: String? = null,
    tint: Color,
) {
    when (icon) {
        is IconButtonIconType.Vector -> Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(24.dp)
        )

        is IconButtonIconType.Drawable -> Icon(
            painter = painterResource(icon.drawable),
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(24.dp)
        )
    }
}