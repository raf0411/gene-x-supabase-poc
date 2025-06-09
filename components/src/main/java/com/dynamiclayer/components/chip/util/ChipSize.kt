package com.dynamiclayer.components.chip.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.GeneralSpacing


enum class ChipSize {
    lg, md, sm;

    fun chipPadding(): PaddingValues = when (this) {
        lg -> PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_8)
        md -> PaddingValues(horizontal = GeneralSpacing.p_12, vertical = GeneralSpacing.p_4)
        sm -> PaddingValues(horizontal = GeneralSpacing.p_8, vertical = GeneralSpacing.p_2)
    }


    fun chipTextStyle(status: ChipState): TextStyle {
        return if (status == ChipState.active || status == ChipState.default) {
            when (this) {
                lg -> AppTextWeight.text_base_regular
                md -> AppTextWeight.text_sm_regular
                sm -> AppTextWeight.text_xs_regular
            }
        } else {
            when (this) {
                lg -> AppTextStrike.text_base
                md -> AppTextStrike.text_sm
                sm -> AppTextStrike.text_xs
            }
        }
    }

}
