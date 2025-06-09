package com.dynamiclayer.components.card.util.models

import androidx.compose.ui.unit.dp


enum class CardSize {
    lg, md;

    val height get() = when(this){
        lg-> 112.dp
        md-> 64.dp
    }

}