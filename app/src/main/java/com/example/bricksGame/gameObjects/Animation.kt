package com.example.bricksGame.gameObjects

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Animation(
    var wasAnimated: MutableState<Boolean> = mutableStateOf(false),
    val rotation: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 360f),
    var translationX: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0F),
    var translationY: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0F),
    var delayTranslation: Int = 0,
    var scaleAnimation: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0F),

)