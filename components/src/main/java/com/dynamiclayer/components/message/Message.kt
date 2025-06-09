package com.dynamiclayer.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.message.utils.MessageStatus
import com.dynamiclayer.components.message.utils.MessageType
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing
import com.dynamiclayer.components.ui.theme.styles.message.MessageStyles
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
private
fun MessageBubble(
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .widthIn(max = 280.dp)
            .heightIn(min = 62.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(AppRadius.rounded_lg))
            .background(backgroundColor)
            .padding(GeneralPaddings.p_12)
    ) {
        content()
    }
}


@Composable
private
fun MessageText(
    text: String,
    textStyle: TextStyle
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true),
        text = text,
        style = textStyle
    )
}

@Composable
private
fun MessageSenderName(
    senderName: String,
    textStyle: TextStyle
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true),
        text = senderName,
        style = textStyle
    )
}

@Composable
private
fun MessageHourText(
    hour: String,
    color: Color
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        maxLines = 1,
        text = hour,
        style = AppTextWeight.text_xs_regular,
        color = color, textAlign = TextAlign.End
    )
}

@Composable
private
fun MessageContent(
    autor: String,
    message: String,
    formattedHour: String,
    type: MessageType,
    state: MessageStatus
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(if (state is MessageStatus.response) GeneralSpacing.p_4 else GeneralSpacing.p_2)
    ) {
        if (autor.isNotEmpty()) {
            MessageSenderName(
                autor,
                AppTextWeight.text_sm_semiBold.copy(color = type.style.textColor)
            )
        }
        if (state is MessageStatus.response) {
            MessageResponse(state.author, state.message, type.style)
        }
        MessageText(message, AppTextWeight.text_sm_regular.copy(color = type.style.textColor))
        MessageHourText(formattedHour, type.style.hourColor)
    }
}

@Composable
private
fun MessageResponse(autor: String, message: String, style: MessageStyles) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 62.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(AppRadius.rounded_md))
            .background(style.responseBackgroundColor)
            .padding(GeneralPaddings.p_12),
        verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_2)
    ) {
        if (autor.isNotBlank())
            MessageSenderName(
                autor,
                AppTextWeight.text_xs_semibold.copy(color = style.responseTextColor)
            )
        MessageText(message, AppTextWeight.text_xs_regular.copy(color = style.responseTextColor))

    }
}

@Composable
fun MessagesSample(navController: NavController? = null) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Message",
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
                        description = "You can edit the type with message or ownMessage parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.message, author = "Thomas"
                                )
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.ownMessage
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with single or response parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.message,
                                    author = "Thomas",
                                    state = MessageStatus.single
                                )
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.message,
                                    author = "Thomas",
                                    state = MessageStatus.response(
                                        author = "Andrew",
                                        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr."
                                    )
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Autor",
                        description = "You can remove the autor by removing the parameter parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.message,
                                    author = "Thomas"
                                )
                                Message(
                                    message = "Lorem ipsum dolor sit amet.",
                                    type = MessageType.message
                                )

                            }
                        })
                }
            }
        })

}

@Composable
@Preview(showBackground = true)
private fun MessagePreview() {
    MessagesSample()
}


@Composable
fun Message(
    author: String = "",
    message: String,
    time: LocalTime = LocalTime.now(),
    type: MessageType = MessageType.ownMessage,
    state: MessageStatus = MessageStatus.single
) {
    val formattedHour = time.format(DateTimeFormatter.ofPattern("h:mm a"))

    MessageBubble(type.style.backgroundColor) {
        MessageContent(
            author,
            message,
            formattedHour,
            type,
            state
        )
    }
}