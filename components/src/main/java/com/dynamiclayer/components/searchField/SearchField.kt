package com.dynamiclayer.components.searchField

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.searchField.utils.SearchFieldSize
import com.dynamiclayer.components.searchField.utils.SearchFieldState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun SearchInputPreview() {
    SearchInputSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun SearchInputSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Search Input",
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
                        title = stringResource(id = R.string.state),
                        description = "You can edit the state with default, active or filled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                SearchField(
                                    placeholder = "Search Field",
                                    text = remember {
                                        mutableStateOf("")
                                    },
                                    size = SearchFieldSize.lg,
                                    state = SearchFieldState.default
                                )
                                SearchField(
                                    placeholder = "Search Field",
                                    text = remember {
                                        mutableStateOf("Search Field")
                                    },
                                    size = SearchFieldSize.lg,
                                    state = SearchFieldState.active
                                )
                                SearchField(
                                    placeholder = "Search Field",
                                    text = remember {
                                        mutableStateOf("Search Field")
                                    },
                                    size = SearchFieldSize.lg,
                                    state = SearchFieldState.filled
                                )
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
                                SearchField(placeholder = "Search Field", text = remember {
                                    mutableStateOf("")
                                }, size = SearchFieldSize.lg)
                                SearchField(placeholder = "Search Field", text = remember {
                                    mutableStateOf("")
                                }, size = SearchFieldSize.md)
                                SearchField(placeholder = "Search Field", text = remember {
                                    mutableStateOf("")
                                }, size = SearchFieldSize.sm)

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "iconRight",
                        description = "You can edit the iconRight with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                SearchField(placeholder = "Search Field", text = remember {
                                    mutableStateOf("")
                                }, size = SearchFieldSize.lg)
                                SearchField(
                                    placeholder = "Search Field",
                                    text = remember {
                                        mutableStateOf("")
                                    },
                                    size = SearchFieldSize.lg,
                                    rightIcon = R.drawable.microphone,
                                    rightIconEnable = true
                                )


                            }
                        })
                }
            }
        })

}

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    placeholder: String,
    text: MutableState<String>,
    rightIconEnable: Boolean = false,
    @DrawableRes rightIcon: Int = R.drawable.cross,
    onRightIconClick: () -> Unit = {},
    size: SearchFieldSize,
    state: SearchFieldState = SearchFieldState.default
) {
    val shape = remember {
        RoundedCornerShape(AppRadius.rounded_full)
    }
    Row(
        modifier = modifier
            .wrapContentSize()
            .background(state.containerColor, shape)
            .border(state.border, shape)
            .padding(size.padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        BasicTextField(
            value = text.value,
            onValueChange = {
                text.value = it

            },
            modifier = Modifier.weight(1f),
            decorationBox = {
                if (text.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = state.textStyle,
                        color = state.contentColor,
                        maxLines = 1
                    )
                }
                it()
            },
            textStyle = state.textStyle.copy(color = ColorPalette.Black),
            cursorBrush = SolidColor(ColorPalette.Black),
            enabled = state != SearchFieldState.disabled, maxLines = 1
        )
        if (text.value.isNotEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.cross),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(null, null, onClick = {
                        text.value = ""
                    })
            )
        } else {
            if (rightIconEnable && state != SearchFieldState.disabled) {
                Image(
                    painter = painterResource(id = rightIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(null, null, onClick = onRightIconClick)
                )
            } else {
                if (state == SearchFieldState.filled || state == SearchFieldState.active) {
                    Image(
                        painter = painterResource(id = rightIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(null, null, onClick = onRightIconClick)
                    )
                }
            }
        }

    }
}