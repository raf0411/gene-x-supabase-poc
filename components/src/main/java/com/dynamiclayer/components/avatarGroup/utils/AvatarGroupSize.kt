package com.dynamiclayer.components.avatarGroup.utils

import com.dynamiclayer.components.avatar.util.models.AvatarSize

enum class AvatarGroupSize {
    lg, xs;

    fun avatarSize(): AvatarSize = when (this) {
        lg -> AvatarSize.lg

        xs -> AvatarSize.xs
    }
}
