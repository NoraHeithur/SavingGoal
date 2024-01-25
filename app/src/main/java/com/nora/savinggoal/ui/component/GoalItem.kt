package com.nora.savinggoal.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nora.savinggoal.R
import com.nora.savinggoal.ui.theme.Color_Jasper
import com.nora.savinggoal.ui.theme.Color_Office_Green

@Composable
fun GoalItem(
    modifier: Modifier,
    isItemSelected: Boolean = false,
    label: String,
    icon: Int,
    onItemClicked: () -> Unit
) {
    val selectedItemColor = if (isItemSelected) Color_Office_Green else Color_Jasper
    GoalItemContainer(
        modifier = modifier
            .clickable { onItemClicked() },
        borderColor = selectedItemColor,
        size = 160
    ) {
        GoalCategory(modifier = modifier, label = label, color = selectedItemColor, icon = icon)
    }
}

@Composable
private fun GoalCategory(
    modifier: Modifier,
    label: String,
    color: Color,
    @DrawableRes icon: Int = R.drawable.ic_dashboard
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.margin_padding_16)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_large)),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        RobotoText(
            modifier = modifier,
            label = label,
            textColor = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoalItemPreview() {
    GoalItemContainer(modifier = Modifier, size = 160) {
        GoalCategory(modifier = Modifier, label = "label", color = Color_Jasper, icon = 0)
    }
}