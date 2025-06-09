package com.dynamiclayer.components.button.util.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ButtonLabel(
    label: String,
    labelColor: Color,
    labelStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        color = labelColor,
        style = labelStyle,
        modifier = modifier, maxLines = 1, overflow = TextOverflow.Ellipsis
    )
}