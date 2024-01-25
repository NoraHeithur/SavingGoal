package com.nora.savinggoal.ui.screen.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.component.GoalPocketItem
import com.nora.savinggoal.ui.component.ImageBanner
import com.nora.savinggoal.ui.component.RobotoText
import com.nora.savinggoal.ui.model.ImageModel
import com.nora.savinggoal.ui.screen.new_goal.NewGoalViewModel
import com.nora.savinggoal.ui.theme.Color_Harvest_Gold
import com.nora.savinggoal.ui.theme.Color_Jasper
import com.nora.savinggoal.util.StringFormatUtil
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    onNewGoalButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader(modifier = Modifier)

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
            onClick = { onNewGoalButtonClicked() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color_Jasper
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8))
        ) {
            RobotoText(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.margin_padding_8)),
                label = "+ New Goal",
                size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                textColor = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        HomeBanner(modifier = Modifier)
    }
}

@Composable
private fun HomeHeader(
    modifier: Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color_Harvest_Gold)
            .padding(vertical = dimensionResource(id = R.dimen.margin_padding_16))
    ) {

        val goalState by viewModel.goalItemState.collectAsStateWithLifecycle()

        val coroutineScope = rememberCoroutineScope()
        
        LaunchedEffect(Unit) {
            viewModel.getAllSavingPocket()
        }

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_16))
        ) {
            items(items = goalState) { pocket ->
                GoalPocketItem(modifier = modifier, pocket)
            }
        }

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        Row(
            modifier = modifier
                .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RobotoText(
                modifier = modifier.weight(1f),
                label = "${goalState.size} Goals",
                size = dimensionResource(id = R.dimen.text_size_24).value.sp,
                textColor = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            RobotoText(
                modifier = modifier.align(Alignment.Bottom),
                label = "All Saving",
                textColor = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            RobotoText(
                modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_padding_8)),
                label = StringFormatUtil.toThousandsFormat(goalState.sumOf { it.remainingAmount }),
                textColor = Color.White,
                size = dimensionResource(id = R.dimen.text_size_24).value.sp,
                fontWeight = FontWeight.SemiBold
            )

            RobotoText(
                modifier = modifier.align(Alignment.Bottom),
                label = "THB",
                textColor = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun HomeBanner(modifier: Modifier) {
    val bannerA = listOf(
        ImageModel(resId = R.drawable.img_backpacker),
        ImageModel(resId = R.drawable.img_cooperate),
        ImageModel(resId = R.drawable.img_nature),
        ImageModel(resId = R.drawable.img_town_at_night),
        ImageModel(resId = R.drawable.img_night_in_town)
    )

    val bannerB = listOf(
        ImageModel(resId = R.drawable.img_porche),
        ImageModel(resId = R.drawable.img_food),
        ImageModel(resId = R.drawable.img_cooperate),
        ImageModel(resId = R.drawable.img_backpacker),
        ImageModel(resId = R.drawable.img_night_in_town)
    )

    Column {
        RobotoText(
            modifier = modifier.padding(dimensionResource(id = R.dimen.margin_padding_16)),
            label = "Best Offer",
            size = dimensionResource(id = R.dimen.text_size_20).value.sp
        )

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_16))
        ) {
            items(
                items = bannerA,
                key = {
                    it.id
                }
            ) { offer ->
                ImageBanner(modifier = modifier, banners = offer.resId)
            }
        }

        RobotoText(
            modifier = modifier.padding(dimensionResource(id = R.dimen.margin_padding_16)),
            label = "Suggest for you",
            size = dimensionResource(id = R.dimen.text_size_20).value.sp
        )

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_padding_16))
        ) {
            items(
                items = bannerB,
                key = {
                    it.id
                }
            ) { offer ->
                ImageBanner(modifier = modifier, banners = offer.resId)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier) {

    }
}