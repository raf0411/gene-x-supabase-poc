package com.dynamiclayer.components.avatar.util.models

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.AvatarDimensions

enum class AvatarSize {
    lg, md, sm, xs;

    fun avatarSize(): Dp = when (this) {
        lg -> AvatarDimensions.LGAvatarSize
        md -> AvatarDimensions.MDAvatarSize
        sm -> AvatarDimensions.XMAvatarSize
        xs -> AvatarDimensions.XSAvatarSize
    }

    fun indicatorSize(): Dp = when (this) {
        lg -> AvatarDimensions.LGAvatarIndicatorSize
        md -> AvatarDimensions.MDAvatarIndicatorSize
        sm -> AvatarDimensions.SMAvatarIndicatorSize
        xs -> AvatarDimensions.XSAvatarIndicatorSize
    }


    fun textStyle(): TextStyle = when (this) {
        lg -> AppTextWeight.text_xl_semiBold
        md -> AppTextWeight.text_lg_semiBold
        sm -> AppTextWeight.text_sm_semiBold
        xs -> AppTextWeight.text_xs_semibold
    }
}
