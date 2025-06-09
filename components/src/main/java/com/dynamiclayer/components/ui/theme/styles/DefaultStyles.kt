package com.dynamiclayer.components.ui.theme.styles

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.buttonIcon.util.models.IconButtonSize
import com.dynamiclayer.components.ui.theme.AppTextLinks
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ButtonBorders
import com.dynamiclayer.components.ui.theme.ButtonColors
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.styles.button.buttonStyle
import com.dynamiclayer.components.ui.theme.styles.iconButton.iconButtonStyle
import com.dynamiclayer.components.ui.theme.styles.segmentedControl.SegmentedControlItemColors
import com.dynamiclayer.components.ui.theme.styles.tabControl.TabControlItemColors

object DefaultButtonStyles {
    object PrimaryButtons {
        val lgStyle get()  = buttonStyle(
            ButtonSize.lg,
            ButtonColors.primaryBackground,
            ColorPalette.Grey.grey200,
            ColorPalette.Grey.grey600,
            ColorPalette.Voilet.color700,
            ButtonColors.primaryText,
            AppTextWeight.text_base_bold,
            ButtonBorders.primaryButtonBorder
        )
        val mdStyle get()  =  buttonStyle(
            ButtonSize.md,
            ButtonColors.primaryBackground,
            ColorPalette.Grey.grey200,
            ColorPalette.Grey.grey600,
            ColorPalette.Voilet.color700,
            ButtonColors.primaryText,
            AppTextWeight.text_base_bold,
            ButtonBorders.primaryButtonBorder
        )
        val smStyle get()  =  buttonStyle(
            ButtonSize.sm,
            ButtonColors.primaryBackground,
            ColorPalette.Grey.grey200,
            ColorPalette.Grey.grey600,
            ColorPalette.Voilet.color700,

            ButtonColors.primaryText,
            AppTextWeight.text_base_bold,
            ButtonBorders.primaryButtonBorder
        )
        val xsStyle get()  =  buttonStyle(
            ButtonSize.xs,
            ButtonColors.primaryBackground,
            ColorPalette.Grey.grey200,
            ColorPalette.Grey.grey600,
            ColorPalette.Voilet.color700,
            ButtonColors.primaryText,
            AppTextWeight.text_sm_bold,
            ButtonBorders.primaryButtonBorder
        )
    }

    object SecondaryButtons {
        val lgStyle get()  =  buttonStyle(
            size = ButtonSize.lg,
            backgroundColor = ColorPalette.Grey.grey800,
            disableBackgroundColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600,
            rippleColor = ColorPalette.Black,
            labelColor = ColorPalette.White,
            labelStyle = AppTextWeight.text_base_bold,
            border = ButtonBorders.secondaryButtonBorder
        )
        val mdStyle get()  =  buttonStyle(
            size = ButtonSize.md,
            backgroundColor = ColorPalette.Grey.grey800,
            disableBackgroundColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600,
            rippleColor = ColorPalette.Black,
            labelColor = ColorPalette.White,
            labelStyle = AppTextWeight.text_base_bold,
            border = ButtonBorders.secondaryButtonBorder
        )
        val smStyle get()  =  buttonStyle(
            size = ButtonSize.sm,
            backgroundColor = ColorPalette.Grey.grey800,
            disableBackgroundColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600,
            rippleColor = ColorPalette.Black,
            labelColor = ColorPalette.White,
            labelStyle = AppTextWeight.text_base_bold,
            border = ButtonBorders.secondaryButtonBorder
        )
        val xsStyle get()  =  buttonStyle(
            size = ButtonSize.xs,
            backgroundColor = ColorPalette.Grey.grey800,
            disableBackgroundColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600,
            rippleColor = ColorPalette.Black,
            labelColor = ColorPalette.White,
            labelStyle = AppTextWeight.text_sm_bold,
            border = ButtonBorders.secondaryButtonBorder
        )
    }

