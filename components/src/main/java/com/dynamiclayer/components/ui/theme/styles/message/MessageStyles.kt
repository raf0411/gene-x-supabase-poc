package com.dynamiclayer.components.ui.theme.styles.message

import androidx.compose.ui.graphics.Color
import com.dynamiclayer.components.ui.theme.ColorPalette

data class MessageStyles(
    val backgroundColor: Color = ColorPalette.Grey.grey200,
    val textColor: Color = ColorPalette.Black,
    val hourColor: Color = ColorPalette.Black,
    val responseBackgroundColor: Color = ColorPalette.Grey.grey700,
    val responseTextColor: Color = ColorPalette.White
)

fun messageStyles(
    backgroundColor: Color = ColorPalette.Grey.grey200,
    textColor: Color = ColorPalette.Black,
    hourColor: Color = ColorPalette.Black,
    responseBackgroundColor: Color = ColorPalette.Grey.grey700,
    responseTextColor: Color = ColorPalette.White
): MessageStyles {
    return MessageStyles(
        backgroundColor = backgroundColor,
        textColor = textColor,
        hourColor = hourColor,
        responseBackgroundColor = responseBackgroundColor,
        responseTextColor = responseTextColor
    )
}