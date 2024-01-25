package com.nora.savinggoal.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nora.savinggoal.R

@Composable
fun ImageBanner(modifier: Modifier, banners: Int) {
    AsyncImage(
        modifier = modifier.size(width = 200.dp, height = 100.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(banners)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
     )
}


@Preview
@Composable
fun BannerPreview() {
    ImageBanner(modifier = Modifier, banners = R.drawable.img_backpacker)
}