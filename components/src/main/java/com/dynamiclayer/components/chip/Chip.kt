package com.dynamiclayer.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.chip.util.ChipSize
import com.dynamiclayer.components.chip.util.ChipState
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@Preview(showBackground = true)
@Composable
private fun ChipPreview() {
    ChipSample()
}

@Composable
fun ChipSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Chip",
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
                        title = "Amount",
                        description = "You can edit the amount with true or false parameter.",
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
                                    Chip(title = "Chip")
                                    Chip(title = "Chip", count = 10)
                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default, active or disabled parameter.",
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
                                    Chip(title = "Chip", state = ChipState.default)
                                    Chip(title = "Chip", state = ChipState.active)
                                    Chip(title = "Chip", state = ChipState.disabled)
                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the state with sm, md or lg parameter.",
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
                                    Chip(title = "Chip", size = ChipSize.lg)
                                    Chip(title = "Chip", size = ChipSize.md)
                                    Chip(title = "Chip", size = ChipSize.sm)


                                }

                            }
                        })
                }

            }
        })

}


@Composable
fun Chip(
    modifier: Modifier = Modifier,
    title: String,
    count: Int? = null,
    state: ChipState = ChipState.default,
    size: ChipSize = ChipSize.lg, onClick: (ChipState) -> Unit = {}
) {
    var chipStatus by remember {
        mutableStateOf(state)
    }
    val padding = size.chipPadding()
    val textStyle = size.chipTextStyle(chipStatus)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppRadius.rounded_full))
            .border(
                1.dp,
                color = ColorPalette.Grey.grey200,
                shape = RoundedCornerShape(AppRadius.rounded_full)
            )
            .background(if (chipStatus == ChipState.active) ColorPalette.Grey.grey50 else ColorPalette.White)
            .clickable(enabled = chipStatus != ChipState.disabled, interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                if (chipStatus == ChipState.default) {
                    chipStatus = ChipState.active
                } else if (chipStatus == ChipState.active) {
                    chipStatus = ChipState.default
                }
                onClick(chipStatus)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
        ) {

            Text(
                text = title,
                style = textStyle,
                color = if (chipStatus == ChipState.disabled) ColorPalette.Grey.grey500 else ColorPalette.Black
            )
            if (count != null) {
                Box(
                    modifier = Modifier
                        .background(
                            ColorPalette.Grey.grey200,
                            shape = RoundedCornerShape(AppRadius.rounded_full)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = count.toString(),
                        modifier
                            .padding(horizontal = GeneralSpacing.p_8),
                        style = AppTextWeight.text_xs_regular,
                        color = ColorPalette.Black
                    )
                }
            }

        }
    }

}