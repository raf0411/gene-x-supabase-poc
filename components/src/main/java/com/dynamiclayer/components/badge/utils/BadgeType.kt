package com.dynamiclayer.components.badge.utils

import com.dynamiclayer.components.ui.theme.ColorPalette

enum class BadgeType {
    Default, Success, Error;

    val contentColor
        get() = when (this) {
            Default -> ColorPalette.Voilet.color600
            Success -> ColorPalette.Green.color700
            Error -> ColorPalette.Red.color600
        }

    val containerColor
        get() = when (this) {
            Default -> ColorPalette.Voilet.color100
            Success -> ColorPalette.Green.color100
            Error -> ColorPalette.Red.color100
        }
}