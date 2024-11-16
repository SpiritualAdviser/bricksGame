package com.example.bricksGame.components.levelGame.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

data class FieldBrick(
    val border: Dp = 0.dp,
    val borderColor: Color = Color.Black,
    var x: Dp = 0.dp,
    var y: Dp = 0.dp,
    var globalX: Dp = 0.dp,
    var globalY: Dp = 0.dp,
    var globalWidth: Dp = 0.dp,
    var globalHeight: Dp = 0.dp,
    val name: String = "FieldBricks",
    var width: Dp = 0.dp,
    var height: Dp = 0.dp,
    val id: String = "00",
    val color: Color = Color.Transparent,
    val position: Pair<Int, Int>,
) {
    fun addToCollision() {
        CollisionBricksOnLevel.addToCollision(this)
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width.dp
        this.globalHeight = coordinates.size.height.dp
        this.globalX = coordinates.positionInWindow().x.dp
        this.globalY = coordinates.positionInWindow().y.dp
    }
}