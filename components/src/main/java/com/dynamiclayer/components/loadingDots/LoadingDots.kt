package com.dynamiclayer.components.loadingDots

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

sealed class LoadingDotsMode {
    data object dark : LoadingDotsMode()
    data object light : LoadingDotsMode()
    data class custom(val dotsColor: Color) : LoadingDotsMode()

    val color: Color
        get() = when (this) {
            dark -> ColorPalette.Black
            light -> ColorPalette.White
            is custom -> this.dotsColor
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LoadingDotsPreview() {
    LoadingDotsSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun LoadingDotsSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Loading Dots",
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
                        title = "Mode",
                        description = "You can change mode by changing LoadingDotsMode.dark and LoadingDotsMode.light",
                        content = {
                            LoadingDots(modifier = Modifier.padding(top = 20.dp))
                        })
                }


            }
        })

}

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    mode: LoadingDotsMode = LoadingDotsMode.dark
) {

    val repeatAnimation = rememberInfiniteTransition("")
    val middleAnimate by repeatAnimation.animateFloat(
        initialValue = 6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 300,
                delayMillis = 1,
                easing = EaseOut
            ), repeatMode = RepeatMode.Reverse
        ), label = "t"
    )
    val otherAnimate by repeatAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 300,
                delayMillis = 1,
                easing = EaseOut
            ), repeatMode = RepeatMode.Reverse
        ), label = "t"
    )


    Box(
        modifier = modifier
            .wrapContentHeight()

    ) {
        Box(
            modifier = Modifier
                .padding(top = otherAnimate.dp)
                .size(10.dp)
                .clip(CircleShape)
                .background(color = mode.color)
        )
        Box(
            modifier = Modifier
                .padding(top = middleAnimate.dp, start = 19.dp)
                .size(10.dp)
                .clip(CircleShape)
                .background(color = mode.color)
        )
        Box(
            modifier = Modifier
                .padding(top = otherAnimate.dp, start = 38.dp)
                .size(10.dp)
                .clip(CircleShape)
                .background(color = mode.color)
        )
    }
}

