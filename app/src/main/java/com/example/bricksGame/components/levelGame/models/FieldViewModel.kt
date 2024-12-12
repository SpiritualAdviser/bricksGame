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
        brickSizeLandscape = fieldMAxWidthSize / GameConfig.COLUMNS

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
                setWinLine(row, itemsOnWin)
            }

            if (index % GameConfig.ROWS == 0) {
                column = brickOnField.subList(index, index + GameConfig.ROWS)
                setWinLine(column, itemsOnWin)
            }
        }
        if (itemsOnWin.isNotEmpty()) {
            itemsOnWin.forEach {
                resetLineOnWin(it)
            }
            itemsOnWin.clear()
        }
    }

    private fun setWinLine(
        checkedList: List<FieldBrick>,
        itemsOnWin: MutableList<List<FieldBrick>>
    ) {
        var temporaryList = mutableListOf<FieldBrick>()
        var winList = mutableListOf<FieldBrick>()
        var winNumberBricks = GameConfig.WIN_NUMBER_LINE
        var startIndex = GameConfig.WIN_NUMBER_LINE - 1
        var endIndex = checkedList.size - GameConfig.WIN_NUMBER_LINE
        var wasWin = false

        if (winNumberBricks == 0 || winNumberBricks == checkedList.size) {
            startIndex = 0
            endIndex = 0
            winNumberBricks = checkedList.size
        }

        for (i in startIndex..endIndex) {
            if (wasWin) {
                break
            }
            val currentBrick = checkedList[i]
            if (currentBrick.id != EMPTY_ID) {

                checkedList.forEach {
                    if (currentBrick.id == it.id && it.id != EMPTY_ID) {
                        temporaryList.add(it)
                    } else {
                        if (!wasWin) {
                            wasWin = checkWin(temporaryList, winList, winNumberBricks)
                        }
                    }
                }
                if (!wasWin) {
                    wasWin = checkWin(temporaryList, winList, winNumberBricks)
                }
            }
        }
        if (winList.isNotEmpty()) {
            itemsOnWin.add(winList)
        }
    }

    private fun checkWin(
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>,
        winNumberBricks: Int,
    ): Boolean {

        if (temporaryList.size >= winNumberBricks) {
            temporaryList.forEach {
                winList.add(it)
            }
            temporaryList.clear()
            return true
        } else {
            temporaryList.clear()
            return false
        }
    }

    fun resetLineOnWin(lineList: List<FieldBrick>, onBonus: Boolean = false) {
        soundController.winReel()
        PlayerViewModel.addScore(lineList.size)
        if (!onBonus) {
            BonusViewModel.setAlpha(GameConfig.SPEED_OPEN_BONUS * lineList.size)
        }

        lineList.forEach { wonItem ->
            brickOnField.forEach {

                if (wonItem.position.toString() == it.position.toString()) {
                    it.resetFieldBrick()
                }
            }
        }
    }

}
