package com.example.bricksGame.gameObjects

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf

data class Cords(
    var x: MutableIntState = mutableIntStateOf(0),
    var y: MutableIntState = mutableIntStateOf(0),

    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,
    var canDrag: Boolean = true,
)