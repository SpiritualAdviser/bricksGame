package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R


@Composable
fun LogoSprite() {

    val image = ImageBitmap.imageResource(R.drawable.and)
    Canvas(modifier = Modifier.border(1.dp, Color.Red)) {
        drawImage(image = image,
            srcOffset = IntOffset(x = 22, y = 0),
            srcSize = IntSize(width = 32, height = 32),
            dstSize = IntSize(width = 80, height = 80)
        )
    }

}