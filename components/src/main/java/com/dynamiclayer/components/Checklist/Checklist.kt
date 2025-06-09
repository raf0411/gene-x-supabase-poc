package com.dynamiclayer.components.Checklist

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dynamiclayer.components.Checklist.utils.CheckListState
import com.dynamiclayer.components.Checklist.utils.CheckListType
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.checkbox.Checkbox
import com.dynamiclayer.components.checkbox.CheckboxState
import com.dynamiclayer.components.exampleView.components.DetailContainer
import com.dynamiclayer.components.radioButton.RadioButton
import com.dynamiclayer.components.radioButton.utils.RadioButtonState
import com.dynamiclayer.components.topNavigation.TopNavigation
import com.dynamiclayer.components.topNavigation.utils.TopNavigationSize
import com.dynamiclayer.components.topNavigation.utils.models.TopNavigationIconType
import com.dynamiclayer.components.ui.theme.AppTextStrike
import com.dynamiclayer.components.ui.theme.AppTextWeight
import com.dynamiclayer.components.ui.theme.ColorPalette
import com.dynamiclayer.components.ui.theme.GeneralSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CheckListPreview() {
    CheckListSample(null)
}

@ExperimentalMaterial3Api
@Composable
fun CheckListSample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Check List",
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
                        description = "You can edit the type with title, checkbox or radioButton parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                Checklist(title = "CheckList title")
                                Checklist(
                                    title = "CheckList title",
                                    type = CheckListType.radioButton,
                                    isCheck = remember {
                                        mutableStateOf(false)
                                    })
                                Checklist(
                                    title = "CheckList title",
                                    type = CheckListType.checkbox,
                                    isCheck = remember {
                                        mutableStateOf(false)
                                    })
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "State",
                        description = "You can edit the state with default or disabled parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16)
                            ) {
                                Checklist(
                                    title = "CheckList title",
                                    type = CheckListType.radioButton,
                                    isCheck = remember {
                                        mutableStateOf(false)
                                    })
                                Checklist(
                                    title = "CheckList title",
                                    type = CheckListType.radioButton,
                                    isCheck = remember {
                                        mutableStateOf(false)
                                    }, state = CheckListState.disabled
                                )
                            }
                        })
                }
            }
        })

}

@Composable
fun Checklist(
    modifier: Modifier = Modifier,
    title: String,
    type: CheckListType = CheckListType.Title,
    state: CheckListState = CheckListState.default,
    onClick: () -> Unit = {},
    isCheck: MutableState<Boolean> = mutableStateOf(false)
) {
    Column(
        modifier = modifier
            .clickable(
                enabled = state == CheckListState.default,
                onClick = onClick
            )
            .background(color = if (type == CheckListType.Title) ColorPalette.Grey.grey200 else ColorPalette.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = state != CheckListState.disabled,
                    interactionSource = null,
                    indication = null,
                    onClick = {
                        isCheck.value = !isCheck.value
                    }
                )
                .padding(vertical = GeneralSpacing.p_12, horizontal = GeneralSpacing.p_16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = if (state == CheckListState.default) AppTextWeight.text_base_medium else AppTextStrike.text_base,
                color = if (state == CheckListState.default) ColorPalette.Black else ColorPalette.Grey.grey500
            )
            when (type) {
                CheckListType.checkbox -> {
                    Checkbox(
                        isActive = isCheck.value,
                        onCheckChange = {
                            isCheck.value = it
                        },
                        state = if (state == CheckListState.disabled) CheckboxState.disabled else CheckboxState.default
                    )
                }

                CheckListType.radioButton -> {
                    RadioButton(
                        isSelected = isCheck.value,
                        onClick = {
                            isCheck.value = !isCheck.value
                        },
                        state = if (state == CheckListState.disabled) RadioButtonState.disabled else RadioButtonState.default
                    )
                }

                else -> {}
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorPalette.Grey.grey200)
        )

    }
}