package com.dynamiclayer.components.exampleView.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun DetailContainer(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
    ) {

        Text(text = title, style = AppTextWeight.text_lg_semiBold, color = ColorPalette.Black)
        Text(
            text = description,
            style = AppTextWeight.text_sm_regular,
            color = ColorPalette.Grey.grey500
        )
        content()
    }
}