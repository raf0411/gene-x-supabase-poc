package kalbe.corp.genexsupabasepoc.utils

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder

object CoilLoader {
    fun get(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}