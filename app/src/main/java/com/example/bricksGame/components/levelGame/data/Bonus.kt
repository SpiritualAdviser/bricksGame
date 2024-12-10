package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableIntState
import kotlinx.coroutines.delay

data class Bonus(
    var id: Int,
    var assetImage: Int,

    var x: MutableIntState,
    var y: MutableIntState,

) {

    fun dragging(xDragAmount: Float, yDragAmount: Float) {
        this.x.intValue += xDragAmount.toInt()
        this.y.intValue += yDragAmount.toInt()
    }

    suspend fun stickPosition() {
        delay(25)
        this.x.intValue = 0
        this.y.intValue = 0
    }

    fun keepSpace(fieldBrick: FieldBrick) {

    }

    fun freeSpace() {

    }
}