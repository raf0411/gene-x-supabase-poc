package com.dynamiclayer.components.badgeActivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.Text
import com.dynamiclayer.components.R
import com.dynamiclayer.components.SampleActivity.Companion.onDarkButtonClick
import com.dynamiclayer.components.badgeActivity.utils.BadgeDirection
import com.dynamiclayer.components.badgeActivity.utils.BadgeType
import com.dynamiclayer.components.exampleView.components.DetailContainer
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
private fun BadgeActivityPreview() {
    BadgeActivitySample(null)
}

@ExperimentalMaterial3Api
@Composable
fun BadgeActivitySample(navController: NavController?) {
    Scaffold(containerColor = ColorPalette.White,
        snackbarHost = {},
        topBar = {
            TopNavigation(
                title = "Badge Activity",
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
                        title = stringResource(id = R.string.type),
                        description = "You can edit the type with the comment, like or commentLike parameter.",
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {

                                BadgeActivity(
                                    type = BadgeType.like,
                                    direction = BadgeDirection.bottom, like = 31
                                )
                                BadgeActivity(
                                    type = BadgeType.comment,
                                    direction = BadgeDirection.bottom, comment = 31
                                )
                                BadgeActivity(
                                    type = BadgeType.commentLike,
                                    direction = BadgeDirection.bottom, like = 4, comment = 31
                                )
                            }
                        })
                }
                item {
                    DetailContainer(
                        title = "Direction",
                        description = "You can edit the direction with the bottom, top, right or left parameter.",
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = GeneralSpacing.p_16),
                                verticalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_16)
                                ) {

                                    BadgeActivity(
                                        type = BadgeType.commentLike,
                                        direction = BadgeDirection.top, like = 4, comment = 31
                                    )
                                    BadgeActivity(
                                        type = BadgeType.commentLike,
                                        direction = BadgeDirection.bottom,
                                        like = 4,
                                        comment = 31
                                    )
                                    BadgeActivity(
                                        type = BadgeType.commentLike,
                                        direction = BadgeDirection.left, like = 4, comment = 31
                                    )
                                }
                                BadgeActivity(
                                    type = BadgeType.commentLike,
                                    direction = BadgeDirection.right, like = 4, comment = 31
                                )
                            }
                        })
                }
            }
        })
}

@Composable
fun BadgeActivity(
    modifier: Modifier = Modifier,
    type: BadgeType,
    direction: BadgeDirection,
    like: Int = 0,
    comment: Int = 0
) {
    if (direction == BadgeDirection.left || direction == BadgeDirection.right) {
        Row(modifier = modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
            if (direction == BadgeDirection.left) {
                Image(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_hr),
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(180f)
                        .offset(x = (-1).dp)
                )

            }
            BadgeContent(
                badgeType = type,
                like = like,
                comment = comment
            )
            if (direction == BadgeDirection.right) {
                Image(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_hr),
                    contentDescription = "",
                    modifier = Modifier
                        .offset(x = (-1).dp)

                )
            }
        }
    } else {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (direction == BadgeDirection.top) {
                Image(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_up),
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(180f)
                        .offset(y = (-1).dp)
                )

            }
            BadgeContent(
                badgeType = type,
                like = like,
                comment = comment
            )
            if (direction == BadgeDirection.bottom) {
                Image(
                    painter = painterResource(id = R.drawable.ic_badge_mark_arrow_up),
                    contentDescription = "", modifier = Modifier.offset(y = (-1).dp)
                )
            }
        }
    }
}

@Composable
private fun BadgeContent(
    badgeType: BadgeType, like: Int = 0,
    comment: Int = 0
) {
    Box(
        modifier = Modifier
            .background(
                color = ColorPalette.Red.color500,
                shape = RoundedCornerShape(AppRadius.rounded_md)
            )
            .padding(GeneralPaddings.p_8), contentAlignment = Alignment.Center
    ) {
        when (badgeType) {
            BadgeType.like -> BadgeItem(icon = R.drawable.ic_heart, count = like)
            BadgeType.comment -> BadgeItem(icon = R.drawable.ic_comment, count = comment)
            BadgeType.commentLike -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BadgeItem(icon = R.drawable.ic_heart, count = like)
                    BadgeItem(icon = R.drawable.ic_comment, count = comment)
                }
            }
        }
    }
}

@Composable
private fun BadgeItem(icon: Int, count: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(GeneralSpacing.p_2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null, modifier = Modifier.size(16.dp)
        )
        Text(text = "$count", style = AppTextWeight.text_sm_semiBold, color = ColorPalette.OriginalWhite)
    }
}
