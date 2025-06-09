package com.dynamiclayer.components.searchField.utils

import androidx.compose.foundation.layout.PaddingValues
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class SearchFieldSize {
    lg, md, sm;

    val padding get() = when(this){
        lg -> GeneralPaddings.p_16
        md -> PaddingValues(vertical = GeneralSpacing.p_12, horizontal = GeneralSpacing.p_16)
        sm -> PaddingValues(vertical = GeneralSpacing.p_8, horizontal = GeneralSpacing.p_16)

    }
}