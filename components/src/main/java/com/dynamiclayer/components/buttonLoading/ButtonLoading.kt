package com.dynamiclayer.components.buttonLoading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.loadingDots.LoadingDots
import com.dynamiclayer.components.loadingDots.LoadingDotsMode
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class ButtonLoadingSize {
    lg, md, sm, xs;

    val paddings: PaddingValues
        get() = when (this) {
            lg -> PaddingValues(horizontal = GeneralSpacing.p_24, )
            md -> PaddingValues(horizontal = GeneralSpacing.p_16)
            sm -> PaddingValues(horizontal = GeneralSpacing.p_16)
            xs -> PaddingValues(horizontal = GeneralSpacing.p_12)
        }
    val height :Dp get() = when(this){
        lg -> 56.dp
        md -> 48.dp
        sm -> 40.dp
        xs -> 32.dp
    }
}

enum class ButtonLoadingType {
    primary, secondary, tertiary, ghost;

    val buttonColor: Color
        get() = when (this) {
            primary -> ColorPalette.Voilet.color500
            secondary -> ColorPalette.Grey.grey800
            tertiary -> ColorPalette.White
            ghost -> Color.Transparent
        }
    val contentColor: Color
        get() = when (this) {
            primary  -> ColorPalette.OriginalWhite
            secondary -> ColorPalette.White
            tertiary -> ColorPalette.Black
            ghost -> ColorPalette.Voilet.color500
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ButtonLoadingPreview() {
    ButtonLoadingSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun ButtonLoadingSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Button Loading",
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
                        title = "Type",
                        description = "You can edit the type with primary, secondary or tertiary parameter.",
                        content = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(
                                    GeneralSpacing.p_16
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ButtonLoading(type = ButtonLoadingType.primary, size = ButtonLoadingSize.lg) { }
                                    ButtonLoading(type = ButtonLoadingType.secondary, size = ButtonLoadingSize.lg) { }
                                    ButtonLoading(type = ButtonLoadingType.tertiary, size = ButtonLoadingSize.lg) { }
                                }
                                ButtonLoading(type = ButtonLoadingType.ghost, size = ButtonLoadingSize.lg) { }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Size",
                        description = "You can edit the size with xs, sm, md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(
                                    GeneralSpacing.p_16
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {
                                    ButtonLoading(type = ButtonLoadingType.primary, size = ButtonLoadingSize.lg) { }
                                    ButtonLoading(type = ButtonLoadingType.primary, size = ButtonLoadingSize.md) { }
                                    ButtonLoading(type = ButtonLoadingType.primary, size = ButtonLoadingSize.sm) { }
                                }
                                ButtonLoading(type = ButtonLoadingType.primary, size = ButtonLoadingSize.xs) { }
                            }
                        })
                }


            }
        })

}

@Composable
fun ButtonLoading(
    modifier: Modifier = Modifier,
    type: ButtonLoadingType,
    size: ButtonLoadingSize,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppRadius.rounded_md))
            .then(
                if (type == ButtonLoadingType.tertiary) Modifier.border(
                    1.dp, color = ColorPalette.Grey.grey200, shape = RoundedCornerShape(
                        AppRadius.rounded_md
                    )
                ) else {
                    Modifier
                }
            )
            .background(type.buttonColor)
            .clickable(null, null, onClick = onClick)
            .height(size.height)
            .padding(size.paddings), contentAlignment = Alignment.Center
    ) {
        LoadingDots(mode = LoadingDotsMode.custom(type.contentColor))
    }
}

