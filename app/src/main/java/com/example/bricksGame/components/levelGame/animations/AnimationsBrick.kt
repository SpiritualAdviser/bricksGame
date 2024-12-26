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
    fun InitAnimationRotate(brick: Brick) {

        LaunchedEffect(brick.animated.value) {
            brick.rotation.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000),
            )
        }
    }

    @Composable
    fun InitAnimationTranslationX(brick: Brick) {

        LaunchedEffect(brick.animated.value) {
            brick.translationX.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 700),
            )
        }
    }

    fun runAnimationRotate(brick: Brick) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(0)
            brick.animated.value = true
        }
    }

    fun runAnimationTranslationX(brick: Brick) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(0)
            brick.animated.value = true
        }
    }
}
