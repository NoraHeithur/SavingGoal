package com.nora.savinggoal.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.theme.RobotoFont

@Composable
fun RobotoText(
    modifier: Modifier,
    label: String,
    size: TextUnit = dimensionResource(id = R.dimen.text_size_16).value.sp,
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlignment: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        modifier = modifier,
        text = label,
        fontSize = size,
        fontWeight = fontWeight,
        fontFamily = RobotoFont,
        color = textColor,
        textAlign = textAlignment,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun RobotoText(
    modifier: Modifier,
    label: Int,
    size: TextUnit = dimensionResource(id = R.dimen.text_size_16).value.sp,
    textColor: Color,
    fontWeight: FontWeight,
    textAlignment: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        modifier = modifier,
        text = stringResource(id = label),
        fontSize = size,
        fontWeight = fontWeight,
        fontFamily = RobotoFont,
        color = textColor,
        textAlign = textAlignment,
        maxLines = maxLines,
        overflow = overflow
    )
}