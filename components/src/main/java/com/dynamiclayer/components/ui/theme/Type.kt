package com.dynamiclayer.components.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp


val LargeTitle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = 0.em
)

val Title1 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.em
)

val Title2 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.em
)

val Title3 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.em
)

val BodyLarge = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.em
)

val BodySmall = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em
)

val BodyLargeBold = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.em
)

val BodySmallBold = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em
)


val Typography = Typography(
    displayLarge = LargeTitle,
    headlineLarge = Title1,
    headlineMedium = Title2,
    headlineSmall = Title3,
    bodyMedium = BodyLarge,
    bodySmall = BodySmall,
    bodyLarge = BodyLargeBold,
    displayMedium = BodySmallBold,
    displaySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
)


object LetterSpacings {
    val spacing_xs = 0.04.sp
    val spacing_sm = 0.sp
    val spacing_base = 0.sp
    val spacing_lg = (-0.04).sp
    val spacing_xl = (-0.08).sp
    val spacing_2xl = (-0.1).sp
    val spacing_3xl = (-0.12).sp
    val spacing_4xl = (-0.16).sp
    val spacing_5xl = (-0.4).sp
}

object TextSizes {
    val text_xs = 12.sp
    val text_sm = 14.sp
    val text_base = 16.sp
    val text_lg = 18.sp
    val text_xl = 20.sp
    val text_2xl = 24.sp
    val text_3xl = 28.sp
    val text_4xl = 32.sp
    val text_5xl = 40.sp
}

object LineHeight {
    val text_xs = 16.sp
    val text_sm = 20.sp
    val text_base = 24.sp
    val text_lg = 28.sp
    val text_xl = 28.sp
    val text_2xl = 32.sp
    val text_3xl = 36.sp
    val text_4xl = 40.sp
    val text_5xl = 48.sp
}

object AppTextWeight {
    val text_xs_light = AppTextStyles.text_xs.copy(
        fontWeight = FontWeight.Light
    )
    val text_xs_regular = AppTextStyles.text_xs.copy(
        fontWeight = FontWeight.Normal
    )
    val text_xs_medium = AppTextStyles.text_xs.copy(
        fontWeight = FontWeight.Medium
    )
    val text_xs_semibold = AppTextStyles.text_xs.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_xs_bold = AppTextStyles.text_xs.copy(
        fontWeight = FontWeight.Bold
    )

    //sm
    val text_sm_light = AppTextStyles.text_sm.copy(
        fontWeight = FontWeight.Light
    )
    val text_sm_regular = AppTextStyles.text_sm.copy(
        fontWeight = FontWeight.Normal
    )
    val text_sm_medium = AppTextStyles.text_sm.copy(
        fontWeight = FontWeight.Medium
    )
    val text_sm_semiBold = AppTextStyles.text_sm.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_sm_bold = AppTextStyles.text_sm.copy(
        fontWeight = FontWeight.Bold
    )

    //base
    val text_base_light = AppTextStyles.text_base.copy(
        fontWeight = FontWeight.Light
    )
    val text_base_regular = AppTextStyles.text_base.copy(
        fontWeight = FontWeight.Normal
    )
    val text_base_medium = AppTextStyles.text_base.copy(
        fontWeight = FontWeight.Medium
    )
    val text_base_semiBold = AppTextStyles.text_base.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_base_bold = AppTextStyles.text_base.copy(
        fontWeight = FontWeight.Bold
    )

