package com.dynamiclayer.components.coachMark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.coachMark.util.models.CoachMarkDirection
import com.dynamiclayer.components.coachMark.util.models.CoachMarkStep
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.pagination.Pagination
import com.dynamiclayer.components.pagination.utils.PaginationMode
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CoachMarkPreview() {
    CoachMarkSample()
}

@ExperimentalMaterial3Api
@Composable
fun CoachMarkSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Coach Mark",
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
                        title = "Direction",
                        description = "You can edit the direction with bottom, top, left or right parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                CoachMark(
                                    items = listOf(
                                        CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        )
                                    ), direction = CoachMarkDirection.bottom
                                )
                                CoachMark(
                                    items = listOf(
                                        CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        )
                                    ), direction = CoachMarkDirection.top
                                )
                                CoachMark(
                                    items = listOf(
                                        CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        )
                                    ), direction = CoachMarkDirection.left
                                )
                                CoachMark(
                                    items = listOf(
                                        CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        ),CoachMarkStep(
                                            "Coachmark",
                                            "Pack my box with five dozen liquor jugs. How vexingly quick draft."
                                        )
                                    ), direction = CoachMarkDirection.right
                                )
                            }
                        })
                }

            }
        })

}


@Composable
fun CoachMark(
    direction: CoachMarkDirection = CoachMarkDirection.bottom,
    items: List<CoachMarkStep>
) {

    val coroutineScope = rememberCoroutineScope()
    val currentStep = remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { items.size })

    LaunchedEffect(pagerState.currentPage) {
        currentStep.intValue = pagerState.currentPage
    }



    CoachMarkLayout(direction = direction) {
        HorizontalPager(state = pagerState) { page ->
            StepLayout(step = items[page])
        }
        CoachMarkControls(
            stepsCount = items.size,
            currentStep = currentStep,
            pagerState = pagerState,
            onStepChange = { newPage ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(newPage)
                }
            }
        )
    }
}


@Composable
private fun CoachMarkLayout(
    direction: CoachMarkDirection = CoachMarkDirection.bottom,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    when (direction) {
        CoachMarkDirection.top -> CoachMarkLayoutTop(content = content)
        CoachMarkDirection.bottom -> CoachMarkLayoutBottom(content = content)
        CoachMarkDirection.left -> CoachMarkLayoutLeft(content = content)
        CoachMarkDirection.right -> CoachMarkLayoutRight(content = content)
    }
}

@Composable
private fun CoachMarkLayoutTop(content: @Composable ColumnScope.() -> Unit = {}) {

    val arrowDrawable = R.drawable.ic_coach_mark_arrow_up

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .offset(y = 1.dp)
                .size(24.dp, 12.dp),
            painter = painterResource(id = arrowDrawable),
            tint = ColorPalette.Grey.grey800,
            contentDescription = null
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(AppRadius.rounded_xl),
            color = ColorPalette.Grey.grey800,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }

        }
    }
}

@Composable
private fun CoachMarkLayoutBottom(content: @Composable ColumnScope.() -> Unit = {}) {

    val arrowDrawable = R.drawable.ic_coach_mark_arrow_down

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(AppRadius.rounded_xl),

            color = ColorPalette.Grey.grey800,

            ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }
        }
        Icon(
            modifier = Modifier
                .offset(y = (-1).dp)
                .size(24.dp, 12.dp),
            painter = painterResource(id = arrowDrawable),
            tint = ColorPalette.Grey.grey800,

            contentDescription = null
        )
    }
}

@Composable
private fun CoachMarkLayoutLeft(content: @Composable ColumnScope.() -> Unit = {}) {
    val layoutDirection = getLayoutDirection(CoachMarkDirection.left)

    val arrowDrawable = R.drawable.ic_coach_mark_arrow_left

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier
                    .offset(x = 1.dp)
                    .size(12.dp, 24.dp),
                painter = painterResource(id = arrowDrawable),
                tint = ColorPalette.Grey.grey800,

                contentDescription = null
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(AppRadius.rounded_xl),

                color = ColorPalette.Grey.grey800,

                ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
private fun CoachMarkLayoutRight(content: @Composable ColumnScope.() -> Unit = {}) {
    val layoutDirection = getLayoutDirection(CoachMarkDirection.right)

    val arrowDrawable = R.drawable.ic_coach_mark_arrow_right
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier
                    .offset(x = 1.dp)
                    .size(12.dp, 24.dp),
                painter = painterResource(id = arrowDrawable),
                tint = ColorPalette.Grey.grey800,

                contentDescription = null
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(AppRadius.rounded_xl),

                color = ColorPalette.Grey.grey800,

                ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

private fun getLayoutDirection(side: CoachMarkDirection): LayoutDirection =
    if (side == CoachMarkDirection.left) LayoutDirection.Ltr else LayoutDirection.Rtl


@Composable
private fun StepLayout(step: CoachMarkStep) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = GeneralSpacing.p_24, vertical = GeneralSpacing.p_16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = step.title,
            style = AppTextWeight.text_base_semiBold,
            color = ColorPalette.White,
        )
        Spacer(modifier = Modifier.height(GeneralSpacing.p_8))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = step.description,
            style = AppTextWeight.text_sm_regular,

            color = ColorPalette.White,
        )
    }
}


@Composable
private fun CoachMarkControls(
    stepsCount: Int,
    currentStep: MutableState<Int>,
    pagerState: PagerState,
    onStepChange: (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = GeneralSpacing.p_24)
            .padding(bottom = GeneralSpacing.p_16),
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Pagination(
            count = stepsCount, mode = PaginationMode.light,
            currentPage = currentStep,
            onDotClicked = onStepChange, modifier = Modifier.weight(1f)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                label = "Back",
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                modifier = Modifier.width(58.dp),
                type = ButtonType.tertiary,
                size = ButtonSize.xs
            )
            Button(
                label = "Next",
                onClick = {
                    if (pagerState.currentPage < pagerState.pageCount) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                type = ButtonType.primary,
                size = ButtonSize.xs,
                modifier = Modifier.width(58.dp)
            )

        }
    }
}