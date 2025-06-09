package com.dynamiclayer.components.tabControl

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.tabControl.util.components.TabControlScope
import com.dynamiclayer.components.tabControl.util.components.TabControlState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TabControlPreview() {
    TabControlSample()
}

@ExperimentalMaterial3Api
@Composable
fun TabControlSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Tab Control",
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
                        description = "You can edit the count by adding items",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                val tabs1 = remember {
                                    mutableIntStateOf(0)
                                }
                                TabControl(selectedIndex = tabs1.intValue) {
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 1",
                                        onClick = {
                                            tabs1.intValue = 0
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 2",
                                        onClick = {
                                            tabs1.intValue = 1
                                        })
                                }
                                val tabs3 = remember {
                                    mutableIntStateOf(0)
                                }
                                TabControl(selectedIndex = tabs3.intValue) {
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 1",
                                        onClick = {
                                            tabs3.intValue = 0
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 2",
                                        onClick = {
                                            tabs3.intValue = 1
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 3",
                                        onClick = {
                                            tabs3.intValue = 2
                                        })
                                }
                                val tabs4 = remember {
                                    mutableIntStateOf(0)
                                }
                                TabControl(selectedIndex = tabs4.intValue) {
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 1",
                                        onClick = {
                                            tabs4.intValue = 0
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 2",
                                        onClick = {
                                            tabs4.intValue = 1
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 3",
                                        onClick = {
                                            tabs4.intValue = 2
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 4",
                                        onClick = {
                                            tabs4.intValue = 3
                                        })
                                }
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default, selected or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {

                                val tabs3 = remember {
                                    mutableIntStateOf(0)
                                }
                                TabControl(selectedIndex = tabs3.intValue) {
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 1",
                                        onClick = {
                                            tabs3.intValue = 0
                                        })
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 2",
                                        onClick = {
                                            tabs3.intValue = 1
                                        })
                                    item(
                                        state = TabControlState.disabled,
                                        label = "Control - 3",
                                        onClick = {
                                            tabs3.intValue = 2
                                        })
                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Badge",
                        description = "You can edit the badge with none or small parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {

                                val tabs2 = remember {
                                    mutableIntStateOf(0)
                                }
                                TabControl(selectedIndex = tabs2.intValue) {
                                    item(
                                        state = TabControlState.default,
                                        label = "Control - 1",
                                        onClick = {
                                            tabs2.intValue = 0
                                        })
                                    item(
                                        state = TabControlState.default,
                                        badge = "5",
                                        label = "Control - 2",
                                        onClick = {
                                            tabs2.intValue = 1
                                        })

                                }

                            }
                        })
                }


            }
        })
}

@Composable
fun TabControl(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    selectedIndex: Int,
    items: TabControlScope.() -> Unit
) {
    val tabItems = mutableListOf<@Composable RowScope.(Boolean) -> Unit>()
    TabControlScope(tabItems).apply(items)

    when {
        tabItems.size < 2 -> {
            Log.w("TabControl", "TabControl requires at least 2 items. Currently: ${tabItems.size}")
        }

        else -> {
            RenderTabControlBar(
                tabItems,
                selectedIndex, modifier, windowInsets
            )
        }
    }
}

@Composable
private fun RenderTabControlBar(
    items: List<@Composable RowScope.(Boolean) -> Unit>,
    selectedIndex: Int,
    modifier: Modifier,
    windowInsets: WindowInsets
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .windowInsetsPadding(windowInsets),
        color = ColorPalette.White,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.run {
                widthIn(min = 365.dp)
                    .fillMaxWidth()
                    .height(5.dp)
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, item -> item.invoke(this, index == selectedIndex) }
        }
    }
}