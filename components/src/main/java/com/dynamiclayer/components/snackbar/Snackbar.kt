package com.dynamiclayer.components.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing

enum class SnackbarType(@DrawableRes val icon: Int) {
    success(R.drawable.s_success),
    error(R.drawable.s_error),
    warning(R.drawable.s_warning),
    information(
        R.drawable.s_information
    );
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun SnackbarPreview() {
    SnackbarSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun SnackbarSample(navController: NavController?) {
    Scaffold(
        containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Snackbar",
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
                        description = "You can edit the type with success, error, warning or information parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(
                                        GeneralSpacing.p_16
                                    )
                                ) {
                                    Snackbar(label = "Success", type = SnackbarType.success)
                                    Snackbar(label = "Error", type = SnackbarType.error)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(
                                        GeneralSpacing.p_16
                                    )
                                ) {
                                    Snackbar(label = "Warning", type = SnackbarType.warning)
                                    Snackbar(label = "Information", type = SnackbarType.information)
                                }
                            }
                        })
                }


            }
        })


}

@Composable
fun Snackbar(modifier: Modifier = Modifier, label: String, type: SnackbarType) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppRadius.rounded_md))
            .border(1.dp, ColorPalette.Grey.grey200, RoundedCornerShape(AppRadius.rounded_md))
            .background(color = ColorPalette.White)
            .padding(vertical = GeneralSpacing.p_12)
            .padding(start = GeneralSpacing.p_12, end = GeneralSpacing.p_20),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
    ) {
        Image(
            painter = painterResource(type.icon),
            contentDescription = type.name,
            modifier = Modifier.sizeIn(maxWidth = 24.dp, maxHeight = 24.dp).drawBehind {
                if(type!= SnackbarType.warning){
                    drawCircle(color = Color.White, radius = size.minDimension/4f)
                }
            }
        )
        Text(label, style = AppTextWeight.text_base_bold, color = ColorPalette.Black)
    }

}