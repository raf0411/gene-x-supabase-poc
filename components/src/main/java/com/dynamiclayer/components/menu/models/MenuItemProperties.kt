package com.dynamiclayer.components.menu.models

data class MenuItemProperties(
    val label: String,
    val onClick: () -> Unit
)