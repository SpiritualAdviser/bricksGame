package com.example.bricksGame.gameObjects

import androidx.compose.runtime.MutableState


class PlaceOnField(
    val position: Pair<Int, Int>,
    var slot: MutableState<GameObjects>,
    val cords: Cords = Cords()
)