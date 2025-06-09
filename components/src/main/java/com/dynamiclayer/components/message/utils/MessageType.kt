package com.dynamiclayer.components.message.utils

import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.styles.message.messageStyles

enum class MessageType {
    message, ownMessage;

    val style
        get() = when (this) {
            message -> messageStyles(
                backgroundColor = ColorPalette.Grey.grey200,
                textColor = ColorPalette.Black,
                hourColor = ColorPalette.Black,
                responseBackgroundColor = ColorPalette.Grey.grey300,
                responseTextColor = ColorPalette.Black
            )

            ownMessage -> messageStyles(
                backgroundColor = ColorPalette.Grey.grey800,
                textColor = ColorPalette.White,
                hourColor = ColorPalette.White,
                responseBackgroundColor = ColorPalette.Grey.grey700,
                responseTextColor = ColorPalette.White

            )
        }
}