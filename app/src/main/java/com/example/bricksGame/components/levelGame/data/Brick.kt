package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import kotlinx.coroutines.delay

data class Brick(
    var name: String = "brick",
    var id: Int,
    var position: String,
    var assetImage: Int,

    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    var borderColor: Color = GameConfig.BRICK_BORDER_COLOR,

    var x: MutableIntState,
    var y: MutableIntState,

    var hasBonusOwnerId: FieldBrick? = null,
    var fieldBrickOnCollision: FieldBrick? = null,
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
        if (this.name == "Bonus") {
            FieldViewModel.onBonus(this)
            this.x.intValue = 0
            this.y.intValue = 0

        } else {

            if (fieldBrickOnCollision != null) {

                val offsetAmount = getOffsetAmount(fieldBrickOnCollision!!)
                dragging(offsetAmount.getValue("x"), offsetAmount.getValue("y"))
                BricksViewModel.removeBrick(this)
                soundController.pushCristal()
            } else {
                this.x.intValue = 0
                this.y.intValue = 0
            }
        }
        hasBonusOwnerId?.onDragEnd()
        fieldBrickOnCollision?.onDragEnd()
    }

    private fun getOffsetAmount(fieldBrickOnCollision: FieldBrick): Map<String, Float> {
        val padding: Float = GameConfig.BRICK_BORDER_SIZE * screenSize.density

        val globalX = fieldBrickOnCollision.globalX + padding
        val globalY = fieldBrickOnCollision.globalY + padding

        val xOffset = globalX - this.globalX
        val yOffset = globalY - this.globalY

        val dragAmount = mapOf(
            "x" to xOffset,
            "y" to yOffset
        )
        return dragAmount
    }

    fun keepSpace(fieldBrick: FieldBrick) {
        fieldBrickOnCollision = fieldBrick
    }

    fun freeSpace() {
        fieldBrickOnCollision = null
    }
}


