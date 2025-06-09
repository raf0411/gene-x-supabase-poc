package com.dynamiclayer.components.bottomSheet.util.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Immutable
import androidx.compose.ui.window.SecureFlagPolicy

@ExperimentalMaterial3Api
class BottomSheetProperties(
    val securePolicy: SecureFlagPolicy,
    val isFocusable: Boolean,
    val shouldDismissOnBackPress: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BottomSheetProperties) return false

        if (securePolicy != other.securePolicy) return false
        if (isFocusable != other.isFocusable) return false
        if (shouldDismissOnBackPress != other.shouldDismissOnBackPress) return false

        return true
    }

    override fun hashCode(): Int {
        var result = securePolicy.hashCode()
        result = 31 * result + isFocusable.hashCode()
        result = 31 * result + shouldDismissOnBackPress.hashCode()
        return result
    }
}

@Immutable
@ExperimentalMaterial3Api
object BottomSheetDefaultsProperties {

    fun properties(
        securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
        isFocusable: Boolean = true,
        shouldDismissOnBackPress: Boolean = true
    ) = BottomSheetProperties(securePolicy, isFocusable, shouldDismissOnBackPress)
}