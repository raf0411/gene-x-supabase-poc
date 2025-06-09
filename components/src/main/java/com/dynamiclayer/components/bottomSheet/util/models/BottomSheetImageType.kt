package com.dynamiclayer.components.bottomSheet.util.models

import androidx.compose.ui.layout.ContentScale

sealed class BottomSheetImageType {
    data class Bitmap(
        val bitmap: android.graphics.Bitmap,
        val contentScale: ContentScale = ContentScale.FillBounds
    ) : BottomSheetImageType()

    data class Url(
        val url: String,
        val contentScale: ContentScale = ContentScale.FillBounds
    ) : BottomSheetImageType()
}