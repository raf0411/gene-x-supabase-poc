package com.dynamiclayer.components.ui.theme

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.dynamiclayer.components.ui.theme.ColorPalette.Black
import com.dynamiclayer.components.ui.theme.ColorPalette.Grey
import com.dynamiclayer.components.ui.theme.ColorPalette.OriginalWhite
import com.dynamiclayer.components.ui.theme.ColorPalette.Red
import com.dynamiclayer.components.ui.theme.ColorPalette.Transparent
import com.dynamiclayer.components.ui.theme.ColorPalette.Voilet
import com.dynamiclayer.components.ui.theme.ColorPalette.White

interface GreyScale {
    val grey50: Color
    val grey100: Color
    val grey200: Color
    val grey300: Color
    val grey400: Color
    val grey500: Color
    val grey600: Color
    val grey700: Color
    val grey800: Color
    val grey900: Color
}

data class GreyScaleColors(
    override val grey50: Color,
    override val grey100: Color,
    override val grey200: Color,
    override val grey300: Color,
    override val grey400: Color,
    override val grey500: Color,
    override val grey600: Color,
    override val grey700: Color,
    override val grey800: Color,
    override val grey900: Color
) : GreyScale

fun whiteGrayScale(): GreyScale {
    return GreyScaleColors(
        grey50 = Color(0xFFF6F6F6),
        grey100 = Color(0xFFEEEEEE),
        grey200 = Color(0xFFE2E2E2),
        grey300 = Color(0xFFCBCBCB),
        grey400 = Color(0xFFAFAFAF),
        grey500 = Color(0xFF757575),
        grey600 = Color(0xFF545454),
        grey700 = Color(0xFF333333),
        grey800 = Color(0xFF1F1F1F),
        grey900 = Color(0xFF141414)
    )
}

fun darkGrayScale(): GreyScale {
    return GreyScaleColors(
        grey50 = Color(0xFF1F1F1F),
        grey100 = Color(0xFF333333),
        grey200 = Color(0xFF545454),
        grey300 = Color(0xFF757575),
        grey400 = Color(0xFFAFAFAF),
        grey500 = Color(0xFFCBCBCB),
        grey600 = Color(0xFFE2E2E2),
        grey700 = Color(0xFFEEEEEE),
        grey800 = Color(0xFFF6F6F6),
        grey900 = Color(0xFFFFFFFF)
    )
}

fun toggleDarkMode(isDarkMode: Boolean) {
    Grey = if (isDarkMode) {
        Black =  Color(0xFFFFFFFF)
        White = Color(0xFF141414)
        darkGrayScale()
    } else {
        White  = Color(0xFFFFFFFF)
        Black = Color(0xFF000000)
        whiteGrayScale()
    }
}

data class ColorScale(
    val color50: Color,
    val color100: Color,
    val color200: Color,
    val color300: Color,
    val color400: Color,
    val color500: Color,
    val color600: Color,
    val color700: Color,
    val color800: Color,
    val color900: Color,
    val color950: Color
)

object ColorPalette {
    val Platinum = ColorScale(
        color50 = Color(0xFFF8FAFC),
        color100 = Color(0xFFF1F5F9),
        color200 = Color(0xFFE2E8F0),
        color300 = Color(0xFFCBD5E1),
        color400 = Color(0xFF94A3B8),
        color500 = Color(0xFF64748B),
        color600 = Color(0xFF475569),
        color700 = Color(0xFF334155),
        color800 = Color(0xFF1E293B),
        color900 = Color(0xFF0F172A),
        color950 = Color(0xFF020617),
    )

    val Purple = ColorScale(
        color50 = Color(0xFFF5F2FF),
        color100 = Color(0xFFECE8FF),
        color200 = Color(0xFFDAD4FF),
        color300 = Color(0xFFC1B1FF),
        color400 = Color(0xFFA285FF),
        color500 = Color(0xFF7E49FF),
        color600 = Color(0xFF7630F7),
        color700 = Color(0xFF681EE3),
        color800 = Color(0xFF5718BF),
        color900 = Color(0xFF48169C),
        color950 = Color(0xFF2C0B6A),
    )

    val Green = ColorScale(
        color50 = Color(0xFFE8FFE4),
        color100 = Color(0xFFCBFFC5),
        color200 = Color(0xFF9AFF92),
        color300 = Color(0xFF5BFF53),
        color400 = Color(0xFF24FB20),
        color500 = Color(0xFF00DD00),
        color600 = Color(0xFF00B505),
        color700 = Color(0xFF028907),
        color800 = Color(0xFF086C0C),
        color900 = Color(0xFF0C5B11),
        color950 = Color(0xFF003305),
    )

