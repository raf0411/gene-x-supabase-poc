package com.dynamiclayer.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.menu.models.MenuItemProperties
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@Composable
fun MenuItem(
    enabled: Boolean = true,
    label: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(ColorPalette.White)
            .clickable(
                interactionSource = null,
                indication = null
            ) { if (enabled) onClick() }
            .wrapContentHeight()
            .defaultMinSize(minHeight = 48.dp)
            .width(245.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = GeneralSpacing.p_16,
                vertical = GeneralSpacing.p_12
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = label,
                style = AppTextWeight.text_base_regular,
                color = ColorPalette.Black,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun Menu(
    modifier: Modifier = Modifier,
    items: List<MenuItemProperties>
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppRadius.rounded_lg))
            .border(1.dp, ColorPalette.Grey.grey200, RoundedCornerShape(AppRadius.rounded_lg))
    ) {
        repeat(items.size){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MenuItem(
                    label = items[it].label,
                    onClick = items[it].onClick
                )
                if (it < items.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .width(245.dp)
                            .background(ColorPalette.Grey.grey200)
                    )
                }
            }
        }

    }

}

@ExperimentalMaterial3Api
@Composable
fun MenuSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Menu",
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
                        description = "You can add menu items by adding them to the items list",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Menu(
                                    items = listOf(
                                        MenuItemProperties("Menu - 1", onClick = {}),
                                        MenuItemProperties("Menu - 2", onClick = {})
                                    )
                                )
                                Menu(
                                    items = listOf(
                                        MenuItemProperties("Menu - 1", onClick = {}),
                                        MenuItemProperties("Menu - 2", onClick = {}),
                                        MenuItemProperties("Menu - 3", onClick = {})
                                    )
                                )
                                Menu(
                                    items = listOf(
                                        MenuItemProperties("Menu - 1", onClick = {}),
                                        MenuItemProperties("Menu - 2", onClick = {}),
                                        MenuItemProperties("Menu - 3", onClick = {}),
                                        MenuItemProperties("Menu - 4", onClick = {})
                                    )
                                )
                            }
                        })
                }
            }
        })

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MenuPreview() {
    MenuSample()
}