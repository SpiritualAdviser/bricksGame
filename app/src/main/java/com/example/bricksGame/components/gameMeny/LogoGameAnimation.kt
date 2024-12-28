package com.example.bricksGame.components.gameMeny

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.bricksGame.R

@Composable
fun Logo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Caves(R.drawable.caves)
        And(R.drawable.and)
        Crystals(R.drawable.crystals)
    }
}


@Composable
fun Caves(imageAsset: Int) {
    Image(
        bitmap = ImageBitmap.imageResource(imageAsset),
        contentDescription = null,
        modifier = Modifier.scale(1.3F)
    )
}

@Composable
fun And(imageAsset: Int) {

    Image(
        bitmap = ImageBitmap.imageResource(imageAsset),
        contentDescription = null,
        modifier = Modifier.scale(0.8F)
    )
}

@Composable
fun Crystals(imageAsset: Int) {

    Image(
        bitmap = ImageBitmap.imageResource(imageAsset),
        contentDescription = null,
        modifier = Modifier.scale(1.3F)
    )
}