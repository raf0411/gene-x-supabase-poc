package com.dynamiclayer.components.avatar.util.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.dynamiclayer.components.avatar.util.models.AvatarImage
import com.dynamiclayer.components.avatar.util.models.AvatarType

@Composable
fun BoxScope.AvatarUserImage(mainContent: AvatarType.Image) {
    when (val imageType = mainContent.image) {
        is AvatarImage.Bitmap -> Image(
            bitmap = imageType.bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .fillMaxSize(), contentScale = ContentScale.Crop
        )

        is AvatarImage.Url -> Image(
            painter = rememberAsyncImagePainter(imageType.url),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .fillMaxSize(), contentScale = ContentScale.Crop
        )

        is AvatarImage.Drawable -> Image(
            painter = painterResource(id = imageType.resId),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .fillMaxSize(), contentScale = ContentScale.Crop
        )
    }
}
