package com.dynamiclayer.components.ui.theme.styles.notification

import com.dynamiclayer.components.R

enum class NotificationType(val leadingIcon: Int) {
    info(R.drawable.s_information), error(R.drawable.ic_error), warning(R.drawable.s_warning), success(
        R.drawable.s_success
    );
}
