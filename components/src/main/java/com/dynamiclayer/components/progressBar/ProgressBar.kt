package com.dynamiclayer.components.progressBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.progressBar.utils.ProgressBarState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun ProgressBarPreview() {
    ProgressBarSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun ProgressBarSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Progress Bar",
                size = TopNavigationSize.md,
                 iconLeft = TopNavigationIconType.Vector(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    onClick = {
                        navController?.popBackStack()
                    }, tint = ColorPalette.Black
                ),
                iconRight = TopNavigationIconType.Drawable(
                    drawable = R.drawable.dark_mode,
                    onClick = {
                        onDarkButtonClick?.invoke()
                    },
                    tint = ColorPalette.Black
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
            ) {
                item {
                    DetailContainer(
                        title = stringResource(id = R.string.state),
                        description = "You can edit the state with 1, 2, 3, 4 or 5 parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
                            ) {
                                ProgressBar(state = ProgressBarState.one)
                                ProgressBar(state = ProgressBarState.two)
                                ProgressBar(state = ProgressBarState.three)
                                ProgressBar(state = ProgressBarState.four)
                                ProgressBar(state = ProgressBarState.five)
                            }
                        })
                }
            }
        })

}

@Composable
fun ProgressBar(modifier: Modifier = Modifier, state: ProgressBarState = ProgressBarState.one) {
    Row(modifier = modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            Box(
                modifier = Modifier
                    .sizeIn(minWidth = 75.dp, minHeight = 4.dp)
                    .background(color = if (index + 1 <= state.count) ColorPalette.Voilet.color600 else ColorPalette.Grey.grey200)
            )
        }
    }
}


