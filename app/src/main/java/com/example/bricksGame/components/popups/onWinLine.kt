package com.example.bricksGame.components.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset

import com.example.bricksGame.components.popups.models.PopupsViewModel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WinLine() {

    Image(modifier = Modifier
        .offset {
            IntOffset(
                x = PopupsViewModel.xPopupWinLine.intValue,
                y = PopupsViewModel.yPopupWinLine.intValue
            )
        }
        .graphicsLayer {
            scaleX = PopupsViewModel.scalePopupWinLine.value
            scaleY = PopupsViewModel.scalePopupWinLine.value
            transformOrigin = TransformOrigin.Center
        },
        bitmap = ImageBitmap.imageResource(PopupsViewModel.imageAsset.intValue),
        contentDescription = "winImage"
    )

}

