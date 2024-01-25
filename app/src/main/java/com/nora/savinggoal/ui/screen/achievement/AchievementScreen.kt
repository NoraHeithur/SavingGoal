package com.nora.savinggoal.ui.screen.achievement

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.component.GoalItemContainer
import com.nora.savinggoal.ui.component.RobotoText
import com.nora.savinggoal.ui.theme.Color_Harvest_Gold
import com.nora.savinggoal.ui.theme.Color_Jasper

@Composable
fun AchievementScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        AchievementHeader(modifier = Modifier)
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
        AchievementItems(
            modifier = Modifier,
            achievements = listOf("First Saving", "1000 saving", "Finished!", "On Going", "Budget Builder")
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
    }
}

@Composable
private fun AchievementHeader(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color_Harvest_Gold)
            .padding(dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        Row(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.margin_padding_16))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_extra_large)),
                painter = painterResource(id = R.drawable.ic_achievement_2),
                contentDescription = null,
                tint = Color.White
            )

            Column {
                RobotoText(
                    modifier = modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.margin_padding_16),
                    ),
                    label = "Achievement",
                    size = dimensionResource(id = R.dimen.text_size_24).value.sp,
                    textColor = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                RobotoText(
                    modifier = modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.margin_padding_16),
                    ),
                    label = "Level 2",
                    size = 36.sp,
                    textColor = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun AchievementItem(
    modifier: Modifier,
    label: String,
) {
    GoalItemContainer(
        modifier = modifier,
        borderColor = Color_Jasper,
        size = 120
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.margin_padding_16)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier
                    .size(dimensionResource(id = R.dimen.icon_size_large))
                    .weight(1f),
                painter = painterResource(id = R.drawable.badge),
                contentDescription = null
            )

            RobotoText(
                modifier = modifier.fillMaxWidth(),
                label = label,
                size = 12.sp,
                textColor = Color.DarkGray,
                textAlignment = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun AchievementItems(modifier: Modifier, achievements: List<String>) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_8)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_8))
    ) {
        items(items = achievements) { achievement  ->
            AchievementItem(
                modifier = modifier,
                label = achievement,
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun AchievementScreenPreview() {
    AchievementScreen(modifier = Modifier)
}