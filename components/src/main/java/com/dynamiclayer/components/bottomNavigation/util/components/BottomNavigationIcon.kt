package com.dynamiclayer.components.bottomNavigation.util.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.bottomNavigation.models.BottomNavigationIconType

@Composable
fun BottomNavigationIcon(
    modifier: Modifier = Modifier,
    icon: BottomNavigationIconType,
    contentDescription: String? = null,
) {
    when (icon) {
        is BottomNavigationIconType.Vector -> Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            modifier = modifier.size(24.dp)
        )

        is BottomNavigationIconType.Drawable -> Icon(
            painter = painterResource(icon.drawable),
            contentDescription = contentDescription,
            modifier = modifier.size(24.dp)
        )
    }
}