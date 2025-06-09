package com.dynamiclayer.components.lineItem.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState

sealed class LineItemType {
    data object default : LineItemType()
    data class icon(@DrawableRes val resId: Int) : LineItemType()
    data object checkbox : LineItemType()
    data object radioButton : LineItemType()
    data class toggle(val value:MutableState<Boolean>) : LineItemType()
    data class button(val text:String,val onButtonClick:()->Unit={}) : LineItemType()
}