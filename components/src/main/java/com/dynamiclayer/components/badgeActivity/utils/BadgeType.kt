package com.dynamiclayer.components.badgeActivity.utils

import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class BadgeType {
    commentLike, comment, like;

    val contentGap
        get() = when (this) {
            commentLike -> GeneralSpacing.p_8
            else -> 0.dp
        }
}