package com.dynamiclayer.components.avatar.util.models

import androidx.annotation.DrawableRes

sealed class AvatarImage {
    data class Drawable(@DrawableRes val resId: Int) : AvatarImage()
    data class Bitmap(val bitmap: android.graphics.Bitmap) : AvatarImage()
    data class Url(val url: String) : AvatarImage()
}