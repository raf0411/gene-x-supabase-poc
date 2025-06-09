package com.dynamiclayer.components.popOverFilter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.checkbox.Checkbox
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class PopOverFilterState {
    default, expand;
}

@Stable
data class PopOverFilterItem(val title: String, var isActive: Boolean = false)

@Stable
data class PopOverButton(
    val btnTertiaryTitle: String,
    val onTertiaryClick: () -> Unit,
    val btnSecondaryTitle: String,
    val onSecondaryClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun PopOverFilterPreview() {
    PopOverFilterSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun PopOverFilterSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Pop Over Filter",
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
                        description = "You can edit the state with default or expand parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                PopOverFilter(
                                    headline = "Headline",
                                    items = listOf(
                                        PopOverFilterItem("Item 1"),
                                        PopOverFilterItem("Item 2"),
                                        PopOverFilterItem("Item 3"),
                                        PopOverFilterItem("Item 4"),
                                        PopOverFilterItem("Item 5"),
                                        PopOverFilterItem("Item 6")
                                    ),
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }
                                )
                                PopOverFilter(
                                    headline = "Headline",
                                    items = listOf(
                                        PopOverFilterItem("Item 1"),
                                        PopOverFilterItem("Item 2"),
                                        PopOverFilterItem("Item 3"),
                                        PopOverFilterItem("Item 4"),
                                        PopOverFilterItem("Item 5"),
                                        PopOverFilterItem("Item 6")
                                    ),
                                    buttons = PopOverButton(
                                        btnTertiaryTitle = "Button",
                                        onTertiaryClick = {},
                                        btnSecondaryTitle = "Button",
                                        onSecondaryClick = {}), onDismiss = {

                                    }, state = PopOverFilterState.expand
                                )
                            }
                        })
                }


            }
        })


}

@Composable
fun PopOverFilter(
    modifier: Modifier = Modifier,
    state: PopOverFilterState = PopOverFilterState.default,
    headline: String,
    items: List<PopOverFilterItem>,
    buttons: PopOverButton,
    onDismiss: () -> Unit
) {
    var mState by remember(state) {
        mutableStateOf(state)
    }
    Column(
        modifier = modifier
            .width(343.dp)
            .clip(RoundedCornerShape(AppRadius.rounded_xl))
            .border(
                1.dp, color = ColorPalette.Grey.grey200, shape = RoundedCornerShape(
                    AppRadius.rounded_xl
                )
            )
            .background(color = ColorPalette.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)

                    .clickable(null, null, onClick = onDismiss)
                   , contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.cross),
                    tint = ColorPalette.Black,
                    modifier = Modifier
                        .padding(GeneralPaddings.p_16)
                        .size(24.dp),
                    contentDescription = "Close",
                )
            }
            Text(
                text = headline,
                style = AppTextWeight.text_base_semiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = GeneralSpacing.p_16)
                    .weight(1f),
                textAlign = TextAlign.Center, color = ColorPalette.Black
            )
            Box(
                modifier = Modifier
                    .size(56.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorPalette.Grey.grey200)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = GeneralSpacing.p_8)
        ) {

            (if (items.size > 3 && mState == PopOverFilterState.default) items.subList(
                0,
                3
            ) else items).forEach { item ->
                var isChecked by remember(item.isActive) {
                    mutableStateOf(item.isActive)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isChecked = !isChecked
                            item.isActive = isChecked
                        }
                        .padding(vertical = GeneralSpacing.p_8, horizontal = GeneralSpacing.p_16),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                ) {
                    Text(
                        item.title,
                        style = AppTextWeight.text_base_regular,
                        color = ColorPalette.Black, modifier = Modifier.weight(1f)
                    )
                    Checkbox(isActive = isChecked, onCheckChange = {
                        isChecked = it
                        item.isActive = it
                    })
                }
            }

            if (items.size > 3) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(
                            vertical = GeneralSpacing.p_8,
                            horizontal = GeneralSpacing.p_16
                        )
                        .clickable(null, null, onClick = {
                            mState =
                                if (mState == PopOverFilterState.default) PopOverFilterState.expand else PopOverFilterState.default
                        })
                ) {
                    Text(
                        if (mState == PopOverFilterState.default) "Show all" else "Hide all",
                        style = AppTextWeight.text_base_semiBold,
                        color = ColorPalette.Black
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_line_item_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(if (mState == PopOverFilterState.default) 90f else 270f), tint = ColorPalette.Black
                    )
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = ColorPalette.Grey.grey200)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_16),
            horizontalArrangement = Arrangement.spacedBy(
                GeneralSpacing.p_16
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.weight(1f),
                label = buttons.btnTertiaryTitle,
                onClick = buttons.onTertiaryClick,
                type = ButtonType.tertiary
            )
            Button(
                modifier = Modifier.weight(1f),
                label = buttons.btnSecondaryTitle,
                onClick = buttons.onSecondaryClick,
                type = ButtonType.secondary
            )
        }
    }
}