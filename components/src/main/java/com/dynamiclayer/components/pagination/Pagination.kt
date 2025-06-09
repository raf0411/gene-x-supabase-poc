package com.dynamiclayer.components.pagination

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.pagination.utils.PaginationMode
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun PaginationPreview() {
    PaginationSample()
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun PaginationSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Pagination",
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
                        title = "Count",
                        description = "You can edit the count with 2, 3, 4 or 5 parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Pagination(
                                        count = 2,
                                        mode = PaginationMode.dark,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })
                                    Pagination(
                                        count = 3,
                                        mode = PaginationMode.dark,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })
                                    Pagination(
                                        count = 4,
                                        mode = PaginationMode.dark,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })
                                    Pagination(
                                        count = 5,
                                        mode = PaginationMode.dark,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })
                                }
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Mode",
                        description = "You can edit the mode with dark or light parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    Pagination(
                                        count = 2,
                                        mode = PaginationMode.dark,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })
                                    Pagination(
                                        count = 2,
                                        mode = PaginationMode.light,
                                        currentPage = remember {
                                            mutableIntStateOf(0)
                                        },
                                        onDotClicked = {

                                        })

                                }
                            }
                        })
                }

            }
        })

}

@Composable
private fun PaginationDark(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: MutableState<Int>,
    onDotClicked: (Int) -> Unit,
) {
    PaginationContainer(
        modifier = modifier,
        pageCount = pageCount,
        currentPage = currentPage,
        onDotClicked = onDotClicked,
        activeColor = ColorPalette.Black,
        inactiveColor = ColorPalette.Black.copy(0.4f)
    )
}

@Composable
private fun PaginationLight(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: MutableState<Int>,
    onDotClicked: (Int) -> Unit,
) {
    PaginationContainer(
        modifier = modifier,
        pageCount = pageCount,
        currentPage = currentPage,
        onDotClicked = onDotClicked,
        activeColor = ColorPalette.White,
        inactiveColor = ColorPalette.White.copy(0.4f)
    )
}

@Composable
private fun PaginationContainer(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: MutableState<Int>,
    onDotClicked: (Int) -> Unit,
    activeColor: Color,
    inactiveColor: Color
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_4)) {
        for (pageIndex in 0 until pageCount) {
            val color by animateColorAsState(
                targetValue = if (currentPage.value == pageIndex) activeColor else inactiveColor,
                animationSpec = TweenSpec(durationMillis = 100), label = ""
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        currentPage.value = pageIndex
                        onDotClicked(pageIndex)
                    }, contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )

            }
        }
    }
}

@Composable
fun Pagination(
    modifier: Modifier = Modifier,
    count: Int,
    mode: PaginationMode,
    currentPage: MutableState<Int>,
    onDotClicked: (Int) -> Unit
) {
    if (mode == PaginationMode.light) {
        PaginationLight(modifier, count, currentPage, onDotClicked)
    } else {
        PaginationDark(modifier, count, currentPage, onDotClicked)
    }
}

