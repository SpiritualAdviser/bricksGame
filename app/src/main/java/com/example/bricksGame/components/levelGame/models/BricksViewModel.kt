package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    private val widthPadding =
        FieldViewModel.padding / FieldViewModel.ROWS + FieldViewModel.border * 2
    private val heightPadding =
        FieldViewModel.padding / 2 / FieldViewModel.COLUMNS + FieldViewModel.border

    private val width = FieldViewModel.fieldBrickWidth - widthPadding
    private val height = FieldViewModel.fieldBrickHeight - heightPadding

    private const val MAX_BRICKS = 3

    private var _bricksList = createBricksList().toMutableStateList()

    val bricks: MutableList<Brick>
        get() = _bricksList

    fun resetData() {
        _bricksList.clear()
        _bricksList = createBricksList().toMutableStateList()
    }

    private fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()

        for (i in 0 until MAX_BRICKS) {
            val brick = Brick(
                width = width,
                height = height,
                id = i,
                position = i.toString(),
                color = getRandomColor()
            )
            bricksList.add(brick)
        }
        return bricksList
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }

}








