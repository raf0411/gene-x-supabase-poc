package com.dynamiclayer.components.messageDock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.messageDock.utils.MessageDockState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun MessageDockPreview() {
    MessageDockSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun MessageDockSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Message Dock",
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
                        description = "You can edit the state with default or active parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_32)
                            ) {
                                MessageDock(state = MessageDockState.default, text = "")
                                MessageDock(state = MessageDockState.active, text = "Message")

                            }
                        })
                }
            }
        })

}

@Composable
fun MessageDock(
    state: MessageDockState = MessageDockState.default,
    text: String

    ) {
    val inputText = remember {
        mutableStateOf(text)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val currentState by remember(isFocused) {
        derivedStateOf {
            if (isFocused) {
                MessageDockState.active
            } else {
                state
            }
        }
    }
    val shape = remember {
        RoundedCornerShape(AppRadius.rounded_md)
    }
    val paddings by remember(currentState) {
        derivedStateOf {
            if (currentState == MessageDockState.default) {
                PaddingValues(horizontal = GeneralSpacing.p_16, vertical = GeneralSpacing.p_12)
            } else {
                PaddingValues(
                    top = GeneralSpacing.p_8,
                    bottom = GeneralSpacing.p_8,
                    end = GeneralSpacing.p_8,
                    start = GeneralSpacing.p_16
                )
            }
        }

    }
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )
        Row(
            modifier = Modifier
                .padding(GeneralPaddings.p_16)
                .fillMaxWidth()
                .border(1.dp, color = ColorPalette.Grey.grey200, shape = shape)
                .background(color = ColorPalette.White)
                .padding(paddings),
            horizontalArrangement = Arrangement.spacedBy(
                GeneralSpacing.p_16
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentState == MessageDockState.default) {
                Icon(
                    painter = painterResource(id = R.drawable.photo_camera),
                    contentDescription = "photo Camera", tint = ColorPalette.Grey.grey500,
                    modifier = Modifier.clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = {}
                    )
                )
            }
            BasicTextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                decorationBox = @Composable { innerTextField ->
                    if (inputText.value.isEmpty()) {
                        Text(
                            text = "Write a message...",
                            style = AppTextWeight.text_sm_regular,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = ColorPalette.Grey.grey500
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier.weight(1f),
                textStyle = AppTextWeight.text_sm_regular.copy(color = ColorPalette.Black),
                interactionSource = interactionSource, cursorBrush = SolidColor(ColorPalette.Black)
            )
            if (currentState == MessageDockState.default) {
                Icon(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "photo Camera",
                    tint = ColorPalette.Grey.grey500,
                    modifier = Modifier.clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = {}
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.microphone),
                    contentDescription = "photo Camera",
                    tint = ColorPalette.Grey.grey500,
                    modifier = Modifier.clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = {}
                    )
                )
            }
            if (currentState == MessageDockState.active) {
                Box(
                    modifier = Modifier
                        .sizeIn(48.dp, 32.dp)
                        .clip(RoundedCornerShape(AppRadius.rounded))
                        .background(color = ColorPalette.Black)
                        .padding(vertical = GeneralSpacing.p_4, horizontal = GeneralSpacing.p_12)
                        .clickable(
                            interactionSource = null,
                            indication = null,
                            onClick = {}
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_up_right),
                        contentDescription = "photo Camera", tint = ColorPalette.White
                    )
                }
            }

        }
    }
}


