package com.dynamiclayer.components.bottomSheet.util.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetContentType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetIconType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetImageType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetState
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType

import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing

@Composable
fun BottomSheetHandle() {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(5.dp)
                .background(
                    ColorPalette.Grey.grey200,
                    RoundedCornerShape(4.dp)
                ),
        )
    }
}

@Composable
fun BottomSheetIconButton(
    modifier: Modifier = Modifier,
    icon: BottomSheetContentType.Icon
) {
    when (val iconType = icon.icon) {
        is BottomSheetIconType.Vector -> Icon(
            imageVector = iconType.imageVector,
            contentDescription = null,
            tint = iconType.tint, modifier = modifier
        )

        is BottomSheetIconType.Drawable -> Icon(
            painter = painterResource(id = iconType.drawable),
            contentDescription = null,
            tint = iconType.tint, modifier = modifier
        )
    }
}

@Composable
fun BottomSheetImage(
    modifier: Modifier = Modifier,
    image: BottomSheetContentType.Image
) {
    when (val imageType = image.image) {
        is BottomSheetImageType.Bitmap -> Image(
            modifier = modifier
                .size(240.dp)
               ,
            bitmap = imageType.bitmap.asImageBitmap(),
            contentScale = imageType.contentScale,
            contentDescription = null,
        )

        is BottomSheetImageType.Url -> Image(
            modifier = modifier
                .size(240.dp)
                ,
            painter = rememberAsyncImagePainter(imageType.url),
            contentScale = imageType.contentScale,
            contentDescription = null,
        )
    }
}

@Composable
fun BottomSheetImageContent(
    modifier: Modifier = Modifier,
    content: BottomSheetContentType = BottomSheetContentType.None,
) {
    if (content != BottomSheetContentType.None) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
             , contentAlignment = Alignment.Center
        ) {
            when (content) {
                is BottomSheetContentType.Icon ->
                    BottomSheetIconButton(icon = content)

                is BottomSheetContentType.Image ->
                    BottomSheetImage(image = content)

                is BottomSheetContentType.None -> {}
            }
        }
    }
}

@Composable
fun BottomSheetMainContent(
    modifier: Modifier = Modifier,
    headLine: String,
    description: String,
    content: BottomSheetContentType = BottomSheetContentType.None
) {
    Column(
        modifier = modifier
            .padding(horizontal = GeneralSpacing.p_16)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (content != BottomSheetContentType.None) {
            BottomSheetImageContent(
                content = content
            )
        }
        BottomSheetTextContent(
            headLine = headLine,
            description = description,
        )
    }
}

@Composable
fun BottomSheetTextContent(
    modifier: Modifier = Modifier,
    headLine: String,
    description: String
) {
    Column(
        modifier = modifier
            .padding(vertical = GeneralSpacing.p_32)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = headLine,
            style = AppTextWeight.text_xl_semiBold,
            color = ColorPalette.Black,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = AppTextWeight.text_base_regular,
            color = ColorPalette.Grey.grey500,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSheetTitle(
    title: String? = null
) {
    if (title != null) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = AppTextWeight.text_base_semiBold,
            color = ColorPalette.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSheetTop(
    modifier: Modifier = Modifier,
    title: String?
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GeneralPaddings.p_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                GeneralSpacing.p_16,
                Alignment.CenterVertically
            )
        ) {
            BottomSheetHandle()
            BottomSheetTitle(title = title)
        }
    }
}

@Composable
fun BottomSheetButtonsVertical(
    modifier: Modifier = Modifier,
    primaryButtonLabel: String,
    secondaryButtonLabel: String,
    primaryOnClick: () -> Unit,
    secondaryOnClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(GeneralPaddings.p_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            label = primaryButtonLabel,
            onClick = primaryOnClick, type = ButtonType.primary, size = ButtonSize.lg
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            label = secondaryButtonLabel,
            onClick = secondaryOnClick, type = ButtonType.tertiary, size = ButtonSize.lg
        )
    }
}

@Composable
fun BottomSheetButtonsHorizontal(
    modifier: Modifier = Modifier,
    primaryButtonLabel: String,
    secondaryButtonLabel: String,
    primaryOnClick: () -> Unit,
    secondaryOnClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(GeneralPaddings.p_16),
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .weight(1f),
            label = primaryButtonLabel,
            onClick = primaryOnClick, type = ButtonType.tertiary, size = ButtonSize.lg
        )
        Button(
            modifier = Modifier
                .weight(1f),
            label = secondaryButtonLabel,
            onClick = secondaryOnClick, type = ButtonType.primary, size = ButtonSize.lg
        )
    }
}

@Composable
fun BottomSheetButton(
    modifier: Modifier = Modifier,
    buttonLabel: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(GeneralPaddings.p_16),
        label = buttonLabel,
        onClick = onClick, type = ButtonType.primary, size = ButtonSize.lg
    )
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
private fun Scrim(
    color: Color,
    onDismissRequest: () -> Unit,
    sheetState: BottomSheetState
) {
    val alpha by animateFloatAsState(
        targetValue = if (sheetState.isVisible) 1f else 0f,
        animationSpec = TweenSpec(), label = ""
    )
    val dismissSheet = if (sheetState.isVisible) {
        Modifier
            .pointerInput(onDismissRequest) {
                detectTapGestures { onDismissRequest() }
            }
            .clearAndSetSemantics {}
    } else {
        Modifier
    }
    val scrimModifier = if (sheetState.isVisible) {
        Modifier.fillMaxSize()
    } else {
        Modifier
    }
    Canvas(
        modifier = scrimModifier.then(dismissSheet)
    ) {
        drawRect(color = color, alpha = alpha)
    }
}