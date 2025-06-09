package com.dynamiclayer.components.buttonIcon.util.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import com.dynamiclayer.components.ButtonIcon.util.models.IconButtonIconType
import com.dynamiclayer.components.ui.theme.styles.iconButton.IconButtonStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconButtonLayout(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    icon: IconButtonIconType,
    style: IconButtonStyle,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isBeingPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .size(style.size)
            .clip(style.shape)
            .border(
                border = style.border,
                shape = style.shape,
            )
            .background(
                color =if(enabled) if (isBeingPressed) style.rippleColor else style.backgroundColor else style.disableColor,
                shape = style.shape,
            )
            .combinedClickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                enabled = enabled
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButtonIcon(
            modifier = Modifier.padding(style.padding),
            icon = icon,
            tint =if(enabled)  style.iconColor else style.disableContentColor
        )
    }

}