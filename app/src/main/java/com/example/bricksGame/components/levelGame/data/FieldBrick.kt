package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.FieldViewModel.EMPTY_ID
import com.example.bricksGame.ui.theme.Green900

data class FieldBrick(
    val name: String = "FieldBricks",
    val position: Pair<Int, Int>,
    var id: String = "Color.Transparent",
    val border: Dp = 1.dp,
    var borderColor: MutableState<Color> = mutableStateOf(Color.Black),
    var color: MutableState<Color> = mutableStateOf(Color.Transparent),
    var assetImageEmpty: MutableState<Int> = mutableIntStateOf(R.drawable.bgfielbrickempty),
    var hasOwnerId: Int? = null,

    var globalX: Float = 0f,
    var globalY: Float = 0f,

    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    var width: Dp = 0.dp,
    var height: Dp = 0.dp,

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

    fun setColorOnStickBrick(color: Color) {
        this.color.value = color
        this.setBorderBlack()
    }

    fun setBorderRed() {
        changeBorder(Color.Red)
    }

    fun setBorderBlack() {
        changeBorder(Green900)
    }

    fun onDragEnd() {

        if (ONLY_EMPTY_PLEASES) {
            changeBorder(Green900)
        } else {
            changeBorder(Green900)
            this.resetFieldBrick()
        }
    }

    fun resetFieldBrick() {
        this.hasOwnerId = null
        this.setBorderBlack()
        this.color.value = Color.Transparent
        this.id = EMPTY_ID
    }
}