package com.dynamiclayer.components.exampleView.destinations

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    data object MainScreen : Destinations()

    @Serializable
    data object HomeScreen : Destinations()

    @Serializable
    data object SettingScreen : Destinations()

    @Serializable
    data object TemplatesScreen : Destinations()

    @Serializable
    data object Avatar : Destinations()

    @Serializable
    data object AvatarGroup : Destinations()

    @Serializable
    data object Badge : Destinations()

    @Serializable
    data object BottomNavigation : Destinations()

    @Serializable
    data object BottomSheet : Destinations()

    @Serializable
    data object ButtonIcon : Destinations()

    @Serializable
    data object Buttons : Destinations()

    @Serializable
    data object ButtonsDock : Destinations()
    @Serializable
    data object ButtonLoading : Destinations()
    @Serializable
    data object CheckList : Destinations()

    @Serializable
    data object Collapse : Destinations()

    @Serializable
    data object Card : Destinations()

    @Serializable
    data object Checkbox : Destinations()

    @Serializable
    data object Chip : Destinations()

    @Serializable
    data object CoachMark : Destinations()


    @Serializable
    data object Inputs : Destinations()

    @Serializable
    data object LineItem : Destinations()
    @Serializable
    data object LineItemMessage : Destinations()
    @Serializable
    data object LoadingDots : Destinations()


    @Serializable
    data object Menu : Destinations()

    @Serializable
    data object Messages : Destinations()

    @Serializable
    data object Notification : Destinations()

    @Serializable
    data object BadgeNotification : Destinations()

    @Serializable
    data object Pagination : Destinations()

    @Serializable
    data object RadioButton : Destinations()

    @Serializable
    data object SegmentedControl : Destinations()

    @Serializable
    data object TabControl : Destinations()

    @Serializable
    data object Toggle : Destinations()

    @Serializable
    data object TopNavigation : Destinations()
    @Serializable
    data object TopNavigationMessage : Destinations()
    @Serializable
    data object FileUploader : Destinations()

    @Serializable
    data object MessageDock : Destinations()

    @Serializable
    data object PinInput : Destinations()

    @Serializable
    data object ProgressBar : Destinations()
    @Serializable
    data object ProgressCircle : Destinations()
    @Serializable
    data object PopOverFilter : Destinations()
    @Serializable
    data object PopOver : Destinations()
    @Serializable
    data object PinKeyboard : Destinations()
    @Serializable
    data object SearchBar : Destinations()

    @Serializable
    data object BadgeActivity : Destinations()
    @Serializable
    data object Teaser : Destinations()
    @Serializable
    data object Tooltip : Destinations()
    @Serializable
    data object Snackbar : Destinations()
    @Serializable
    data object Rating : Destinations()
    @Serializable
    data object Textarea : Destinations()
}
