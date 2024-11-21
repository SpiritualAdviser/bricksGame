package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.screenSize
import kotlinx.coroutines.delay

data class Brick(
    var id: Int,
    var position: String,
    var color: Color,

    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    var x: MutableIntState,
    var y: MutableIntState,

    var width: Dp,
    var height: Dp,
    var collisionTarget: FieldBrick? = null,
) {

    fun dragging(xDragAmount: Float, yDragAmount: Float) {
        this.x.intValue += xDragAmount.toInt()
        this.y.intValue += yDragAmount.toInt()
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width
        this.globalHeight = coordinates.size.height
        this.globalX = coordinates.positionInWindow().x
        this.globalY = coordinates.positionInWindow().y
    }

    suspend fun stickPosition() {
        delay(25)
        if (collisionTarget != null) {
            val offsetAmount = getOffsetAmount(collisionTarget!!)
            dragging(offsetAmount.getValue("x"), offsetAmount.getValue("y"))
            BricksViewModel.removeBrick(this)

        } else {
            this.x.intValue = 0
            this.y.intValue = 0
        }
        collisionTarget?.onDragEnd()
    }

    private fun getOffsetAmount(collisionTarget: FieldBrick): Map<String, Float> {
        val padding: Float = collisionTarget.border.value * screenSize.density

        val globalX = collisionTarget.globalX + padding
        val globalY = collisionTarget.globalY + padding

        val xOffset = globalX - this.globalX
        val yOffset = globalY - this.globalY

        val dragAmount = mapOf(
            "x" to xOffset,
            "y" to yOffset
        )
        return dragAmount
    }

    fun keepSpace(fieldBrick: FieldBrick) {
        collisionTarget = fieldBrick
    }

    fun freeSpace() {
        collisionTarget = null
    }
}


