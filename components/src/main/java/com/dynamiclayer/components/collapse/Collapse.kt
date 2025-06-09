package com.dynamiclayer.components.collapse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.collapse.utils.CollapseState
import com.dynamiclayer.components.collapse.utils.CollapseType
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CheckListPreview() {
    CollapseSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun CollapseSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Collapse",
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
                        title = "Type",
                        description = "You can edit the type with title or default parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                Collapse(
                                    title = "Collapse title",
                                    type = CollapseType.title
                                )
                                Collapse(
                                    title = "Collapse default"
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default, expand or disabled parameter.",
                        content = {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                Collapse(
                                    title = "Collapse",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
                                    state = CollapseState.Default
                                )
                                Collapse(
                                    title = "Collapse",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
                                    state = CollapseState.Expand
                                )
                                Collapse(
                                    title = "Collapse disabled",
                                    state = CollapseState.disabled
                                )
                            }
                        })
                }
            }
        })

}

@Composable
fun Collapse(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    type: CollapseType = CollapseType.default,
    state: CollapseState = CollapseState.Default
) {
    var mutableState by remember {
        mutableStateOf(state)
    }
    Column(
        modifier = modifier
            .clickable(
                enabled = mutableState != CollapseState.disabled,
                onClick = {
                    mutableState =
                        if (mutableState == CollapseState.Default) CollapseState.Expand else CollapseState.Default
                }, indication = null, interactionSource = null
            )
            .background(color = if (type == CollapseType.title) ColorPalette.Grey.grey200 else ColorPalette.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = GeneralSpacing.p_12, horizontal = GeneralSpacing.p_16),
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    style = if (mutableState == CollapseState.disabled) AppTextStrike.text_base else AppTextWeight.text_base_medium,
                    color = if (mutableState == CollapseState.disabled) ColorPalette.Grey.grey500 else ColorPalette.Black
                )
                when (type) {
                    CollapseType.default -> {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(if (mutableState == CollapseState.Expand) 180f else 0f),
                            tint = if (mutableState == CollapseState.disabled) ColorPalette.Grey.grey500 else ColorPalette.Black
                        )
                    }

                    else -> {}
                }
            }
            AnimatedVisibility(
                visible = mutableState == CollapseState.Expand,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut(),
                label = "Animated Visibility", modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    style = AppTextWeight.text_sm_regular,
                    color = ColorPalette.Black
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )

    }
}