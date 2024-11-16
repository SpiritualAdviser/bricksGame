package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

data class Brick(
    private var innerX: Dp = 0.dp,
    private var innerY: Dp = 0.dp,
    var width: Dp,
    var height: Dp,
    var id: Int,
    var position: String,
    var color: Color,
    var onCollision: Boolean = false,
) {
    var x by mutableStateOf(innerX)
    var y by mutableStateOf(innerY)
    var globalX = 0.dp
    var globalY = 0.dp
    var globalWidth: Dp = 0.dp
    var globalHeight: Dp = 0.dp

    fun addToCollision() {
        CollisionBricksOnLevel.observeCenterObjects(this)
    }

    fun dragging(dragAmountX: Dp = 0.dp, dragAmountY: Dp = 0.dp) {
        this.x += dragAmountX
        this.y += dragAmountY
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width.dp
        this.globalHeight = coordinates.size.height.dp
        this.globalX = coordinates.positionInWindow().x.dp
        this.globalY = coordinates.positionInWindow().y.dp
    }

    fun onCollision() {
        return
    }
}