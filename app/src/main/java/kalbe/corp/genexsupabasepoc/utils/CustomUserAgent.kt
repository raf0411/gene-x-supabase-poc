package kalbe.corp.genexsupabasepoc.utils

import android.os.Build

fun getCustomUserAgent(): String {
    val appName = "GeneX"
    val appVersion = "1.0.0"

    val osVersion = Build.VERSION.RELEASE
    val deviceModel = Build.MODEL
    val deviceManufacturer = Build.MANUFACTURER
    val sdkInt = Build.VERSION.SDK_INT

    return "$appName/$appVersion " +
            "(Android/$osVersion; SDK/$sdkInt; $deviceManufacturer/$deviceModel) "
}