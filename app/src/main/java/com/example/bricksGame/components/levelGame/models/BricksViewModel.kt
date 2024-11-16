package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    private val widthPadding = FieldViewModel.padding / FieldViewModel.ROWS + FieldViewModel.border*2
    private val heightPadding =
        FieldViewModel.padding/2 / FieldViewModel.COLUMNS + FieldViewModel.border

    private val width = FieldViewModel.fieldBrickWidth - widthPadding
    private val height = FieldViewModel.fieldBrickHeight-heightPadding

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








