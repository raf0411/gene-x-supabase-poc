package com.dynamiclayer.components.lineItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.ButtonState
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType
import com.dynamiclayer.components.checkbox.Checkbox
import com.dynamiclayer.components.checkbox.CheckboxState
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.lineItem.utils.LineItemState
import com.dynamiclayer.components.lineItem.utils.LineItemType
import com.dynamiclayer.components.radioButton.RadioButton
import com.dynamiclayer.components.radioButton.utils.RadioButtonState
import com.dynamiclayer.components.toggle.Toggle
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun LineItemPreview() {
    LineItemSample()
}

@Composable
private fun LineItemView(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    state: LineItemState = LineItemState.default, content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(ColorPalette.White)
            .wrapContentHeight()
            .width(375.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorPalette.Grey.grey200)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = GeneralSpacing.p_16)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = title,
                        style = state.headerStyle
                    )
                    if (description.isNotEmpty()) {
                        Text(
                            text = description,
                            style = state.descriptionStyle
                        )
                    }
                }
                content()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = ColorPalette.Grey.grey200)
            )

        }

    }
}

@Composable
fun LineItem(
    modifier: Modifier = Modifier,
    type: LineItemType = LineItemType.default,
    state: LineItemState = LineItemState.default,
    title: String,
    description: String = ""
) {
    when (type) {

        is LineItemType.default -> {
            LineItemView(title = title,
                description = description, state = state, modifier = modifier, content = {

                })

        }

        is LineItemType.icon -> {
            LineItemView(title = title,
                description = description, state = state, modifier = modifier, content = {
                    Image(
                        painter = painterResource(id = type.resId),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        colorFilter = if (state == LineItemState.disabled) null else androidx.compose.ui.graphics.ColorFilter.tint(
                            ColorPalette.Grey.grey500
                        )
                    )
                })
        }

        is LineItemType.button -> {
            LineItemView(
                title = title,
                description = description, state = state, content = {
                    Button(
                        label = type.text,
                        onClick = type.onButtonClick,
                        modifier = Modifier.size(52.dp, 32.dp),
                        type = ButtonType.tertiary,
                        size = ButtonSize.xs,
                        state = if (state != LineItemState.disabled) ButtonState.default else ButtonState.disabled
                    )
                }
            )
        }

        is LineItemType.toggle -> {
            LineItemView(
                title = title,
                description = description, state = state, content = {
                    Toggle(toggle = type.value)

                }
            )
        }

        is LineItemType.checkbox -> {
            LineItemView(
                title = title,
                description = description, state = state, content = {
                    val isChecked = remember {
                        mutableStateOf(false)
                    }
                    Checkbox(
                        isActive = isChecked.value,
                        state = if (state == LineItemState.disabled) CheckboxState.disabled else CheckboxState.default,
                        onCheckChange = {
                            isChecked.value = it
                        })

                }
            )
        }

        is LineItemType.radioButton -> {
            LineItemView(
                title = title,
                description = description, state = state, content = {
                    val isSelected = remember {
                        mutableStateOf(false)
                    }
                    RadioButton(
                        isSelected = isSelected.value,
                        onClick = {
                            isSelected.value = !isSelected.value
                        },
                        state = if (state == LineItemState.disabled) RadioButtonState.disabled else RadioButtonState.default,
                    )

                }
            )
        }


    }

}

@ExperimentalMaterial3Api
@Composable
fun LineItemSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Line Item",
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
                        description = "You can edit the type with default, toggle, button, checkbox, radioButton or chevron parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.default
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.toggle(value = remember {
                                        mutableStateOf(false)
                                    })
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.button(text = "Add",onButtonClick = {})
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.checkbox
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.radioButton
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.icon(R.drawable.ic_line_item_arrow)
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.default,
                                    state = LineItemState.default
                                )
                                LineItem(
                                    title = "Title",
                                    description = "Description",
                                    type = LineItemType.default,
                                    state = LineItemState.disabled
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Description",
                        description = "You can edit the description by removing the description parameter",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                LineItem(
                                    title = "Title",
                                    type = LineItemType.default
                                )
                            }
                        })
                }

            }
        })

}


