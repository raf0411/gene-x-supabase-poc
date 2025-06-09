package com.dynamiclayer.components.button.util.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ButtonIcon {
    data class Vector(val imageVector: ImageVector) : ButtonIcon()
    data class Drawable(@DrawableRes val drawable: Int) : ButtonIcon()
}