    val Red = ColorScale(
        color50 = Color(0xFFFFF2F1),
        color100 = Color(0xFFFFE1DF),
        color200 = Color(0xFFFFC8C5),
        color300 = Color(0xFFFFA29D),
        color400 = Color(0xFFFF6C64),
        color500 = Color(0xFFFF2C20),
        color600 = Color(0xFFED2115),
        color700 = Color(0xFFC8170D),
        color800 = Color(0xFFA5170F),
        color900 = Color(0xFFA5170F),
        color950 = Color(0xFF4B0804),
    )

    val Voilet = ColorScale(
        color50 = Color(0xFFF5F2FF),
        color100 = Color(0xFFECE8FF),
        color200 = Color(0xFFDAD4FF),
        color300 = Color(0xFFC1B1FF),
        color400 = Color(0xFFA285FF),
        color500 = Color(0xFF7E49FF),
        color600 = Color(0xFF7630F7),
        color700 = Color(0xFF681EE3),
        color800 = Color(0xFF5718BF),
        color900 = Color(0xFF48169C),
        color950 = Color(0xFF2C0B6A)
    )

    val Blue = ColorScale(
        color50 = Color(0xFFEDFAFF),
        color100 = Color(0xFFD6F1FF),
        color200 = Color(0xFFB5E9FF),
        color300 = Color(0xFF83DCFF),
        color400 = Color(0xFF48C7FF),
        color500 = Color(0xFF1EA7FF),
        color600 = Color(0xFF0689FF),
        color700 = Color(0xFF0075FF),
        color800 = Color(0xFF0859C5),
        color900 = Color(0xFF0D4E9B),
        color950 = Color(0xFF0E305D)
    )

    val Yellow = ColorScale(
        color50 = Color(0xFFFFFFE7),
        color100 = Color(0xFFFEFFC1),
        color200 = Color(0xFFFFFD86),
        color300 = Color(0xFFFFF441),
        color400 = Color(0xFFFFE50D),
        color500 = Color(0xFFFFD600),
        color600 = Color(0xFFD19D00),
        color700 = Color(0xFFA67102),
        color800 = Color(0xFF89570A),
        color900 = Color(0xFF74470F),
        color950 = Color(0xFF442504),
    )
    val Rose = ColorScale(
        color50 = Color(0xFFFFF1F2),
        color100 = Color(0xFFFFE4E6),
        color200 = Color(0xFFFECDD3),
        color300 = Color(0xFFFDA4AF),
        color400 = Color(0xFFFB7185),
        color500 = Color(0xFFF43F5E),
        color600 = Color(0xFFE11D48),
        color700 = Color(0xFFBE123C),
        color800 = Color(0xFF9F1239),
        color900 = Color(0xFF881337),
        color950 = Color(0xFF4C0519),
    )
    val Pink = ColorScale(
        color50 = Color(0xFFFDF2F8),
        color100 = Color(0xFFFCE7F3),
        color200 = Color(0xFFFBCFE8),
        color300 = Color(0xFFF9A8D4),
        color400 = Color(0xFFF472B6),
        color500 = Color(0xFFEC4899),
        color600 = Color(0xFFDB2777),
        color700 = Color(0xFFBE185D),
        color800 = Color(0xFF9D174D),
        color900 = Color(0xFF831843),
        color950 = Color(0xFF500724),
    )
    val Magenta = ColorScale(
        color50 = Color(0xFFFDF1F9),
        color100 = Color(0xFFFEE5F5),
        color200 = Color(0xFFFDCEE9),
        color300 = Color(0xFFFB9DBD),
        color400 = Color(0xFFEF63C1),
        color500 = Color(0xFFEA2A7A),
        color600 = Color(0xFFD01284),
        color700 = Color(0xFFB10568),
        color800 = Color(0xFFA02D55),
        color900 = Color(0xFF8F0C4A),
        color950 = Color(0xFF580025),
    )
    val Fuchsia = ColorScale(
        color50 = Color(0xFFFDF4FF),
        color100 = Color(0xFFFAE8FF),
        color200 = Color(0xFFF5D0FE),
        color300 = Color(0xFFf0ABFC),
        color400 = Color(0xFFE879F9),
        color500 = Color(0xFFD946EF),
        color600 = Color(0xFFC026D3),
        color700 = Color(0xFFA21CAF),
        color800 = Color(0xFF86198F),
        color900 = Color(0xFF701A75),
        color950 = Color(0xFF4A044E),
    )

