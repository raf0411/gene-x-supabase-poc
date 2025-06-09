package com.dynamiclayer.components.topNavigation.utils.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TopNavigationIconType {
    data class Vector(
        val imageVector: ImageVector,
        val tint: Color = Color.Unspecified,
        val onClick: () -> Unit = {}
    ) : TopNavigationIconType()

    data class Drawable(
        @DrawableRes val drawable: Int,
        val tint: Color =  Color.Unspecified,
        val onClick: () -> Unit = {}
    ) :
        TopNavigationIconType()
}