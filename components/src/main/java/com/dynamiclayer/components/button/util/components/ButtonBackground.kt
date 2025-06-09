package com.dynamiclayer.components.button.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import com.dynamiclayer.components.button.util.models.ButtonColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonBackground(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    shape: Shape,
    border: BorderStroke? = null,
    colors: ButtonColors,
    paddingValues: PaddingValues,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isBeingPressed by interactionSource.collectIsPressedAsState()
    val rippleColor = colors.rippleColor(enabled).value
    val containerColor = colors.containerColor(enabled).value
    val contentColor = colors.contentColor(enabled).value
    val shadowElevation = buttonElevation().shadowElevation(enabled, interactionSource).value
    val tonalElevation = buttonElevation().tonalElevation(enabled, interactionSource).value

    Surface(
        modifier = modifier
            .then(
                Modifier
                    .clip(shape = shape)
                    .combinedClickable(
                        onClick = onClick,
                        interactionSource = interactionSource,
                        indication = null,
                        role = Role.Button,
                        enabled = enabled
                    )

            ),
        shape = shape,
        color = if (isBeingPressed) rippleColor else containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        )
                        .padding(paddingValues),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}