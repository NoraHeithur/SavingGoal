package com.nora.savinggoal.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nora.savinggoal.R
import com.nora.savinggoal.domain.model.SavingPocket
import com.nora.savinggoal.ui.theme.Color_Jasper
import com.nora.savinggoal.ui.theme.Color_Office_Green
import com.nora.savinggoal.util.StringFormatUtil.toThousandsFormat
import com.nora.savinggoal.util.StringFormatUtil.twoDecimalPos
import com.nora.savinggoal.util.calculateProgress

@Composable
fun GoalPocketItem(
    modifier: Modifier,
    pocket: SavingPocket
) {
    GoalItemContainer(
        modifier = modifier,
        borderColor = pocket.mapStatusColor(pocket.status),
        size = 200
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(all = dimensionResource(id = R.dimen.margin_padding_16))
        ) {
            PocketHeader(
                modifier = modifier,
                pocket = pocket
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            PocketProgress(modifier = modifier, goalAmount = pocket.targetAmount, remainingAmount = pocket.remainingAmount)

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            PocketName(
                modifier = modifier.weight(1f),
                name = pocket.goal
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            PocketStatus(
                modifier = modifier,
                pocket = pocket
            )
        }
    }
}

@Composable
private fun PocketHeader(
    modifier: Modifier,
    pocket: SavingPocket) {
    Column(
        modifier = modifier
    ) {
        Row {
            Icon(
                modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_default)),
                painter = painterResource(id = pocket.goalType.icon),
                contentDescription = null,
                tint = Color_Jasper
            )

            Column(
                modifier.weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                RobotoText(
                    modifier = modifier,
                    label = "${toThousandsFormat(pocket.remainingAmount)} THB",
                    size = dimensionResource(id = R.dimen.text_size_20).value.sp,
                    textColor = Color_Jasper
                )

                RobotoText(
                    modifier = modifier,
                    label = "${toThousandsFormat(pocket.targetAmount)} THB",
                    size = dimensionResource(id = R.dimen.text_size_16).value.sp,
                    textColor = Color.DarkGray
                )
            }
        }
    }
}

@Composable
private fun PocketProgress(modifier: Modifier, goalAmount: Int, remainingAmount: Int) {
    val inProgress = twoDecimalPos(calculateProgress(goalAmount, remainingAmount))
    val progress by remember { mutableFloatStateOf(inProgress) }
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .fillMaxWidth()
            .height(10.dp),
        color = Color_Jasper,
        trackColor = Color.Black,
        strokeCap = StrokeCap.Round
    )
}

@Composable
fun PocketName(
    modifier: Modifier,
    name: String
) {
    RobotoText(
        modifier = modifier,
        label = name,
        size = dimensionResource(id = R.dimen.text_size_20).value.sp,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun PocketStatus(
    modifier: Modifier,
    pocket: SavingPocket
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RobotoText(
            modifier = modifier,
            label = pocket.status,
            textColor = pocket.mapStatusColor(pocket.status),
            size = dimensionResource(id = R.dimen.text_size_14).value.sp
        )

        PocketRemaining(
            modifier = modifier,
            remaining = pocket.remainingDay.toString()
        )
    }
}

@Composable
private fun PocketRemaining(
    modifier: Modifier,
    remaining: String
) {
    Row {
        RobotoText(
            modifier = modifier,
            label = remaining,
            size = dimensionResource(id = R.dimen.text_size_14).value.sp
        )
        RobotoText(
            modifier = modifier.padding(horizontal = 4.dp),
            label = "days",
            size = dimensionResource(id = R.dimen.text_size_14).value.sp
        )
        RobotoText(
            modifier = modifier,
            label = "left",
            size = dimensionResource(id = R.dimen.text_size_14).value.sp
        )
    }
}