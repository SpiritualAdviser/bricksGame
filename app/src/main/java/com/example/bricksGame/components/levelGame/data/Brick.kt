package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricksGame.screenSize
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

data class Brick(
    var id: Int,
    var position: String,
    var color: Color,

    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    private var innerX: Dp = 0.dp,
    private var innerY: Dp = 0.dp,
    var width: Dp,
    var height: Dp,

    var onCollision: Boolean = false,
) {
    var x by mutableStateOf(innerX)
    var y by mutableStateOf(innerY)

    private var collisionTarget: FieldBrick? = null

    fun addToCollision() {
        CollisionBricksOnLevel.observeCenterObjects(this)
    }

    fun dragging(x: Float, y: Float) {
        this.x += toDp(x)
        this.y += toDp(y)
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width
        this.globalHeight = coordinates.size.height
        this.globalX = coordinates.positionInWindow().x
        this.globalY = coordinates.positionInWindow().y
    }

    fun onCollision(fieldBrick: FieldBrick?) {
        collisionTarget = fieldBrick
        println(collisionTarget)
    }

    fun stickPosition() {
        if (collisionTarget != null) {
            val offsetAmount = getOffsetAmount(collisionTarget!!)
            dragging(offsetAmount.getValue("x"), offsetAmount.getValue("y"))
        } else {
            this.x = 0.dp
            this.y = 0.dp
        }
        collisionTarget?.onDragEnd()
    }

    private fun getOffsetAmount(collisionTarget: FieldBrick): Map<String, Float> {

        val globalX = collisionTarget.globalX
        val globalY = collisionTarget.globalY

        val xOffset = globalX - this.globalX
        val yOffset = globalY - this.globalY

        val dragAmount = mapOf(
            "x" to xOffset,
            "y" to yOffset
        )
        return dragAmount
    }

    private fun toDp(x: Float): Dp {
        return (x / screenSize.density).dp
    }
}


