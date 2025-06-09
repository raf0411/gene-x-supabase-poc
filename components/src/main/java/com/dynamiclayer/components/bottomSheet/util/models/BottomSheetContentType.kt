package com.dynamiclayer.components.bottomSheet.util.models

sealed class BottomSheetContentType {
    data object None : BottomSheetContentType()
    data class Icon(val icon:  BottomSheetIconType) : BottomSheetContentType()
    data class Image(val image: BottomSheetImageType) : BottomSheetContentType()
}