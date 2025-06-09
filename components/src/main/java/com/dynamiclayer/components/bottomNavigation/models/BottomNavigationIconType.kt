package com.dynamiclayer.components.bottomNavigation.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationIconType {
    data class Vector(
        val imageVector: ImageVector
    ) : BottomNavigationIconType()
    data class Drawable(
        @DrawableRes val drawable: Int
    ) :
        BottomNavigationIconType()
}
