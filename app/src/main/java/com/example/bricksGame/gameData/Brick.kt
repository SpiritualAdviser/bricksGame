package com.example.bricksGame.gameData

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import com.example.bricksGame.logic.controller.BricksController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.LevelLogic
import kotlinx.coroutines.delay
import javax.inject.Inject

class Brick(

    var name: String = "brick",
    var id: Int,
    var position: String,
    var assetImage: Int,
    var life: Int = 0,

    var globalX: Float = 0f,
    var globalY: Float = 0f,
    var globalWidth: Int = 0,
    var globalHeight: Int = 0,
    var canDrag: Boolean = true,
    var alpha: MutableState<Float> = mutableFloatStateOf(1f),
    var zIndex: MutableState<Float> = mutableFloatStateOf(0f),

    var x: MutableIntState,
    var y: MutableIntState,

    var hasBonusOwnerId: FieldBrick? = null,
    var fieldBrickOnCollision: FieldBrick? = null,

    var borderColor: Color,
    private var padding: Float,
    private var translationXInit: Float,
    private var translationYInit: Float,
    private var activeBonusBorder: MutableState<Color>
) {

    var wasAnimated = mutableStateOf(false)
    val rotation = Animatable(initialValue = 360f)
    val translationX = Animatable(initialValue = translationXInit)
    val translationY = Animatable(initialValue = translationYInit)
    var delayTranslation = 0

    fun changeZIndex(index: Float) {
        zIndex.value = index
    }

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

    suspend fun takeAPlaces() {
        delay(25)
        if (this.position == "Bonus") {
            if (this.hasBonusOwnerId != null) {
//                BonusViewModel.onBonus(this)
//                MapModel.changeLevelStepOnRound()
//                levelLogic.checkRound(this)
                this.hasBonusOwnerId = null
            }
            this.x.intValue = 0
            this.y.intValue = 0

        } else {

            if (fieldBrickOnCollision != null) {

                val offsetAmount = getOffsetAmount(fieldBrickOnCollision!!)
                dragging(offsetAmount.getValue("x"), offsetAmount.getValue("y"))
//                bricksController.removeBrick(this)
//                levelLogic.checkRound(this)
//                freeSpace()
//                soundController.pushCristal()
//                MapModel.changeLevelStepOnRound()

            } else {
                this.x.intValue = 0
                this.y.intValue = 0
            }
            fieldBrickOnCollision?.onDragEnd()
        }
    }

    private fun getOffsetAmount(fieldBrickOnCollision: FieldBrick): Map<String, Float> {

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


