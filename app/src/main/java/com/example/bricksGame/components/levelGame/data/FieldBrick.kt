package com.example.bricksGame.components.levelGame.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FieldBrick(
    val name: String = "FieldBricks",
    val position: Pair<Int, Int>,
    val id: String = "00",
    val border: Dp = 0.dp,
    var borderColor: MutableState<Color> = mutableStateOf(Color.Black),
    val color: Color = Color.Transparent,

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

    fun setBorderRed() {
        changeBorder(Color.Red)
    }

    fun setBorderBlack() {
        changeBorder(Color.Black)
    }

    fun onDragEnd() {

        if (ONLY_EMPTY_PLEASES) {
            changeBorder(Color.Black)
        } else {
            changeBorder(Color.Black)
            this.hasOwnerId = null
        }
    }
}