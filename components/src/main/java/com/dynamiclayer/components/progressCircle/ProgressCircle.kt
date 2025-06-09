package com.dynamiclayer.components.progressCircle

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Retention(AnnotationRetention.SOURCE)
@IntRange(from = 0, to = 4)
annotation class ProgressCircleState {
    companion object {
        const val zero = 0
        const val one = 1
        const val two = 2
        const val three = 3
        const val four = 4
    }
}

enum class ProgressCircleSize {
    sm, md, lg;

    val size: Int
        get() = when (this) {
            sm -> 25
            md -> 40
            lg -> 56
        }
    val border: Int
        get() = when (this) {
            sm -> 4
            md -> 6
            lg -> 8
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun ProgressCirclePreview() {
    ProgressCircleSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun ProgressCircleSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Progress Circle",
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
                        title = "State",
                        description = "You can edit the state with 0, 1, 2, 3 or 4 parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ProgressCircle(
                                        state = ProgressCircleState.zero,
                                        size = ProgressCircleSize.lg
                                    )
                                    ProgressCircle(
                                        state = ProgressCircleState.one,
                                        size = ProgressCircleSize.lg
                                    )
                                    ProgressCircle(
                                        state = ProgressCircleState.two,
                                        size = ProgressCircleSize.lg
                                    )
                                    ProgressCircle(
                                        state = ProgressCircleState.three,
                                        size = ProgressCircleSize.lg
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ProgressCircle(state =  ProgressCircleState.four, size = ProgressCircleSize.lg)
                                }
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with sm, md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    ProgressCircle(
                                        state = ProgressCircleState.zero,
                                        size = ProgressCircleSize.sm
                                    )
                                    ProgressCircle(
                                        state = ProgressCircleState.zero,
                                        size = ProgressCircleSize.md
                                    )
                                    ProgressCircle(
                                        state = ProgressCircleState.zero,
                                        size = ProgressCircleSize.lg
                                    )
                                }
                            }
                        })
                }


            }
        })


}

@Composable
fun ProgressCircle(
    modifier: Modifier = Modifier,
    @ProgressCircleState
    state: Int, size: ProgressCircleSize
) {
    val progress = remember(state) {
        ((state * 100) / 4) / 100f
    }
    CircularProgressIndicator(
        modifier = modifier.size(size.size.dp),
        strokeWidth = size.border.dp,
        strokeCap = StrokeCap.Round,
        trackColor = ColorPalette.Grey.grey200,
        color = ColorPalette.Voilet.color500,
        progress = {
            progress
        }
    )
}