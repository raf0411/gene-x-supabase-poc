package com.dynamiclayer.components.teaser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class TeaserState {
    default, disabled;
}

enum class TeaserSize {
    md, lg;
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TeaserPreview() {
    TeaserSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun TeaserSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Teaser",
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
                        title = "Size",
                        description = "You can edit the size with md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = GeneralSpacing.p_16),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        GeneralSpacing.p_16,
                                        alignment = Alignment.CenterHorizontally
                                    )
                                ) {
                                    Teaser(
                                        modifier = Modifier.weight(1f),
                                        size = TeaserSize.md,
                                        title = "Title",
                                        description = "Description"
                                    )
                                    Box(modifier = Modifier.weight(1f))
                                }

                                Teaser(
                                    size = TeaserSize.lg,
                                    title = "Title",
                                    description = "Description"
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default or disabled parameter.",
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                horizontalArrangement = Arrangement.spacedBy(
                                    GeneralSpacing.p_16,
                                    alignment = Alignment.CenterHorizontally
                                )
                            ) {
                                Teaser(
                                    modifier = Modifier.weight(1f),
                                    size = TeaserSize.md,
                                    title = "Title",
                                    description = "Description"
                                )
                                Teaser(
                                    modifier = Modifier.weight(1f),
                                    size = TeaserSize.md,
                                    title = "Title",
                                    description = "Description", state = TeaserState.disabled
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Description",
                        description = "You can remove the description by removing this parameter",
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                horizontalArrangement = Arrangement.spacedBy(
                                    GeneralSpacing.p_16,
                                    alignment = Alignment.CenterHorizontally
                                )
                            ) {
                                Teaser(
                                    modifier = Modifier.weight(1f),
                                    size = TeaserSize.md,
                                    title = "Title",
                                    description = "Description"
                                )
                                Teaser(
                                    modifier = Modifier.weight(1f),
                                    size = TeaserSize.md,
                                    title = "Title"
                                )
                            }
                        })
                }

            }
        })


}

@Composable
fun Teaser(
    modifier: Modifier = Modifier,
    size: TeaserSize,
    title: String,
    description: String="",
    imgRes: Int = R.drawable.img,
    state: TeaserState = TeaserState.default
) {

    val isEnable = state == TeaserState.default
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppRadius.rounded_xl))
            .border(1.dp, ColorPalette.Grey.grey200, RoundedCornerShape(AppRadius.rounded_xl))
            .background(color = ColorPalette.White)
    ) {
        if (size == TeaserSize.md) {
            TeaserMd(
                title = title,
                description = description,
                imgRes = imgRes,
                isEnable = isEnable
            )
        } else {
            TeaserLg(
                title = title,
                description = description,
                imgRes = imgRes,
                isEnable = isEnable
            )
        }
    }
}

@Composable
private fun TeaserMd(
    title: String, description: String, imgRes: Int, isEnable: Boolean
) {
    val color = if (isEnable) ColorPalette.Black else ColorPalette.Grey.grey500
    Column {
        Image(
            painter = painterResource(id = imgRes),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp), contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = GeneralSpacing.p_12, horizontal = GeneralSpacing.p_16)
        ) {
            Text(
                text = title,
                style = if (isEnable) AppTextWeight.text_sm_semiBold else AppTextStrike.text_sm,
                color = color, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = if (isEnable) AppTextWeight.text_sm_regular else AppTextStrike.text_sm,
                    color = color,maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Composable
private fun TeaserLg(
    title: String,
    description: String,
    imgRes: Int = R.drawable.img,
    isEnable: Boolean
) {
    val color = if (isEnable) ColorPalette.Black else ColorPalette.Grey.grey500
    Row(
        modifier = Modifier.padding(GeneralPaddings.p_16),
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
    ) {
        Image(
            painter = painterResource(id = imgRes),
            contentDescription = title,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(120.dp)
                .clip(RoundedCornerShape(AppRadius.rounded_md)), contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                style = if (isEnable) AppTextWeight.text_sm_semiBold else AppTextStrike.text_sm,
                color = color,
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = if (isEnable) AppTextWeight.text_sm_regular else AppTextStrike.text_sm,
                    color = color
                )
            }
        }
    }

}