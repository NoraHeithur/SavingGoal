package com.nora.savinggoal.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.theme.Color_Jasper

@Composable
fun GoalItemContainer(
    modifier: Modifier,
    borderColor: Color = Color_Jasper,
    size: Int,
    content: @Composable () -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .size(size.dp)
            .wrapContentSize(unbounded = false),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_size_small)
        ),
        border = BorderStroke(dimensionResource(id = R.dimen.stroke_size_medium), borderColor)
    ) {
        content()
    }
}