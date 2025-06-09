package com.dynamiclayer.components.lineItem.utils

import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette

enum class LineItemState {
    default, disabled;

    val headerStyle
        get() = when (this) {
            default -> AppTextWeight.text_base_semiBold.copy(color = ColorPalette.Black)
            disabled -> AppTextStrike.text_base.copy(color = ColorPalette.Grey.grey500)
        }
    val descriptionStyle
        get() = when (this) {
            default -> AppTextWeight.text_sm_regular.copy(color = ColorPalette.Black)
            disabled -> AppTextStrike.text_sm.copy(color = ColorPalette.Grey.grey500)
        }
}