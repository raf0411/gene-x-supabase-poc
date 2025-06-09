package com.dynamiclayer.components.searchField.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette

enum class SearchFieldState {
    default, active, filled, disabled;

    val containerColor
        get() = when (this) {
            default -> ColorPalette.Grey.grey50
            else -> ColorPalette.Grey.grey100
        }

    val border
        get() = when (this) {
            active -> BorderStroke(2.dp, ColorPalette.Black)
            else -> BorderStroke(0.dp, Color.Transparent)
        }

    val contentColor
        get() = when (this) {
            active, filled -> ColorPalette.Black
            else -> ColorPalette.Grey.grey500
        }
    val textStyle
        get() = when (this) {
            disabled -> AppTextStrike.text_base
            else -> AppTextWeight.text_base_regular
        }
}