    object TertiaryButtons {
        val lgStyle get()  =  buttonStyle(
            size = ButtonSize.lg,
            backgroundColor = ColorPalette.White,
            disableBackgroundColor = ColorPalette.White,
            disableContentColor = ColorPalette.Grey.grey500,
            rippleColor = ColorPalette.Grey.grey100,
            labelColor = ColorPalette.Black,
            labelStyle = AppTextWeight.text_base_bold,
            border = ButtonBorders.tertiaryButtonBorder
        )
        val mdStyle get()  =  buttonStyle(
            ButtonSize.md,
            ColorPalette.White,
            ColorPalette.White,
            ColorPalette.Grey.grey500,
            ColorPalette.Grey.grey100,
            ColorPalette.Black,
            AppTextWeight.text_base_bold,
            ButtonBorders.tertiaryButtonBorder
        )
        val smStyle get()  =  buttonStyle(
            ButtonSize.sm,
            ColorPalette.White,
            ColorPalette.White,
            ColorPalette.Grey.grey500,
            ColorPalette.Grey.grey100,
            ColorPalette.Black,
            AppTextWeight.text_base_bold,
            ButtonBorders.tertiaryButtonBorder
        )
        val xsStyle get()  =  buttonStyle(
            ButtonSize.xs,
            ColorPalette.White,
            ColorPalette.White,
            ColorPalette.Grey.grey500,
            ColorPalette.Grey.grey100,
            ColorPalette.Black,
            AppTextWeight.text_sm_bold,
            ButtonBorders.tertiaryButtonBorder
        )
    }

    object GhostButtons {
        val lgStyle get()  =  buttonStyle(
            ButtonSize.lg,
            ButtonColors.ghostBackground,
            Color.Transparent,
            ColorPalette.Grey.grey500,
            Color.Unspecified,
            ButtonColors.ghostText,
            AppTextLinks.text_base,
            ButtonBorders.ghostButtonBorder
        )
        val mdStyle get()  =  buttonStyle(
            ButtonSize.md,
            ButtonColors.ghostBackground,
            Color.Transparent,
            ColorPalette.Grey.grey500,
            Color.Unspecified,

            ButtonColors.ghostText,
            AppTextLinks.text_base,
            ButtonBorders.ghostButtonBorder
        )
        val smStyle get()  =  buttonStyle(
            ButtonSize.sm,
            ButtonColors.ghostBackground,
            Color.Transparent,
            ColorPalette.Grey.grey500,
            Color.Unspecified,
            ButtonColors.ghostText,
            AppTextLinks.text_base,
            ButtonBorders.ghostButtonBorder
        )
        val xsStyle get()  =  buttonStyle(
            ButtonSize.xs,
            ButtonColors.ghostBackground,
            Color.Transparent,
            ColorPalette.Grey.grey500,
            Color.Unspecified,
            ButtonColors.ghostText,
            AppTextLinks.text_sm,
            ButtonBorders.ghostButtonBorder
        )
    }
}

object DefaultIconButtonStyles {
    object PrimaryIconButton {
        val lgStyle get()  =  iconButtonStyle(
            size = IconButtonSize.lg,
            backgroundColor = ColorPalette.Voilet.color500,
            iconColor = ColorPalette.OriginalWhite,
            rippleColor = ColorPalette.Voilet.color700,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600
        )
        val mdStyle get()  =  iconButtonStyle(
            size = IconButtonSize.md,
            backgroundColor = ColorPalette.Voilet.color500,
            iconColor = ColorPalette.OriginalWhite,
            rippleColor = ColorPalette.Voilet.color700,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600

        )
        val smStyle get()  =  iconButtonStyle(
            size = IconButtonSize.sm,
            backgroundColor = ColorPalette.Voilet.color500,
            iconColor = ColorPalette.OriginalWhite,
            rippleColor = ColorPalette.Voilet.color700,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600

        )
        val xsStyle get()  =  iconButtonStyle(
            size = IconButtonSize.xs,
            backgroundColor = ColorPalette.Voilet.color500,
            iconColor = ColorPalette.OriginalWhite,
            rippleColor = ColorPalette.Voilet.color700,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600

        )
    }

