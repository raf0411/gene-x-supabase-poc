package com.dynamiclayer.components.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.inputField.util.InputFieldSize


object GeneralSpacing {
    val p_0 = 0.dp
    val p_2 = 2.dp
    val p_4 = 4.dp
    val p_8 = 8.dp
    val p_12 = 12.dp
    val p_16 = 16.dp
    val p_20 = 20.dp
    val p_24 = 24.dp
    val p_28 = 28.dp
    val p_32 = 32.dp
    val p_36 = 36.dp
    val p_40 = 40.dp
    val p_44 = 44.dp
    val p_48 = 48.dp
    val p_56 = 56.dp
    val p_64 = 64.dp
    val p_80 = 80.dp
    val p_96 = 96.dp
}

object GeneralPaddings {
    val p_0 = PaddingValues(GeneralSpacing.p_0)
    val p_2 = PaddingValues(GeneralSpacing.p_2)
    val p_4 = PaddingValues(GeneralSpacing.p_4)
    val p_8 = PaddingValues(GeneralSpacing.p_8)
    val p_12 = PaddingValues(GeneralSpacing.p_12)
    val p_16 = PaddingValues(GeneralSpacing.p_16)
    val p_20 = PaddingValues(GeneralSpacing.p_20)
    val p_24 = PaddingValues(GeneralSpacing.p_24)
    val p_28 = PaddingValues(GeneralSpacing.p_28)
    val p_32 = PaddingValues(GeneralSpacing.p_32)
    val p_36 = PaddingValues(GeneralSpacing.p_36)
    val p_40 = PaddingValues(GeneralSpacing.p_40)
    val p_44 = PaddingValues(GeneralSpacing.p_44)
    val p_48 = PaddingValues(GeneralSpacing.p_48)
    val p_56 = PaddingValues(GeneralSpacing.p_56)
    val p_64 = PaddingValues(GeneralSpacing.p_64)
    val p_80 = PaddingValues(GeneralSpacing.p_80)
    val p_96 = PaddingValues(GeneralSpacing.p_96)
}

object ButtonPaddings {
    val LgButtonPadding =
        PaddingValues(horizontal = GeneralSpacing.p_24, vertical = GeneralSpacing.p_16)
    val MdButtonPadding =
        PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_12)
    val SmButtonPadding =
        PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_8)
    val XsButtonPadding =
        PaddingValues(horizontal = GeneralSpacing.p_12, vertical = GeneralSpacing.p_4)
}







object InputFieldPaddings {
    val inputFieldLgUnfocusedPadding = GeneralPaddings.p_16
    val inputFieldMdUnfocusedPadding = GeneralPaddings.p_16
    val inputFieldLgFocusedPadding =
        PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_8)
    val inputFieldMdFocusedPadding =
        PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_8)

    val inputFieldSmUnfocusedPadding = PaddingValues(horizontal = GeneralSpacing.p_12, vertical = GeneralSpacing.p_8)
    val inputFieldSmFocusedPadding = PaddingValues(horizontal = GeneralSpacing.p_12)


    @Composable
    internal fun inputFieldPadding(
        isFocused: Boolean,
        size: InputFieldSize
    ): PaddingValues = when (size) {
        InputFieldSize.lg -> if (isFocused) inputFieldLgFocusedPadding else inputFieldLgUnfocusedPadding
        InputFieldSize.md -> if (isFocused) inputFieldMdFocusedPadding else inputFieldMdUnfocusedPadding
        InputFieldSize.sm -> if (isFocused) inputFieldSmFocusedPadding else inputFieldSmUnfocusedPadding
    }

}