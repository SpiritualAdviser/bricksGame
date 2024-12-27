package com.example.bricksGame.components.levelGame.animations

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.bricksGame.components.levelGame.models.Brick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AnimationsBrick {

    @Composable
    fun InitAnimationTranslationX(brick: Brick) {

        LaunchedEffect(brick.animated.value) {
            brick.translationX.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = brick.delayTranslation,
//                    easing = LinearOutSlowInEasing
                ),
            )
        }
    }

    @Composable
    fun InitAnimationTranslationY(brick: Brick) {

        LaunchedEffect(brick.animated.value) {
            brick.translationY.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = brick.delayTranslation,
//                    easing = LinearOutSlowInEasing
                ),
            )
        }
    }

    fun runAnimationTranslation(brick: Brick, index: Int) {
        brick.delayTranslation = 250 * (index + 1)
        CoroutineScope(Dispatchers.Main).launch {
            delay(0)
            brick.animated.value = true
        }
    }

}
