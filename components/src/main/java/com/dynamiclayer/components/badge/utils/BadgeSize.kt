package com.dynamiclayer.components.badge.utils

import com.dynamiclayer.components.ui.theme.AppTextWeight

enum class BadgeSize {
    lg, md, sm;

    val textStyle
        get() = when (this) {
            lg -> AppTextWeight.text_base_semiBold
            md -> AppTextWeight.text_sm_semiBold
            sm -> AppTextWeight.text_xs_semibold
        }


}