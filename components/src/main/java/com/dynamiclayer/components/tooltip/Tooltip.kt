package com.dynamiclayer.components.tooltip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.Text
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class TooltipDirection {
    top, bottom, left, right;
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TooltipPreview() {
    TooltipSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun TooltipSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Tooltip",
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
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Tooltip(direction = TooltipDirection.bottom, label = "Tooltip")
                                    Tooltip(direction = TooltipDirection.top, label = "Tooltip")
                                    Tooltip(direction = TooltipDirection.left, label = "Tooltip")
                                }
                                Tooltip(direction = TooltipDirection.right, label = "Tooltip")

                            }
                        })
                }

            }
        })


}


@Composable
fun Tooltip(
    modifier: Modifier = Modifier,
    direction: TooltipDirection,
    label: String
) {
    if (direction == TooltipDirection.left || direction == TooltipDirection.right) {
        Row(modifier = modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
            if (direction == TooltipDirection.left) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_hr),
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(180f)
                        .offset(x = (-1).dp), tint = ColorPalette.Grey.grey800
                )

            }
            TooltipContent(
                label
            )

            if (direction == TooltipDirection.right) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_hr),
                    contentDescription = "",
                    modifier = Modifier
                        .offset(x = (-1).dp), tint = ColorPalette.Grey.grey800

                )
            }
        }
    } else {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (direction == TooltipDirection.top) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_up),
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(180f)
                        .offset(y = (-1).dp), tint = ColorPalette.Grey.grey800
                )

            }
            TooltipContent(
                label
            )
            if (direction == TooltipDirection.bottom) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_up),
                    contentDescription = "",
                    modifier = Modifier.offset(y = (-1).dp),
                    tint = ColorPalette.Grey.grey800
                )
            }
        }
    }
}

@Composable
private fun TooltipContent(
    label: String
) {
    Box(
        modifier = Modifier
            .sizeIn(maxWidth = 140.dp)
            .background(
                color = ColorPalette.Grey.grey800,
                shape = RoundedCornerShape(AppRadius.rounded_md)
            )
            .padding(vertical = GeneralSpacing.p_8, horizontal = GeneralSpacing.p_16),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = AppTextWeight.text_base_regular,
            color = ColorPalette.White,
            textAlign = TextAlign.Center
        )


    }
}

