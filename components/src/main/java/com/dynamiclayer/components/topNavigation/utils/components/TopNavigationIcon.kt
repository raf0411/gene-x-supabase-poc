package com.dynamiclayer.components.topNavigation.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType

@Composable
fun EmptyBox(){
    Box(
        modifier = Modifier
            .size(56.dp)
    )
}
@Composable
fun TopNavigationIcon(
    modifier: Modifier = Modifier,
    icon: TopNavigationIconType,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
) {
    when (icon) {
        is TopNavigationIconType.Vector ->
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clickable(enabled = true, interactionSource = remember {
                        MutableInteractionSource()
                    }, indication = null) { icon.onClick.invoke() },
                contentAlignment = alignment
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon.imageVector,
                        tint = icon.tint,
                        contentDescription = contentDescription,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }

        is TopNavigationIconType.Drawable ->
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clickable(enabled = true, interactionSource = remember {
                        MutableInteractionSource()
                    }, indication = null) { icon.onClick.invoke() },
                contentAlignment = alignment
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(icon.drawable),
                        tint = icon.tint,
                        contentDescription = contentDescription,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
    }
}