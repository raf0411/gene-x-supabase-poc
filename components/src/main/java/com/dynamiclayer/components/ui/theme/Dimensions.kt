package com.dynamiclayer.components.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.inputField.util.InputFieldSize


object AvatarDimensions {
    val LGAvatarSize = 56.dp
    val MDAvatarSize = 48.dp
    val XMAvatarSize = 40.dp
    val XSAvatarSize = 32.dp

    val LGAvatarIndicatorSize = 18.dp
    val MDAvatarIndicatorSize = 16.dp
    val SMAvatarIndicatorSize = 14.dp
    val XSAvatarIndicatorSize = 12.dp
}












object InputFieldDimensions {
    private val inputFieldLgMinSize = 56.dp
    private val inputFieldMdMinSize = 48.dp

    private val inputFieldSmMinSize = 40.dp

    val inputFieldIconsSize = 24.dp

    @Composable
    internal fun dimensionsHeight(
        size: InputFieldSize,
    ): State<Dp> {
        val targetValue = when (size) {
            InputFieldSize.lg -> inputFieldLgMinSize
            InputFieldSize.md -> inputFieldMdMinSize
            InputFieldSize.sm -> inputFieldSmMinSize
        }
        return rememberUpdatedState(targetValue)
    }
}
