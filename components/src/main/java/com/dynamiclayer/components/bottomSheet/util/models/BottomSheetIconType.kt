package com.dynamiclayer.components.bottomSheet.util.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.dynamiclayer.components.ui.theme.ColorPalette

sealed class BottomSheetIconType {
    data class Vector(
        val imageVector: ImageVector,
        val tint: Color = ColorPalette.Grey.grey300
    ) : BottomSheetIconType()

    data class Drawable(
        @DrawableRes val drawable: Int,
        val tint: Color = Color.Unspecified
    ) : BottomSheetIconType()
}
