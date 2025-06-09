package com.dynamiclayer.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.card.util.models.CardSize
import com.dynamiclayer.components.card.util.models.CardState
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppRadius
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralPaddings
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CardPreview() {
    CardSample()
}

@ExperimentalMaterial3Api
@Composable
fun CardSample(navController: NavController? = null) {
    fun updateCardState(state: MutableState<CardState>){
        when(state.value){
            CardState.Default -> state.value= CardState.Active
            CardState.Active -> state.value= CardState.Default
            CardState.disabled -> {
                //Nothing
            }
        }
    }
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Card",
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
                        title = "Size",
                        description = "You can edit the size with md or lg parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    val mdCardState = remember {
                                        mutableStateOf(CardState.Default)
                                    }
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.md, onClick = {
                                            updateCardState(mdCardState)
                                        }, modifier = Modifier.weight(1f), state = mdCardState.value, iconTint = ColorPalette.Black
                                    )
                                    val lgCardState = remember {
                                        mutableStateOf(CardState.Default)
                                    }
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.lg, onClick = {
                                            updateCardState(lgCardState)

                                        }, modifier = Modifier.weight(1f), state = lgCardState.value,iconTint = ColorPalette.Black
                                    )


                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default, active or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    val lgCardState1 = remember {
                                        mutableStateOf(CardState.Default)
                                    }
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.lg, onClick = {
                                            updateCardState(lgCardState1)
                                        }, modifier = Modifier.weight(1f), state = lgCardState1.value,iconTint = ColorPalette.Black
                                    )
                                    val lgCardState2 = remember {
                                        mutableStateOf(CardState.Active)
                                    }
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.lg, onClick = {
                                            updateCardState(lgCardState2)
                                        }, modifier = Modifier.weight(1f), state = lgCardState2.value,iconTint = ColorPalette.Black
                                    )


                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.lg,
                                        onClick = {

                                        },
                                        modifier = Modifier.weight(1f),
                                        state = CardState.disabled,iconTint = ColorPalette.Black
                                    )
                                    Box(
                                        modifier = Modifier.weight(1f)
                                    )


                                }

                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Description",
                        description = "You can edit the description with true or false parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    val lgCardState = remember {
                                        mutableStateOf(CardState.Default)
                                    }
                                    Card(
                                        title = "Title",
                                        icon = R.drawable.ic_crop,
                                        description = "Description",
                                        size = CardSize.lg, onClick = {
                                            updateCardState(lgCardState)
                                        }, modifier = Modifier.weight(1f), state = lgCardState.value, descriptionEnable = false,iconTint = ColorPalette.Black
                                    )
                                    Box(
                                        modifier = Modifier.weight(1f)
                                    )

                                }

                            }
                        })
                }

            }
        })
}


@Composable
fun Card(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes
    icon: Int,
    iconTint:Color = Color.Unspecified,
    descriptionEnable: Boolean = true,
    description: String,
    size: CardSize,
    state: CardState = CardState.Default,
    onClick: () -> Unit
) {
    when (size) {
        CardSize.md -> MdCard(
            modifier = modifier,
            title = title,
            descriptionEnable = descriptionEnable,
            description = description,
            state = state, onClick = onClick, icon = icon,iconTint = iconTint
        )

        CardSize.lg -> LgCard(
            modifier = modifier,
            title = title,
            descriptionEnable = descriptionEnable,
            description = description,
            state = state, onClick = onClick, icon = icon,iconTint = iconTint
        )
    }
}

@Composable
private fun MdCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes
    icon: Int,
    descriptionEnable: Boolean,
    description: String,
    state: CardState,
    onClick: () -> Unit,
    iconTint: Color
) {
    val titleTextStyle = state.titleStyle
    val descriptionTextStyle = state.descriptionStyle
    CardLayout(
        modifier = modifier,
        size = CardSize.md,
        state = state
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clickable(enabled = state != CardState.disabled, interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null) { onClick() }
                .padding(GeneralPaddings.p_12),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_12)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = if (state == CardState.disabled) ColorPalette.Grey.grey500 else iconTint

            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = titleTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                if (descriptionEnable) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = description,
                        style = descriptionTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}


@Composable
private fun LgCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes
    icon: Int,
    descriptionEnable: Boolean,
    description: String,
    state: CardState,
    onClick: () -> Unit,
    iconTint: Color,
) {
    val titleTextStyle = state.titleStyle
    val descriptionTextStyle = state.descriptionStyle
    CardLayout(
        modifier = modifier,
        size = CardSize.lg,
        state = state
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clickable(enabled = state != CardState.disabled, interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null) { onClick() }
                .padding(GeneralPaddings.p_16),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = if (state == CardState.disabled) ColorPalette.Grey.grey500 else iconTint
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = titleTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                if (descriptionEnable) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = description,
                        style = descriptionTextStyle, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun CardLayout(
    modifier: Modifier = Modifier,
    size: CardSize,
    state: CardState,
    content: @Composable () -> Unit
) {
    val cardHeight = size.height

    val cardBorder = state.cardBorder


    Box(
        modifier = modifier
            .sizeIn(maxHeight = cardHeight)
            .wrapContentHeight()
            .wrapContentWidth()
            .border(
                border = cardBorder,
                shape = RoundedCornerShape(AppRadius.rounded_lg)
            )
            .background(
                color = ColorPalette.White,
                shape = RoundedCornerShape(AppRadius.rounded_lg)
            )
    ) {
        content()
    }

}