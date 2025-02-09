package com.example.bricksGame.components.levelGame.animations

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.gameObjects.GameObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AnimationsBrick {
    var canRunTranslation = mutableStateOf(false)

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

    fun runAnimationTranslation(brick: GameObjects.Brick, index: Int) {
        brick.animation.delayTranslation = 250 * (index + 1)

        CoroutineScope(Dispatchers.Main).launch {

            delay((brick.animation.delayTranslation + 200).toLong())
            brick.animation.wasAnimated.value = true
        }
    }
}
