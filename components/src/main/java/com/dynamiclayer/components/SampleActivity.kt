@file:Suppress("KotlinConstantConditions")

package com.dynamiclayer.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dynamiclayer.components.exampleView.navigation.MainNavGraph
import com.dynamiclayer.components.ui.theme.toggleDarkMode


class SampleActivity : ComponentActivity() {

    private var isDarkMode: Boolean = false

    companion object {
        var onDarkButtonClick: (() -> Unit?)? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        isDarkMode = prefs.getBoolean("isDark", false)
        setContent { MainNavGraph() }
        onDarkButtonClick = {
            updateDarkMode()
        }

    }

    private fun updateDarkMode() {
        isDarkMode = !isDarkMode

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit().putBoolean("isDark", isDarkMode).apply()

        toggleDarkMode(isDarkMode)
        recreate()
    }

}























