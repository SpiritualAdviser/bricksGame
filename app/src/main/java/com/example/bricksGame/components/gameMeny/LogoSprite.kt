package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.gameMeny.animation.AnimationLogo

@Composable
fun LogoSprite() {

    val image = AnimationLogo.spriteSheet?.value
    val frameParams = AnimationLogo.imageFrame?.value

    if (image != null && frameParams != null) {

        Canvas(modifier = Modifier.border(1.dp, Color.Red)) {
            drawImage(
                image = image,
                srcOffset = IntOffset(x = frameParams.frame.x, y = frameParams.frame.y),
                srcSize = IntSize(width = frameParams.frame.w, height = frameParams.frame.h),
            )
        }
    }
}