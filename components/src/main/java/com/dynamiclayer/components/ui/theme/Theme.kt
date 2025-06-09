package com.dynamiclayer.components.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.dynamiclayer.components.ui.theme.ColorPalette.Green
import com.dynamiclayer.components.ui.theme.ColorPalette.Grey
import com.dynamiclayer.components.ui.theme.ColorPalette.Purple
import com.dynamiclayer.components.ui.theme.ColorPalette.Red
import com.dynamiclayer.components.ui.theme.ColorPalette.Yellow
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val darkColorScheme = darkColorScheme(
    primary = Purple.color700,
    onPrimary = ColorPalette.White,
    primaryContainer = Purple.color800,
    onPrimaryContainer = Purple.color200,
    secondary = Green.color700,
    onSecondary = ColorPalette.White,
    secondaryContainer = Green.color800,
    onSecondaryContainer = Green.color200,
    tertiary = Yellow.color700,
    onTertiary = ColorPalette.White,
    tertiaryContainer = Yellow.color800,
    onTertiaryContainer = Yellow.color200,
    error = Red.color700,
    onError = ColorPalette.White,
    errorContainer = Red.color800,
    onErrorContainer = Red.color200,
    background = Grey.grey900,
    onBackground = ColorPalette.White,
    surface = Grey.grey800,
    onSurface = ColorPalette.White,
    surfaceVariant = Grey.grey700,
    onSurfaceVariant = Grey.grey300,
    outline = Grey.grey500,
    inverseOnSurface = Grey.grey200,
    inverseSurface = Grey.grey300,
    surfaceTint = Purple.color700,
)


private val lightColorScheme = lightColorScheme(
    primary = Purple.color500,
    onPrimary = ColorPalette.White,
    primaryContainer = Purple.color100,
    onPrimaryContainer = Purple.color700,
    secondary = Green.color500,
    onSecondary = ColorPalette.Black,
    secondaryContainer = Green.color100,
    onSecondaryContainer = Green.color700,
    tertiary = Yellow.color500,
    onTertiary = ColorPalette.Black,
    tertiaryContainer = Yellow.color100,
    onTertiaryContainer = Yellow.color700,
    error = Red.color500,
    onError = ColorPalette.Black,
    errorContainer = Red.color100,
    onErrorContainer = Red.color700,
    background = Grey.grey50,
    onBackground = ColorPalette.Black,
    surface = Grey.grey100,
    onSurface = ColorPalette.Black,
    surfaceVariant = Grey.grey200,
    onSurfaceVariant = Grey.grey600,
    outline = Grey.grey400,
    inverseOnSurface = Grey.grey900,
    inverseSurface = Grey.grey800,
    surfaceTint = Purple.color500,
)

@Composable
fun ComponentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = ColorPalette.Transparent,
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
