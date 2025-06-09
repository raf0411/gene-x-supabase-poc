package com.dynamiclayer.components.ui.theme.styles.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ButtonPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

data class ButtonStyle(
    val width: Dp,
    val height: Dp,
    val backgroundColor: Color,
    val disableBackgroundColor: Color,
    val disableContentColor: Color,
    val rippleColor: Color,
    val border: BorderStroke?,
    val labelColor: Color,
    val labelStyle: TextStyle,
    val padding: PaddingValues,
    val gap: Dp,
    val shape: Shape,
)

fun buttonStyle(
    size: ButtonSize,
    backgroundColor: Color,
    disableBackgroundColor: Color,
    disableContentColor: Color,
    rippleColor: Color,
    labelColor: Color,
    labelStyle: TextStyle,
    border: BorderStroke?
): ButtonStyle {
    return when (size) {
        ButtonSize.lg -> ButtonStyle(
            width = 139.dp,
            height = 56.dp,
            backgroundColor = backgroundColor,
            disableBackgroundColor = disableBackgroundColor,
            disableContentColor = disableContentColor,
            rippleColor = rippleColor,
            border = border,
            labelColor = labelColor,
            labelStyle = labelStyle,
            padding = ButtonPaddings.LgButtonPadding,
            gap = GeneralSpacing.p_8,
            shape = RoundedCornerShape(AppRadius.rounded_md)
        )

        ButtonSize.md -> ButtonStyle(
            width = 123.dp,
            height = 48.dp,
            backgroundColor = backgroundColor,
            disableBackgroundColor = disableBackgroundColor,
            disableContentColor = disableContentColor,
            rippleColor = rippleColor,
            border = border,
            labelColor = labelColor,
            labelStyle = labelStyle,
            padding = ButtonPaddings.MdButtonPadding,
            gap = GeneralSpacing.p_8,
            shape = RoundedCornerShape(AppRadius.rounded_md)
        )

        ButtonSize.sm -> ButtonStyle(
            width = 123.dp,
            height = 40.dp,
            backgroundColor = backgroundColor,
            disableBackgroundColor = disableBackgroundColor,
            disableContentColor = disableContentColor,
            rippleColor = rippleColor,
            border = border,
            labelColor = labelColor,
            labelStyle = labelStyle,
            padding = ButtonPaddings.SmButtonPadding,
            gap = GeneralSpacing.p_8,
            shape = RoundedCornerShape(AppRadius.rounded_md)

        )

        ButtonSize.xs -> ButtonStyle(
            width = 104.dp,
            height = 32.dp,
            backgroundColor = backgroundColor,
            disableBackgroundColor = disableBackgroundColor,
            disableContentColor = disableContentColor,
            rippleColor = rippleColor,
            border = border,
            labelColor = labelColor,
            labelStyle = labelStyle,
            padding = ButtonPaddings.XsButtonPadding,
            gap = GeneralSpacing.p_4,
            shape = RoundedCornerShape(AppRadius.rounded_md)
        )
    }
}
