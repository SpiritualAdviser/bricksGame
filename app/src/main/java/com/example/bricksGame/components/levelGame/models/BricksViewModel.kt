package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    private val widthPadding = FieldViewModel.padding / FieldViewModel.ROWS + FieldViewModel.border
    private val heightPadding = FieldViewModel.padding / FieldViewModel.COLUMNS + FieldViewModel.border

    private val width = FieldViewModel.fieldBrickWidth - widthPadding
    private val height = FieldViewModel.fieldBrickHeight - heightPadding

    private const val MAX_BRICKS = 3

    private val _bricksList = createBricksList().toMutableStateList()

    val bricks: MutableList<Brick>
        get() = _bricksList

    private fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()

        for (i in 0 until MAX_BRICKS) {
            bricksList.add(
                Brick(
                    width = width,
                    height = height,
                    id = i,
                    position = i.toString(),
                    color = getRandomColor()
                )
            )
        }
        return bricksList
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }
}

data class Brick(
    private var innerX: Dp = 0.dp,
    private var innerY: Dp = 0.dp,
    var width: Dp,
    var height: Dp,
    var id: Int,
    var position: String,
    var color: Color,
) {
    var x by mutableStateOf(innerX)
    var y by mutableStateOf(innerY)
    var globalX = 0.dp
    var globalY = 0.dp
    var globalWidth: Dp = 0.dp
    var globalHeight: Dp = 0.dp

    fun addToCollision() {
        CollisionBricksOnLevel.observeObjects(this)
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width.dp
        this.globalHeight = coordinates.size.height.dp
        this.globalX = coordinates.positionInWindow().x.dp
        this.globalY = coordinates.positionInWindow().y.dp
    }
}







