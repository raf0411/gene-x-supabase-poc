package com.dynamiclayer.components.rating

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
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

enum class RatingState(val state: Int) {
    one(1),
    two(2),
    three(3),
    four(4),
    five(5);
}

enum class RatingSize {
    sm, md, lg;

    val size: Int
        get() = when (this) {
            sm -> 16
            md -> 20
            lg -> 24
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun RatingPreview() {
    RatingSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun RatingSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Rating",
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
                        description = "You can edit the state with 1, 2, 3, 4 or 5 parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Rating(state = RatingState.one, size = RatingSize.md)
                                    Rating(state = RatingState.two, size = RatingSize.md)
                                    Rating(state = RatingState.three, size = RatingSize.md)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Rating(state = RatingState.four, size = RatingSize.md)
                                    Rating(state = RatingState.five, size = RatingSize.md)
                                    Rating(
                                        state = RatingState.five,
                                        size = RatingSize.md,
                                        modifier = Modifier.alpha(0f)
                                    )
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
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Rating(state = RatingState.one, size = RatingSize.lg)
                                    Rating(state = RatingState.three, size = RatingSize.md)
                                    Rating(state = RatingState.three, size = RatingSize.sm)
                                }

                            }
                        })
                }


            }
        })


}

@Composable
fun Rating(
    modifier: Modifier = Modifier,

    state: RatingState, size: RatingSize
) {
    val mutableState = remember(state) {
        mutableIntStateOf(state.state)
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_0)
    ) {
        repeat(5) {
            key(it) {
                Icon(
                    painter = painterResource(R.drawable.rating_star),
                    contentDescription = "Star $it",
                    modifier = Modifier
                        .size(size.size.dp)
                        .clickable(null, null, onClick = {
                            mutableState.intValue = it + 1
                        }),
                    tint = if (it < mutableState.intValue) ColorPalette.Yellow.color500 else ColorPalette.Grey.grey200
                )
            }
        }
    }

}