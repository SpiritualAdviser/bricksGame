package com.example.bricksGame.data.level

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.FieldViewModel.EMPTY_ID
import com.example.bricksGame.ui.GameConfig

data class FieldBrick(
    val name: String = "FieldBricks",
    val position: Pair<Int, Int>,
    var id: String = "Color.Transparent",

    var borderColor: MutableState<Color> = mutableStateOf(GameConfig.BRICK_BORDER_COLOR),
    var assetImage: MutableState<Int> = mutableIntStateOf(R.drawable.bgfielbrickempty),
    var hasOwnerId: Int? = null,

    var globalX: Float = 0f,
    var globalY: Float = 0f,

    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    /**
     * They were placed in an occupied room ONLY_EMPTY_PLEASES=true
     */
    private val ONLY_EMPTY_PLEASES: Boolean = true,
) {

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width
        this.globalHeight = coordinates.size.height
        this.globalX = coordinates.positionInWindow().x
        this.globalY = coordinates.positionInWindow().y
    }

    private fun changeBorder(color: Color) {
        this.borderColor.value = color
    }

    fun setImageOnStickBrick(image: Int) {
        this.assetImage.value = image
        this.setBorderBlack()
    }

    fun setBorderRed() {
        changeBorder(GameConfig.BRICK_BORDER_HOVER_COLOR)
    }

    fun setBorderBlack() {
        changeBorder(GameConfig.BRICK_BORDER_COLOR)
    }

    fun onDragEnd() {

        if (ONLY_EMPTY_PLEASES) {
            changeBorder(GameConfig.BRICK_BORDER_COLOR)
        } else {
            changeBorder(GameConfig.BRICK_BORDER_COLOR)
            this.resetFieldBrick()
        }
    }

    fun resetFieldBrick() {
        this.hasOwnerId = null
        this.setBorderBlack()
        this.assetImage.value = R.drawable.bgfielbrickempty
        this.id = EMPTY_ID
    }
}