    val Indigo = ColorScale(
        color50 = Color(0xFFEEF2FF),
        color100 = Color(0xFFE0E7FF),
        color200 = Color(0xFFC7D2FE),
        color300 = Color(0xFFA5B4FC),
        color400 = Color(0xFF818CF8),
        color500 = Color(0xFF6366F1),
        color600 = Color(0xFF4F46E5),
        color700 = Color(0xFF4338CA),
        color800 = Color(0xFF3730A3),
        color900 = Color(0xFF312E81),
        color950 = Color(0xFF1E1B4B),
    )
    val Sky = ColorScale(
        color50 = Color(0xFFF0F9FF),
        color100 = Color(0xFFE0F2FE),
        color200 = Color(0xFFBAE6FD),
        color300 = Color(0xFF7DD3FC),
        color400 = Color(0xFF38BDF8),
        color500 = Color(0xFF0EA5E9),
        color600 = Color(0xFF0284C7),
        color700 = Color(0xFF0369A1),
        color800 = Color(0xFF075985),
        color900 = Color(0xFF0C4A6E),
        color950 = Color(0xFF082F49),
    )

    val Cyan = ColorScale(
        color50 = Color(0xFFECFEFF),
        color100 = Color(0xFFCFFAFE),
        color200 = Color(0xFFA5F3FC),
        color300 = Color(0xFF67E8F9),
        color400 = Color(0xFF22D3EE),
        color500 = Color(0xFF06B6D4),
        color600 = Color(0xFF0891B2),
        color700 = Color(0xFF0E7490),
        color800 = Color(0xFF155E75),
        color900 = Color(0xFF164E63),
        color950 = Color(0xFF083344),
    )

    val Teal = ColorScale(
        color50 = Color(0xFFF0FDFA),
        color100 = Color(0xFFCCFBF1),
        color200 = Color(0xFF99F6E4),
        color300 = Color(0xFF5EEAD4),
        color400 = Color(0xFF2DD4BF),
        color500 = Color(0xFF14B8A6),
        color600 = Color(0xFF0D9488),
        color700 = Color(0xFF0F766E),
        color800 = Color(0xFF115E59),
        color900 = Color(0xFF134E4A),
        color950 = Color(0xFF042F2E),
    )
    val Emerald = ColorScale(
        color50 = Color(0xFFECFDF5),
        color100 = Color(0xFFD1FAE5),
        color200 = Color(0xFFA7F3D0),
        color300 = Color(0xFF6EE7B7),
        color400 = Color(0xFF34D399),
        color500 = Color(0xFF10B981),
        color600 = Color(0xFF059669),
        color700 = Color(0xFF047857),
        color800 = Color(0xFF065F46),
        color900 = Color(0xFF064E3B),
        color950 = Color(0xFF022C22),
    )

    val Lime = ColorScale(
        color50 = Color(0xFFF7FEE7),
        color100 = Color(0xFFECFCCB),
        color200 = Color(0xFFD9F99D),
        color300 = Color(0xFFBEF264),
        color400 = Color(0xFFA3E635),
        color500 = Color(0xFF84CC16),
        color600 = Color(0xFF65A30D),
        color700 = Color(0xFF4D7C0F),
        color800 = Color(0xFF3F6212),
        color900 = Color(0xFF365314),
        color950 = Color(0xFF1A2E05),
    )
    val Orange = ColorScale(
        color50 = Color(0xFFFFFAEC),
        color100 = Color(0xFFFFF3D3),
        color200 = Color(0xFFFEF3A5),
        color300 = Color(0xFFFCE06D),
        color400 = Color(0xFFFAD432),
        color500 = Color(0xFFFF920A),
        color600 = Color(0xFFFF7A00),
        color700 = Color(0xFFCC5802),
        color800 = Color(0xFFA1440B),
        color900 = Color(0xFF823A0C),
        color950 = Color(0xFF461B04),
    )

