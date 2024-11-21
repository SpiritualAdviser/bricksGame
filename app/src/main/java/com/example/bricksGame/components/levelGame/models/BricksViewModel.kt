package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    private val widthPadding =
        FieldViewModel.padding / FieldViewModel.ROWS + FieldViewModel.border * 2
    private val heightPadding =
        FieldViewModel.padding / 2 / FieldViewModel.COLUMNS + FieldViewModel.border

    private val width = FieldViewModel.fieldBrickWidth - widthPadding
    private val height = FieldViewModel.fieldBrickHeight - heightPadding

    private const val MAX_BRICKS = 3
    private var brickId = 0

    private var _bricksList = createBricksList().toMutableStateList()

    val bricks
        get() = _bricksList

    fun resetData() {
        brickId = 0
        _bricksList.clear()
        _bricksList = createBricksList().toMutableStateList()
    }

    private fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()
        for (i in 0 until MAX_BRICKS) {

            bricksList.add(createBrick())
        }
        return bricksList
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }

    fun removeBrick(brick: Brick) {
        FieldViewModel.setBricksOnField(brick)
        brick.freeSpace()
        _bricksList.remove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        if (_bricksList.size == 0) {
            for (i in 0 until MAX_BRICKS) {
                _bricksList.add(createBrick())
            }
        }
    }

    private fun createBrick(): Brick {
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            width = width,
            height = height,
            id = ++brickId,
            position = brickId.toString(),
            color = getRandomColor()
        )
    }

}








