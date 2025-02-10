package com.example.bricksGame.components.levelGame.animations

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.bricksGame.gameObjects.GameObjects

@Composable
fun InitAnimationTranslationX(brick: GameObjects.Brick) {

    LaunchedEffect(brick) {
        brick.animation.translationX.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = brick.animation.delayTranslation,
//                    easing = LinearOutSlowInEasing
            ),
        )
    }
}

@Composable
fun InitAnimationTranslationY(brick: GameObjects.Brick) {

    LaunchedEffect(brick) {
        brick.animation.translationY.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = brick.animation.delayTranslation,
//                    easing = LinearOutSlowInEasing
            ),
        )
    }
}