    val Brown = ColorScale(
        color50 = Color(0xFFFBF8EF),
        color100 = Color(0xFFF3E5D2),
        color200 = Color(0xFFE5C9A2),
        color300 = Color(0xFFDBA971),
        color400 = Color(0xFFCF8E50),
        color500 = Color(0xFFC5733B),
        color600 = Color(0xFFB95E35),
        color700 = Color(0xFF91412C),
        color800 = Color(0xFF773529),
        color900 = Color(0xFF622D25),
        color950 = Color(0xFF371511),
    )

    var Grey: GreyScale = whiteGrayScale()

    var Black:Color = Color(0xFF000000)
    var White = Color(0xFFFFFFFF)
    val OriginalWhite = Color(0xFFFFFFFF)
    var OriginalBlack:Color = Color(0xFF000000)
    val Transparent = Color(0x00000000)

}

object ButtonColors {
    val primaryBackground = Voilet.color500
    val primaryText = OriginalWhite

    val ghostBackground = Transparent
    val ghostText = Voilet.color500
}


object InputFieldColors {


    val inputColor = Black
    val errorTextColor = Red.color500
    val disabledTextColor = Grey.grey500
    val supportingErrorColor = Red.color500

    val cursorColor = Grey.grey500
    val errorCursorColor = Red.color500

    val placeholderColor = Grey.grey500
    val errorPlaceholderColor = Red.color500
    val disabledPlaceholderColor = Grey.grey500

    val labelColor = Grey.grey500
    val errorLabelColor = Red.color500
    val disabledLabelColor = Grey.grey500


    val textColor = Black

    @Composable
    internal fun textColor(
        enabled: Boolean,
        isSuccess: Boolean,
        isEmpty: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> Grey.grey500
            isSuccess -> Black
            focused || !isEmpty -> Black
            else -> Black
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }


    @Composable
    internal fun borderColor(
        isNotEmpty: Boolean,
        enabled: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> Grey.grey200
            focused || isNotEmpty -> Black
            else -> Grey.grey200
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    fun textSelectionColors(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): TextSelectionColors {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> disabledTextColor
            isError -> errorTextColor
            focused -> inputColor
            else -> textColor
        }
        return TextSelectionColors(
            handleColor = targetValue,
            backgroundColor = targetValue.copy(alpha = 0.12f)
        )
    }

    @Composable
    fun placeHolderColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> disabledPlaceholderColor
            isError -> errorPlaceholderColor
            focused -> inputColor
            else -> placeholderColor
        }
        return rememberUpdatedState(targetValue)
    }


    @Composable
    fun labelColor(
        isError: Boolean,
        enabled: Boolean
    ): State<Color> {
        val targetValue = when {
            !enabled -> disabledLabelColor
            isError -> errorLabelColor
            else -> labelColor
        }
        return rememberUpdatedState(targetValue)
    }


    @Composable
    fun customInputFieldColors(
        enabled: Boolean,
        isError: Boolean,
        isSuccess: Boolean,
        isEmpty: Boolean,
        interactionSource: InteractionSource
    ): TextFieldColors {
        return TextFieldDefaults.colors(
            focusedTextColor = textColor(enabled, isSuccess, isEmpty, interactionSource).value,
            unfocusedTextColor = textColor(enabled, isSuccess, isEmpty, interactionSource).value,
            disabledTextColor = textColor(enabled, isSuccess, isEmpty, interactionSource).value,
            errorTextColor = textColor(enabled, isSuccess, isEmpty, interactionSource).value,
            cursorColor = cursorColor(isError).value,
            errorCursorColor = cursorColor(isError).value,
            selectionColors = textSelectionColors(enabled, isError, interactionSource),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedLabelColor = labelColor(isError, enabled).value,
            unfocusedLabelColor = labelColor(isError, enabled).value,
            disabledLabelColor = labelColor(isError, enabled).value,
            errorLabelColor = labelColor(isError, enabled).value,
            focusedPlaceholderColor = placeHolderColor(
                enabled,
                isError,
                interactionSource
            ).value,
            unfocusedPlaceholderColor = placeHolderColor(
                enabled,
                isError,
                interactionSource
            ).value,
            disabledPlaceholderColor = placeHolderColor(
                enabled,
                isError,
                interactionSource
            ).value,
            errorPlaceholderColor = placeHolderColor(enabled, isError, interactionSource).value,
            focusedSupportingTextColor = errorTextColor,
            unfocusedSupportingTextColor = errorTextColor,
            disabledSupportingTextColor = errorTextColor,
            errorSupportingTextColor = errorTextColor,
        )
    }


}