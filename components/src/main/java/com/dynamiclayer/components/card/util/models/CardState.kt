package com.dynamiclayer.components.card.util.models

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette


enum class CardState {
    Default, Active, disabled;

    val titleStyle: TextStyle
        get() = when (this) {
            Default, Active -> AppTextWeight.text_sm_semiBold.copy(color = ColorPalette.Black)
            disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
        }
    val descriptionStyle: TextStyle
        get() = when (this) {
            Default, Active -> AppTextWeight.text_sm_regular.copy(color = ColorPalette.Black)
            disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
        }

    val cardBorder
        get() = when (this) {
            Default -> BorderStroke(1.dp, ColorPalette.Grey.grey200)
            Active -> BorderStroke(2.dp, ColorPalette.Black)
            disabled -> BorderStroke(1.dp, ColorPalette.Grey.grey200)
        }

}