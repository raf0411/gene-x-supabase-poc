package com.dynamiclayer.components.ui.theme.styles.iconButton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.buttonIcon.util.models.IconButtonSize
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.GeneralPaddings

data class IconButtonStyle(
    val size: Dp,
    val padding: PaddingValues,
    val shape: Shape,
    val backgroundColor: Color,
    val rippleColor: Color,
    val border: BorderStroke,
    val iconColor: Color,
    val disableColor: Color,
    val disableContentColor: Color
)

fun iconButtonStyle(
    size: IconButtonSize,
    backgroundColor: Color,
    rippleColor: Color,
    iconColor: Color,
    border: BorderStroke, disableColor: Color,disableContentColor: Color
): IconButtonStyle {
    return when (size) {
        IconButtonSize.lg -> IconButtonStyle(
            size = 56.dp,
            padding = GeneralPaddings.p_16,
            shape = RoundedCornerShape(AppRadius.rounded_md),
            backgroundColor = backgroundColor,
            rippleColor = rippleColor,
            border = border,
            iconColor = iconColor, disableColor = disableColor, disableContentColor = disableContentColor
        )

        IconButtonSize.md -> IconButtonStyle(
            size = 48.dp,
            padding = GeneralPaddings.p_12,
            shape = RoundedCornerShape(AppRadius.rounded_md),
            backgroundColor = backgroundColor,
            rippleColor = rippleColor,
            border = border,
            iconColor = iconColor, disableColor = disableColor,disableContentColor = disableContentColor
        )

        IconButtonSize.sm -> IconButtonStyle(
            size = 40.dp,
            padding = GeneralPaddings.p_8,
            shape = RoundedCornerShape(AppRadius.rounded_md),
            backgroundColor = backgroundColor,
            rippleColor = rippleColor,
            border = border,
            iconColor = iconColor, disableColor = disableColor,disableContentColor = disableContentColor
        )

        IconButtonSize.xs -> IconButtonStyle(
            size = 32.dp,
            padding = GeneralPaddings.p_4,
            shape = RoundedCornerShape(AppRadius.rounded_md),
            backgroundColor = backgroundColor,
            rippleColor = rippleColor,
            border = border,
            iconColor = iconColor, disableColor = disableColor,disableContentColor = disableContentColor
        )
    }
}
