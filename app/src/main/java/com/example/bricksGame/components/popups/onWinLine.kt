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
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.bricksGame.components.popups.models.PopupsViewModel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WinLine(popupsViewModel:PopupsViewModel= hiltViewModel()) {

    Image(modifier = Modifier
        .offset {
            IntOffset(
                x = popupsViewModel.xPopupWinLine.intValue,
                y = popupsViewModel.yPopupWinLine.intValue
            )
        }
        .graphicsLayer {
            scaleX = popupsViewModel.scalePopupWinLine.value
            scaleY = popupsViewModel.scalePopupWinLine.value
            transformOrigin = TransformOrigin.Center
        },
        bitmap = ImageBitmap.imageResource(popupsViewModel.imageAsset.intValue),
        contentDescription = "winImage"
    )

}

