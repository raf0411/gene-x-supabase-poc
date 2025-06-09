package com.dynamiclayer.components.button.util.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.R
import com.dynamiclayer.components.button.util.models.ButtonIcon

@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    icon: ButtonIcon = ButtonIcon.Drawable(drawable = R.drawable.ic_default),
    contentDescription: String? = null,
    tint: Color,
) {
    when (icon) {
        is ButtonIcon.Vector -> Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(24.dp)
        )

        is ButtonIcon.Drawable -> Icon(
            painter = painterResource(icon.drawable),
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(24.dp)
        )
    }
}