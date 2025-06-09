package com.dynamiclayer.components.fileUploader

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material3.Text
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.fileUploader.utils.FileUploaderState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun FileUploaderPreview() {
    FileUploaderSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun FileUploaderSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "File Uploader",
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
                        title = stringResource(id = R.string.state),
                        description = "You can edit the state with default or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
                            ) {
                                FileUploader(
                                    title = "Upload file",
                                    description = "Max file size is 500kb. Supported file types are .jpg, .png, and .pdf.",
                                    icon = R.drawable.ic_file_upload
                                )
                                FileUploader(
                                    title = "Upload file",
                                    description = "Max file size is 500kb. Supported file types are .jpg, .png, and .pdf.",
                                    icon = R.drawable.ic_file_upload,
                                    state = FileUploaderState.disabled
                                )
                            }
                        })
                }
            }
        })

}


@Composable
fun FileUploader(
    title: String,
    description: String,
    @DrawableRes icon: Int,
    state: FileUploaderState = FileUploaderState.default, onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                ColorPalette.Grey.grey200,
                shape = RoundedCornerShape(AppRadius.rounded_lg)
            )
            .background(color = ColorPalette.White)
            .clickable(
                enabled = state == FileUploaderState.default,
                interactionSource = null,
                indication = null, onClick = onClick
            )
            .padding(GeneralPaddings.p_24)
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(

            modifier = Modifier
                .size(72.dp)
                .border(
                    1.dp, ColorPalette.Grey.grey200, CircleShape
                )
                .background(
                    color = if (state == FileUploaderState.default) ColorPalette.White else ColorPalette.Grey.grey100,
                    shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "round icon",
                tint = if (state == FileUploaderState.default) ColorPalette.Black else ColorPalette.Grey.grey500
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_4)) {
            Text(
                text = title,
                style = if (state == FileUploaderState.default) AppTextWeight.text_base_semiBold else AppTextStrike.text_base,
                color = if (state == FileUploaderState.default) ColorPalette.Black else ColorPalette.Grey.grey500
            )
            Text(
                text = description,
                style = if (state == FileUploaderState.default) AppTextWeight.text_sm_regular else AppTextStrike.text_sm,
                color = if (state == FileUploaderState.default) ColorPalette.Black else ColorPalette.Grey.grey500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
