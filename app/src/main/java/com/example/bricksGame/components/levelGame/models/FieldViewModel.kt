package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

object FieldViewModel : ViewModel() {

    var fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
        screenSize.screenHeightDp - GameConfig.PADDING_FIELD.dp
    } else {
        screenSize.screenWidthDp - GameConfig.PADDING_FIELD.dp
    }

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp

    fun onOptionChange() {
        brickSizePortrait = fieldMAxWidthSize / GameConfig.ROWS
        brickSizeLandscape =
            fieldMAxWidthSize / GameConfig.COLUMNS

        if (brickSizePortrait > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizePortrait = GameConfig.MAX_BRICKS_SIZE.dp
        }

        if (brickSizeLandscape > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizeLandscape = GameConfig.MAX_BRICKS_SIZE.dp
        }
    }

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
        var itemsOnWin = mutableListOf<List<FieldBrick>>()

        brickOnField.forEachIndexed { index, _ ->


            if (index < GameConfig.ROWS) {
                row = brickOnField.filter { index == it.position.second }

                if (GameConfig.WIN_NUMBER_LINE == 0) {

                    if (row.isNotEmpty() && checkWin(row)) {
                        itemsOnWin.add(row)
                    }
                } else {
                    val subWin = getSubWinList(row)

                    if (subWin.isNotEmpty()) {
                        itemsOnWin.add(subWin)
                    }
                }
            }

            if (index % GameConfig.ROWS == 0) {
                column = brickOnField.subList(index, index + GameConfig.ROWS)

                if (GameConfig.WIN_NUMBER_LINE == 0) {

                    if (column.isNotEmpty() && checkWin(column)) {
                        itemsOnWin.add(column)
                    }
                } else {
                    val subColumn = getSubWinList(column)

                    if (subColumn.isNotEmpty()) {
                        itemsOnWin.add(subColumn)
                    }
                }
            }
        }
        if (itemsOnWin.isNotEmpty()) {
            itemsOnWin.forEach {
                resetLineOnWin(it)
            }
        }
    }

    private fun getSubWinList(list: List<FieldBrick>): List<FieldBrick> {
        var border = GameConfig.WIN_NUMBER_LINE
        var winList: List<FieldBrick> = listOf()

        list.forEachIndexed { index, _ ->

            border = index + GameConfig.WIN_NUMBER_LINE

            if (border <= list.size) {
                val sublist = list.subList(index, border)

                if (sublist.isNotEmpty() && this.checkWin(sublist)) {
                    winList = sublist
                }
            }
        }
        return winList
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
