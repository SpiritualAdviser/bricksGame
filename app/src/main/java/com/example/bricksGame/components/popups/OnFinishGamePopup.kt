package com.example.bricksGame.components.popups

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.bricksGame.components.popups.models.PopupsViewModel
import com.example.bricksGame.ui.theme.overlayBg

@Composable
fun WinPopup() {
    Overlay()
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WinBlock()
    }
}

@Composable
fun WinBlock(popupsViewModel:PopupsViewModel= hiltViewModel()) {
    if (popupsViewModel.levelWin.value){
        GifImage(popupsViewModel.imageAssetOnWinLevel)
    }else{
        GifImage(popupsViewModel.imageAssetOnLoseLevel)
    }
}

@Composable
fun GifImage(imageAsset: Int) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(imageAsset, imageLoader),
        contentDescription = null,
        modifier = Modifier.scale(1.5F)
    )
}

@Composable
fun Overlay() {
    Box(
        Modifier
            .fillMaxSize()
            .clickable(enabled = false, onClick = {})
            .background(overlayBg)
    ) {
    }
}