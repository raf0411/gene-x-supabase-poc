package com.dynamiclayer.components.message.utils

sealed class MessageStatus {
    data object single : MessageStatus()
    data class response(val author: String, val message: String) : MessageStatus() {
        constructor(message: String) : this("", message)
    }

}