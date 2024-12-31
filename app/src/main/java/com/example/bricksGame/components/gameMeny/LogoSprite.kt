package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.bricksGame.helper.SpriteAnimation


@Composable
fun LogoSprite() {

    val image = SpriteAnimation.animations[0].imageSheet.asImageBitmap()
    Canvas(modifier = Modifier.border(1.dp, Color.Red)) {
        drawImage(image = image,
            srcOffset = IntOffset(x = 22, y = 0),
            srcSize = IntSize(width = 384, height = 240),
            dstSize = IntSize(width = 384, height =240)
        )
    }
}