package com.dynamiclayer.components.bottomSheet

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.R
import com.dynamiclayer.components.bottomSheet.util.components.BottomSheetButton
import com.dynamiclayer.components.bottomSheet.util.components.BottomSheetButtonsHorizontal
import com.dynamiclayer.components.bottomSheet.util.components.BottomSheetButtonsVertical
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetContentType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetIconType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetState
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue
import com.dynamiclayer.components.bottomSheet.util.models.rememberBottomSheetState
import com.dynamiclayer.components.bottomSheet.util.models.rememberCustomBottomSheetState
import com.dynamiclayer.components.button.Button
import com.dynamiclayer.components.button.util.models.ButtonSize
import com.dynamiclayer.components.button.util.models.ButtonType
import kotlinx.coroutines.launch


enum class BottomSheetType {
    Default, SingleButton, DoubleButton, LinkAndButton
}

enum class BottomSheetButtonDirection {
    vertical, horizontal;
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BottomSheetPreview() {
    BottomSheetSample()
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun BottomSheetSample() {
    val context = LocalContext.current
    val selectedBottomSheetType = remember { mutableStateOf(BottomSheetType.Default) }

    val sheetState = rememberCustomBottomSheetState(skipPartiallyExpanded = true)

    val coroutineScope = rememberCoroutineScope()

    val title = remember {
        "title"
    }
    var currentImage by remember {
        mutableIntStateOf(R.drawable.shopping_cart)
    }
    val imageItems = remember {
        listOf(
            R.drawable.shopping_cart,
            R.drawable.onboarding,
            R.drawable.lock,
            R.drawable.money,
            R.drawable.order_complete
        )
    }


    Scaffold(containerColor = Color(0xFF404040),
        snackbarHost = {},
        topBar = {

        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.TopCenter,
            ) {
                val content: @Composable () -> Unit = {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                label = "Change type", onClick = {
                                    selectedBottomSheetType.value =
                                        when (selectedBottomSheetType.value) {
                                            BottomSheetType.Default -> BottomSheetType.SingleButton
                                            BottomSheetType.SingleButton -> BottomSheetType.DoubleButton
                                            BottomSheetType.DoubleButton -> BottomSheetType.LinkAndButton
                                            BottomSheetType.LinkAndButton -> BottomSheetType.Default
                                        }
                                }, type = ButtonType.primary, size = ButtonSize.lg
                            )
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                label = if (sheetState.currentValue == BottomSheetValue.Expanded || sheetState.currentValue == BottomSheetValue.Collapsed) "Hide" else "Show",
                                onClick = {
                                    if (sheetState.currentValue == BottomSheetValue.Expanded || sheetState.currentValue == BottomSheetValue.Collapsed) {
                                        coroutineScope.launch { sheetState.hide() }
                                    } else {
                                        coroutineScope.launch { sheetState.show() }
                                    }
                                }, type = ButtonType.secondary, size = ButtonSize.lg
                            )
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(12.dp)
                        ) {
                            items(imageItems, key = {
                                it
                            }) {
                                Image(
                                    painter = painterResource(id = it),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(110.dp)
                                        .clickable {
                                            currentImage = it
                                        }
                                )
                            }
                        }

                    }
                }

                when (selectedBottomSheetType.value) {
                    BottomSheetType.Default ->

                        BottomSheet(
                        sheetState = sheetState,
                        title = title,
                        headLine = "Headline",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.",
                        sheetContent = BottomSheetContentType.Icon(
                            icon = BottomSheetIconType.Drawable(
                                currentImage
                            )
                        ),
                        mainContent = content,
                    )

                    BottomSheetType.SingleButton -> BottomSheet(
                        sheetState = sheetState,
                        title = title,
                        headLine = "Headline",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.",
                        sheetContent = BottomSheetContentType.Icon(
                            icon = BottomSheetIconType.Drawable(
                                currentImage
                            )
                        ),
                        buttonLabel = "Save",
                        onClick = {
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                        },
                        mainContent = content,
                    )


                    BottomSheetType.DoubleButton -> BottomSheet(
                        sheetState = sheetState,
                        title = title,
                        headLine = "Headline",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.",
                        sheetContent = BottomSheetContentType.Icon(
                            icon = BottomSheetIconType.Drawable(
                                currentImage
                            )
                        ),

                        primaryButtonLabel = "Save",
                        secondaryButtonLabel = "Cancel",
                        primaryOnClick = {
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                        },
                        secondaryOnClick = {
                            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                        },
                        buttonsDirection = BottomSheetButtonDirection.vertical,
                        mainContent = content
                    )

                    BottomSheetType.LinkAndButton -> BottomSheet(
                        sheetState = sheetState,
                        title = title,
                        headLine = "Headline",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore.",
                        sheetContent = BottomSheetContentType.Icon(
                            icon = BottomSheetIconType.Drawable(
                                currentImage
                            )
                        ),
                        primaryButtonLabel = "Cancel",
                        secondaryButtonLabel = "Save",
                        primaryOnClick = {
                            Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                        },
                        secondaryOnClick = {
                            Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                        },
                        buttonsDirection = BottomSheetButtonDirection.horizontal,
                        mainContent = content,
                    )
                }
            }
        },
        bottomBar = {

        })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetState = rememberBottomSheetState(
        skipPartiallyExpanded = true,
        skipHiddenState = true
    ),
    title: String? = null,
    headLine: String,
    description: String,
    sheetContent: BottomSheetContentType = BottomSheetContentType.None,
    mainContent: (@Composable () -> Unit)? = null
) {
    GenericBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        title = title,
        headLine = headLine,
        description = description,
        sheetContent = sheetContent,
        mainContent = mainContent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetState = rememberBottomSheetState(
        skipPartiallyExpanded = true,
        skipHiddenState = true
    ),
    title: String? = null,
    headLine: String,
    description: String,
    sheetContent: BottomSheetContentType,
    primaryButtonLabel: String,
    primaryOnClick: () -> Unit,
    secondaryButtonLabel: String,
    secondaryOnClick: () -> Unit,
    buttonsDirection: BottomSheetButtonDirection,
    mainContent: (@Composable () -> Unit)? = null
) {
    GenericBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        title = title,
        headLine = headLine,
        description = description,
        sheetContent = sheetContent,
        content = {
            if (buttonsDirection == BottomSheetButtonDirection.vertical) {
                BottomSheetButtonsVertical(
                    modifier = it,
                    primaryButtonLabel = primaryButtonLabel,
                    secondaryButtonLabel = secondaryButtonLabel,
                    primaryOnClick = primaryOnClick,
                    secondaryOnClick = secondaryOnClick
                )
            } else {
                BottomSheetButtonsHorizontal(
                    modifier = it,
                    primaryButtonLabel = primaryButtonLabel,
                    secondaryButtonLabel = secondaryButtonLabel,
                    primaryOnClick = primaryOnClick,
                    secondaryOnClick = secondaryOnClick
                )
            }
        },
        mainContent = mainContent
    )
}


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetState = rememberBottomSheetState(
        skipPartiallyExpanded = true,
        skipHiddenState = true
    ),
    title: String? = null,
    headLine: String,
    description: String,
    sheetContent: BottomSheetContentType,
    buttonLabel: String,
    onClick: () -> Unit,
    mainContent: (@Composable () -> Unit)? = null
) {
    GenericBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        title = title,
        headLine = headLine,
        description = description,
        sheetContent = sheetContent,
        content = {
            BottomSheetButton(
                modifier = it,
                buttonLabel = buttonLabel,
                onClick = onClick
            )
        },
        mainContent = mainContent
    )
}


