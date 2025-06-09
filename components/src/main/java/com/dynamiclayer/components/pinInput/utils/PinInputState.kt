package com.dynamiclayer.components.pinInput.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette

enum class PinInputState {
    default, active, filled, error, success, disabled;

    val containerColor
        get() = when (this) {
            success -> ColorPalette.Green.color50
            error -> ColorPalette.Red.color50
            else -> ColorPalette.Grey.grey100
        }

    val border
        get() = when (this) {
            success -> BorderStroke(2.dp, ColorPalette.Green.color700)
            error -> BorderStroke(2.dp, ColorPalette.Red.color500)
            active -> BorderStroke(2.dp, ColorPalette.Black)
            else -> BorderStroke(0.dp, Color.Transparent)
        }

    val contentColor
        get() = when (this) {
            success -> ColorPalette.Green.color700
            error -> ColorPalette.Red.color500
            disabled -> ColorPalette.Grey.grey500
            else -> ColorPalette.Black
        }
    val textStyle
        get() = when (this) {
            disabled -> AppTextWeight.text_base_regular
            else -> AppTextWeight.text_base_semiBold

        }
}