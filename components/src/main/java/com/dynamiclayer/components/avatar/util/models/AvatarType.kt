package com.dynamiclayer.components.avatar.util.models

import androidx.annotation.DrawableRes

sealed class AvatarType {
    data class Icon(@DrawableRes val icon: Int) : AvatarType()
    data class Image(val image: AvatarImage) : AvatarType()
    data class Initials(val text: String) : AvatarType()
}