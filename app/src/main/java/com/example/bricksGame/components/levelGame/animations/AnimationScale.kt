package com.example.bricksGame.components.levelGame.animations

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.bricksGame.gameObjects.PlaceOnField

@Composable
fun RunAnimationScaleOnPlace(placeOnField: PlaceOnField) {

    LaunchedEffect(placeOnField.animation.wasAnimated.value) {
        placeOnField.animation.scaleAnimation.snapTo(0.4F)

        placeOnField.animation.scaleAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 400),
            initialVelocity = 0.4F,
        )
    }
}