    //lg
    val text_lg_light = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.Light
    )
    val text_lg_regular = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.Normal
    )
    val text_lg_medium = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.Medium
    )
    val text_lg_semiBold = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_lg_bold = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.Bold
    )


    //xl
    val text_xl_light = AppTextStyles.text_xl.copy(
        fontWeight = FontWeight.Light
    )
    val text_xl_regular = AppTextStyles.text_xl.copy(
        fontWeight = FontWeight.Normal
    )
    val text_xl_medium = AppTextStyles.text_xl.copy(
        fontWeight = FontWeight.Medium
    )
    val text_xl_semiBold = AppTextStyles.text_xl.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_xl_bold = AppTextStyles.text_xl.copy(
        fontWeight = FontWeight.Bold
    )

    //2xl
    val text_2xl_light = AppTextStyles.text_2xl.copy(
        fontWeight = FontWeight.Light
    )
    val text_2xl_regular = AppTextStyles.text_2xl.copy(
        fontWeight = FontWeight.Normal
    )
    val text_2xl_medium = AppTextStyles.text_2xl.copy(
        fontWeight = FontWeight.Medium
    )
    val text_2xl_semiBold = AppTextStyles.text_2xl.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_2xl_bold = AppTextStyles.text_lg.copy(
        fontWeight = FontWeight.Bold
    )

    //3xl
    val text_3xl_light = AppTextStyles.text_3xl.copy(
        fontWeight = FontWeight.Light
    )
    val text_3xl_regular = AppTextStyles.text_3xl.copy(
        fontWeight = FontWeight.Normal
    )
    val text_3xl_medium = AppTextStyles.text_3xl.copy(
        fontWeight = FontWeight.Medium
    )
    val text_3xl_semiBold = AppTextStyles.text_3xl.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_3xl_bold = AppTextStyles.text_3xl.copy(
        fontWeight = FontWeight.Bold
    )

    //4xl
    val text_4xl_light = AppTextStyles.text_4xl.copy(
        fontWeight = FontWeight.Light
    )
    val text_4xl_regular = AppTextStyles.text_4xl.copy(
        fontWeight = FontWeight.Normal
    )
    val text_4xl_medium = AppTextStyles.text_4xl.copy(
        fontWeight = FontWeight.Medium
    )
    val text_4xl_semiBold = AppTextStyles.text_4xl.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_4xl_bold = AppTextStyles.text_4xl.copy(
        fontWeight = FontWeight.Bold
    )

    //5xl
    val text_5xl_light = AppTextStyles.text_5xl.copy(
        fontWeight = FontWeight.Light
    )
    val text_5xl_regular = AppTextStyles.text_5xl.copy(
        fontWeight = FontWeight.Normal
    )
    val text_5xl_medium = AppTextStyles.text_5xl.copy(
        fontWeight = FontWeight.Medium
    )
    val text_5xl_semiBold = AppTextStyles.text_5xl.copy(
        fontWeight = FontWeight.SemiBold
    )
    val text_5xl_bold = AppTextStyles.text_5xl.copy(
        fontWeight = FontWeight.Bold
    )

}

object AppTextLinks {
    val text_xs = AppTextWeight.text_xs_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_sm = AppTextWeight.text_sm_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_base = AppTextWeight.text_base_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_lg = AppTextWeight.text_lg_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_xl = AppTextWeight.text_xl_bold.copy(
        textDecoration = TextDecoration.Underline
    )

    val text_2xl = AppTextWeight.text_2xl_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_3xl = AppTextWeight.text_3xl_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_4xl = AppTextWeight.text_4xl_bold.copy(
        textDecoration = TextDecoration.Underline
    )
    val text_5xl = AppTextWeight.text_5xl_bold.copy(
        textDecoration = TextDecoration.Underline
    )
}

object AppTextStrike {
    val text_xs = AppTextWeight.text_xs_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_sm = AppTextWeight.text_sm_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_base = AppTextWeight.text_base_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_lg = AppTextWeight.text_lg_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_xl = AppTextWeight.text_xl_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )

    val text_2xl = AppTextWeight.text_2xl_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_3xl = AppTextWeight.text_3xl_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_4xl = AppTextWeight.text_4xl_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
    val text_5xl = AppTextWeight.text_5xl_regular.copy(
        textDecoration = TextDecoration.LineThrough
    )
}

object AppTextStyles {
    val text_xs = TextStyle(
        fontSize = TextSizes.text_xs,
        letterSpacing = LetterSpacings.spacing_xs,
        lineHeight = LineHeight.text_xs
    )
    val text_sm = TextStyle(
        fontSize = TextSizes.text_sm,
        letterSpacing = LetterSpacings.spacing_sm,
        lineHeight = LineHeight.text_sm
    )
    val text_base = TextStyle(
        fontSize = TextSizes.text_base,
        letterSpacing = LetterSpacings.spacing_base,
        lineHeight = LineHeight.text_base
    )
    val text_lg = TextStyle(
        fontSize = TextSizes.text_lg,
        letterSpacing = LetterSpacings.spacing_lg,
        lineHeight = LineHeight.text_lg
    )
    val text_xl = TextStyle(
        fontSize = TextSizes.text_xl,
        letterSpacing = LetterSpacings.spacing_xl,
        lineHeight = LineHeight.text_xl
    )
    val text_2xl = TextStyle(
        fontSize = TextSizes.text_2xl,
        letterSpacing = LetterSpacings.spacing_2xl,
        lineHeight = LineHeight.text_2xl
    )
    val text_3xl = TextStyle(
        fontSize = TextSizes.text_3xl,
        letterSpacing = LetterSpacings.spacing_3xl,
        lineHeight = LineHeight.text_3xl
    )
    val text_4xl = TextStyle(
        fontSize = TextSizes.text_4xl,
        letterSpacing = LetterSpacings.spacing_4xl,
        lineHeight = LineHeight.text_4xl
    )
    val text_5xl = TextStyle(
        fontSize = TextSizes.text_5xl,
        letterSpacing = LetterSpacings.spacing_5xl,
        lineHeight = LineHeight.text_5xl
    )

}











object InputField {
    val InputFieldPlaceholderStyle = BodyLarge

}

