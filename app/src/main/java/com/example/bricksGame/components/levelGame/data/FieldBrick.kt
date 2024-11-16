package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

data class FieldBrick(
    val position: Pair<Int, Int>,
    val id: String = "00",
    val border: Dp = 0.dp,
    var borderColor: MutableState<Color> = mutableStateOf(Color.Black),
    var x: Dp = 0.dp,
    var y: Dp = 0.dp,
    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,
    val name: String = "FieldBricks",
    var width: Dp = 0.dp,
    var height: Dp = 0.dp,
    val color: Color = Color.Transparent,
    var onCollision: Boolean = false,
) {
    fun addToCollision() {
        CollisionBricksOnLevel.addToCollision(this)
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width
        this.globalHeight = coordinates.size.height
        this.globalX = coordinates.positionInWindow().x
        this.globalY = coordinates.positionInWindow().y
    }

    private fun changeBorder(color: Color) {
        this.borderColor.value = color
    }

    fun onTargetCollision(brick: Brick) {
        changeBorder(Color.Red)
        brick.onCollision(this)
    }

    fun onOutCollision(brick: Brick) {
        changeBorder(Color.Black)
        brick.onCollision(null)
    }

    fun onDragEnd(){
        changeBorder(Color.Black)
    }
}