    object SecondaryIconButton {
        val lgStyle get()  =  iconButtonStyle(
            size = IconButtonSize.lg,
            backgroundColor = ColorPalette.Grey.grey800,
            iconColor = ColorPalette.White,
            rippleColor = ColorPalette.Black,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600
        )
        val mdStyle get()  =  iconButtonStyle(
            size = IconButtonSize.md,
            backgroundColor = ColorPalette.Grey.grey800,
            iconColor = ColorPalette.White,
            rippleColor = ColorPalette.Black,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600
        )
        val smStyle get()  =  iconButtonStyle(
            size = IconButtonSize.sm,
            backgroundColor = ColorPalette.Grey.grey800,
            iconColor = ColorPalette.White,
            rippleColor = ColorPalette.Black,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600
        )
        val xsStyle get()  =  iconButtonStyle(
            size = IconButtonSize.xs,
            backgroundColor = ColorPalette.Grey.grey800,
            iconColor = ColorPalette.White,
            rippleColor = ColorPalette.Black,
            border = BorderStroke(0.dp, Color.Transparent),
            disableColor = ColorPalette.Grey.grey100,
            disableContentColor = ColorPalette.Grey.grey600
        )
    }

    object TertiaryIconButton {
        val lgStyle get()  =  iconButtonStyle(
            size = IconButtonSize.lg,
            backgroundColor = ColorPalette.White,
            iconColor = ColorPalette.Black,
            rippleColor = ColorPalette.Grey.grey200,
            border = BorderStroke(1.dp, ColorPalette.Grey.grey200),
            disableColor = ColorPalette.White,
            disableContentColor = ColorPalette.Grey.grey500
        )
        val mdStyle get()  =  iconButtonStyle(
            size = IconButtonSize.md,
            backgroundColor = ColorPalette.White,
            iconColor = ColorPalette.Black,
            rippleColor = ColorPalette.Grey.grey200,
            border = BorderStroke(1.dp, ColorPalette.Grey.grey200),
            disableColor = ColorPalette.White,
            disableContentColor = ColorPalette.Grey.grey500
        )
        val smStyle get()  =  iconButtonStyle(
            size = IconButtonSize.sm,
            backgroundColor = ColorPalette.White,
            iconColor = ColorPalette.Black,
            rippleColor = ColorPalette.Grey.grey200,
            border = BorderStroke(1.dp, ColorPalette.Grey.grey200),
            disableColor = ColorPalette.White,
            disableContentColor = ColorPalette.Grey.grey500
        )
        val xsStyle get()  =  iconButtonStyle(
            size = IconButtonSize.xs,
            backgroundColor = ColorPalette.White,
            iconColor = ColorPalette.Black,
            rippleColor = ColorPalette.Grey.grey200,
            border = BorderStroke(1.dp, ColorPalette.Grey.grey200),
            disableColor = ColorPalette.White,
            disableContentColor = ColorPalette.Grey.grey500
        )
    }
}


object TabControlItemStyles {

    @Composable
    fun colors(
        selectedLabelColor: Color = ColorPalette.Black,
        unselectedLabelColor: Color = ColorPalette.Grey.grey500,

        ): TabControlItemColors = TabControlItemColors(
        selectedLabelColor = selectedLabelColor,
        unselectedLabelColor = unselectedLabelColor,
    )
}

object SegmentedControlItemStyles {
    @Composable
    fun colors(
        selectedBackgroundColor: Color = ColorPalette.White,
        selectedLabelColor: Color = ColorPalette.Black,
        unselectedBackgroundColor: Color = ColorPalette.Transparent,
        unselectedLabelColor: Color = ColorPalette.Black,
    ): SegmentedControlItemColors = SegmentedControlItemColors(
        selectedBackgroundColor = selectedBackgroundColor,
        selectedLabelColor = selectedLabelColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        unselectedLabelColor = unselectedLabelColor,
    )
}

