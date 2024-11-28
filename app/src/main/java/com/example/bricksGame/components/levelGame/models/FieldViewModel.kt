package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.screenSize
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

object FieldViewModel : ViewModel() {

    private val fieldSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
        screenSize.screenHeightDp - GameConfig.PADDING_BG_FIELD.dp * 2
    } else {
        screenSize.screenWidthDp - GameConfig.PADDING_BG_FIELD.dp * 2
    }

    val brickSizePortrait = (fieldSize - GameConfig.PADDING_FIELD.dp * 2) / GameConfig.ROWS
    val brickSizeLandscape = (fieldSize - GameConfig.PADDING_FIELD.dp * 2) / GameConfig.COLUMNS

    const val EMPTY_ID = "Color.Transparent"
    var brickOnField = createBricksList()

    fun resetData() {
        this.brickOnField.clear()
        CollisionBricksOnLevel.resetData()
        this.brickOnField = createBricksList()
    }

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = GameConfig.ROWS * GameConfig.COLUMNS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % GameConfig.ROWS == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createBrick(positionColumn, positionRow)
            bricksList.add(fieldBrick)

            addToCollision(fieldBrick)

            ++positionRow
        }
        runCollision()
        println(bricksList.toString())
        return bricksList
    }

    private fun createBrick(positionColumn: Int, positionRow: Int): FieldBrick {
        return FieldBrick(
            position = Pair(positionColumn, positionRow),
        )
    }

    private fun addToCollision(fieldBrick: FieldBrick) {
        CollisionBricksOnLevel.addToCollision(fieldBrick = fieldBrick)
    }

    private fun runCollision() {
        CollisionBricksOnLevel.runCollision(true)
    }

    fun setBricksOnField(brick: Brick) {
        val currentFieldBrick = brick.fieldBrickOnCollision

        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
        currentFieldBrick?.id = brick.assetImage.toString()
    }

    fun checkFieldOnFinishRound() {
        var column: List<FieldBrick>
        var row: List<FieldBrick>
        var columnWin: List<FieldBrick> = listOf()
        var rowWin: List<FieldBrick> = listOf()
        brickOnField.forEachIndexed { index, _ ->

            if (index < GameConfig.ROWS) {
                row = brickOnField.filter { index == it.position.second }

                if (row.isNotEmpty() && this.checkWin(row)) {
                    rowWin = row
                }
            }

            if (index % GameConfig.ROWS == 0) {
                column = brickOnField.subList(index, index + GameConfig.ROWS)

                if (column.isNotEmpty() && this.checkWin(column)) {
                    columnWin = column
                }
            }
        }
        if (rowWin.isNotEmpty()) {
            resetLineOnWin(rowWin)
        }
        if (columnWin.isNotEmpty()) {
            resetLineOnWin(columnWin)
        }
    }

    private fun checkWin(checkedList: List<FieldBrick>): Boolean {
        val currentId = checkedList[0].id
        val isWin = checkedList.all { currentId == it.id && currentId != EMPTY_ID }
        return isWin
    }

    private fun resetLineOnWin(lineList: List<FieldBrick>) {
        soundController.winReel()
        PlayerViewModel.addScore(lineList.size)
        lineList.forEach { wonItem ->
            brickOnField.forEach {

                if (wonItem.position.toString() == it.position.toString()) {
                    it.resetFieldBrick()
                }
            }
        }
    }
}
