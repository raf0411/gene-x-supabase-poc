package com.dynamiclayer.components.exampleView.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.card.Card
import com.dynamiclayer.components.card.util.models.CardSize
import com.dynamiclayer.components.exampleView.destinations.Destinations
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Immutable
data class HomeCard(val title: String, val icon: Int, val destination: Destinations)

@Composable
fun HomeScreen(homeNavController: NavController?) {
    val homeCards = remember {
        listOf(
            HomeCard("Avatar", R.drawable.ic_input_success, Destinations.Avatar),
            HomeCard("Avatar Group", R.drawable.ic_input_success, Destinations.AvatarGroup),
            HomeCard("Badge", R.drawable.ic_input_success, Destinations.Badge),
            HomeCard(
                "Badge Notification", R.drawable.ic_input_success, Destinations.BadgeNotification
            ),
            HomeCard("Badge Activity", R.drawable.ic_input_success, Destinations.BadgeActivity),
            HomeCard(
                "bottom Navigation", R.drawable.ic_input_success, Destinations.BottomNavigation
            ),
            HomeCard("bottom Sheet", R.drawable.ic_input_success, Destinations.BottomSheet),
            HomeCard("button Icon", R.drawable.ic_input_success, Destinations.ButtonIcon),
            HomeCard("button", R.drawable.ic_input_success, Destinations.Buttons),
            HomeCard("Button Dock", R.drawable.ic_input_success, Destinations.ButtonsDock),
            HomeCard("Button Loading", R.drawable.ic_input_success, Destinations.ButtonLoading),
            HomeCard("Card", R.drawable.ic_input_success, Destinations.Card),
            HomeCard("Checkbox", R.drawable.ic_input_success, Destinations.Checkbox),
            HomeCard("Chip", R.drawable.ic_input_success, Destinations.Chip),
            HomeCard("Coach Mark", R.drawable.ic_input_success, Destinations.CoachMark),
            HomeCard("CheckList", R.drawable.ic_input_success, Destinations.CheckList),
            HomeCard("Collapse", R.drawable.ic_input_success, Destinations.Collapse),
            HomeCard("File Uploader", R.drawable.ic_input_success, Destinations.FileUploader),
            HomeCard("Inputs", R.drawable.ic_input_success, Destinations.Inputs),
            HomeCard("Line Item", R.drawable.ic_input_success, Destinations.LineItem),
            HomeCard(
                "Line Item Message",
                R.drawable.ic_input_success,
                Destinations.LineItemMessage
            ),
            HomeCard("Loading Dots", R.drawable.ic_input_success, Destinations.LoadingDots),
            HomeCard("Menu", R.drawable.ic_input_success, Destinations.Menu),
            HomeCard("Message Dock", R.drawable.ic_input_success, Destinations.MessageDock),
            HomeCard("Message", R.drawable.ic_input_success, Destinations.Messages),
            HomeCard("Notification", R.drawable.ic_input_success, Destinations.Notification),

            HomeCard("Pagination", R.drawable.ic_input_success, Destinations.Pagination),
            HomeCard("Pin Input", R.drawable.ic_input_success, Destinations.PinInput),
            HomeCard("Progress Bar", R.drawable.ic_input_success, Destinations.ProgressBar),
            HomeCard("ProgressCircle", R.drawable.ic_input_success, Destinations.ProgressCircle),
            HomeCard("Pop Over Filter", R.drawable.ic_input_success, Destinations.PopOverFilter),
            HomeCard("Pop Over", R.drawable.ic_input_success, Destinations.PopOver),
            HomeCard("Pin Keyboard", R.drawable.ic_input_success, Destinations.PinKeyboard),
            HomeCard("Radio button", R.drawable.ic_input_success, Destinations.RadioButton),
            HomeCard("Rating", R.drawable.ic_input_success, Destinations.Rating),
            HomeCard(
                "Segmented Control", R.drawable.ic_input_success, Destinations.SegmentedControl
            ),
            HomeCard("Search Bar", R.drawable.ic_input_success, Destinations.SearchBar),
            HomeCard("Snackbar", R.drawable.ic_input_success, Destinations.Snackbar),
            HomeCard("Tab Control", R.drawable.ic_input_success, Destinations.TabControl),
            HomeCard("Toggle", R.drawable.ic_input_success, Destinations.Toggle),
            HomeCard("Top Navigation", R.drawable.ic_input_success, Destinations.TopNavigation),
            HomeCard(
                "Top Navigation Message",
                R.drawable.ic_input_success,
                Destinations.TopNavigationMessage
            ),
            HomeCard("Textarea", R.drawable.ic_input_success, Destinations.Textarea),
            HomeCard("Teaser", R.drawable.ic_input_success, Destinations.Teaser),
            HomeCard("Tooltip", R.drawable.ic_input_success, Destinations.Tooltip),

            )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
        ) {
            item(span = {
                GridItemSpan(2)
            }) {
                TopNavigation(
                    title = "Home",
                    iconLeft = null,
                    iconRight = TopNavigationIconType.Drawable(
                        drawable = R.drawable.dark_mode,
                        onClick = {
                            onDarkButtonClick?.invoke()
                        },
                        tint = ColorPalette.Black
                    ),
                    size = TopNavigationSize.lg,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            itemsIndexed(homeCards, key = { _, it ->
                it.hashCode()
            }) { index, it ->
                Card(
                    title = it.title,
                    icon = it.icon,
                    description = "Component",
                    size = CardSize.lg,
                    onClick = {
                        homeNavController?.navigate(it.destination)
                    },
                    modifier = Modifier
                        .then(
                            if (index % 2 == 0) {
                                Modifier.padding(start = GeneralSpacing.p_12)
                            } else {
                                Modifier.padding(end = GeneralSpacing.p_12)
                            }
                        )
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContent() {
    HomeScreen(homeNavController = null)
}