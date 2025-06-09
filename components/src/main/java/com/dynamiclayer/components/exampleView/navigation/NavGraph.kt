package com.dynamiclayer.components.exampleView.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dynamiclayer.components.buttonIcon.IconButtonSample
import com.dynamiclayer.components.Checklist.CheckListSample
import com.dynamiclayer.components.avatar.AvatarsSample
import com.dynamiclayer.components.avatarGroup.AvatarGroupSample
import com.dynamiclayer.components.badge.BadgeSample
import com.dynamiclayer.components.badgeActivity.BadgeActivitySample
import com.dynamiclayer.components.badgeNotification.BadgeNotificationSample
import com.dynamiclayer.components.bottomNavigation.BottomNavigationSample
import com.dynamiclayer.components.bottomSheet.BottomSheetSample
import com.dynamiclayer.components.button.ButtonsSample
import com.dynamiclayer.components.buttonDock.ButtonsDockSample
import com.dynamiclayer.components.buttonLoading.ButtonLoadingSample
import com.dynamiclayer.components.card.CardSample
import com.dynamiclayer.components.checkbox.CheckBoxSample
import com.dynamiclayer.components.chip.ChipSample
import com.dynamiclayer.components.coachMark.CoachMarkSample
import com.dynamiclayer.components.collapse.CollapseSample
import com.dynamiclayer.components.exampleView.destinations.Destinations
import com.dynamiclayer.components.exampleView.screens.MainScreen
import com.dynamiclayer.components.fileUploader.FileUploaderSample
import com.dynamiclayer.components.inputField.InputFieldsSample
import com.dynamiclayer.components.lineItem.LineItemSample
import com.dynamiclayer.components.lineItemMessage.LineItemMessageSample
import com.dynamiclayer.components.loadingDots.LoadingDotsSample
import com.dynamiclayer.components.menu.MenuSample
import com.dynamiclayer.components.message.MessagesSample
import com.dynamiclayer.components.messageDock.MessageDockSample
import com.dynamiclayer.components.notification.NotificationsSample
import com.dynamiclayer.components.pagination.PaginationSample
import com.dynamiclayer.components.pinInput.PinInputSample
import com.dynamiclayer.components.pinKeyboard.PinKeyboardSample
import com.dynamiclayer.components.popOver.PopOverSample
import com.dynamiclayer.components.popOverFilter.PopOverFilterSample
import com.dynamiclayer.components.progressBar.ProgressBarSample
import com.dynamiclayer.components.progressCircle.ProgressCircleSample
import com.dynamiclayer.components.radioButton.RadioButtonSample
import com.dynamiclayer.components.rating.RatingSample
import com.dynamiclayer.components.searchField.SearchInputSample
import com.dynamiclayer.components.segmentControl.SegmentedControlSample
import com.dynamiclayer.components.snackbar.SnackbarSample
import com.dynamiclayer.components.tabControl.TabControlSample
import com.dynamiclayer.components.teaser.TeaserSample
import com.dynamiclayer.components.textarea.TextareaSample
import com.dynamiclayer.components.toggle.ToggleSample
import com.dynamiclayer.components.tooltip.TooltipSample
import com.dynamiclayer.components.topNavigation.TopNavigationSample
import com.dynamiclayer.components.topNavigationMessage.TopNavigationMessageSample

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.MainScreen,
        enterTransition = {
           fadeIn(animationSpec = tween(0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(0))
        }) {
        composable<Destinations.MainScreen> {
            MainScreen(navController = navController)
        }
        composable<Destinations.Avatar> {
            AvatarsSample(navController)
        }
        composable<Destinations.AvatarGroup> {
            AvatarGroupSample(navController)
        }
        composable<Destinations.Badge> {
            BadgeSample(navController)
        }
        composable<Destinations.BottomNavigation> {
            BottomNavigationSample(navController)
        }
        composable<Destinations.BottomSheet> {
            BottomSheetSample()
        }
        composable<Destinations.ButtonIcon> {
            IconButtonSample(navController)
        }
        composable<Destinations.Buttons> {
            ButtonsSample(navController)
        }
        composable<Destinations.ButtonsDock> {
            ButtonsDockSample(navController)
        }
        composable<Destinations.ButtonLoading> {
            ButtonLoadingSample(navController)
        }
        composable<Destinations.CheckList> {
            CheckListSample(navController)
        }
        composable<Destinations.Card> {
            CardSample(navController)
        }
        composable<Destinations.Checkbox> {
            CheckBoxSample(navController)
        }
        composable<Destinations.Chip> {
            ChipSample(navController)
        }
        composable<Destinations.CoachMark> {
            CoachMarkSample(navController)
        }

        composable<Destinations.Inputs> {
            InputFieldsSample(navController)
        }
        composable<Destinations.LineItem> {
            LineItemSample(navController)
        }
        composable<Destinations.LineItemMessage> {
            LineItemMessageSample(navController)
        }
        composable<Destinations.LoadingDots> {
            LoadingDotsSample(navController)
        }
        composable<Destinations.Collapse> {
            CollapseSample(navController)
        }
        composable<Destinations.Menu> {
            MenuSample(navController)
        }
        composable<Destinations.Messages> {
            MessagesSample(navController)
        }
        composable<Destinations.Notification> {
            NotificationsSample(navController)
        }
        composable<Destinations.BadgeNotification> {
            BadgeNotificationSample(navController)
        }
        composable<Destinations.Pagination> {
            PaginationSample(navController)
        }
        composable<Destinations.RadioButton> {
            RadioButtonSample(navController)
        }
        composable<Destinations.SegmentedControl> {
            SegmentedControlSample(navController)
        }
        composable<Destinations.TabControl> {
            TabControlSample(navController)
        }
        composable<Destinations.Toggle> {
            ToggleSample(navController)
        }
        composable<Destinations.FileUploader> {
            FileUploaderSample(navController)
        }

        composable<Destinations.TopNavigation> {
            TopNavigationSample(navController)
        }
        composable<Destinations.TopNavigationMessage> {
            TopNavigationMessageSample(navController)
        }
        composable<Destinations.MessageDock> {
            MessageDockSample(navController)
        }

        composable<Destinations.PinInput> {
            PinInputSample(navController = navController)
        }
        composable<Destinations.ProgressBar> {
            ProgressBarSample(navController = navController)
        }
        composable<Destinations.SearchBar> {
            SearchInputSample(navController = navController)
        }
        composable<Destinations.BadgeActivity> {
            BadgeActivitySample(navController = navController)
        }
        composable<Destinations.Textarea> {
            TextareaSample(navController = navController)
        }
        composable<Destinations.Teaser> {
            TeaserSample(navController = navController)
        }
        composable<Destinations.Snackbar> {
            SnackbarSample(navController = navController)
        }
        composable<Destinations.Rating> {
            RatingSample(navController = navController)
        }
        composable<Destinations.ProgressCircle> {
            ProgressCircleSample(navController = navController)
        }
        composable<Destinations.PopOverFilter> {
            PopOverFilterSample(navController = navController)
        }
        composable<Destinations.PopOver> {
            PopOverSample(navController = navController)
        }
        composable<Destinations.PinKeyboard> {
            PinKeyboardSample(navController = navController)
        }
        composable<Destinations.Tooltip> {
            TooltipSample(navController = navController)
        }
    }
}