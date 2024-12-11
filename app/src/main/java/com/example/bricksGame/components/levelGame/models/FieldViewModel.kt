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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

                val isWinList = checkWin(row)

                if (isWinList != null) {
                    itemsOnWin.add(isWinList)
                }
            }

            if (index % GameConfig.ROWS == 0) {
                column = brickOnField.subList(index, index + GameConfig.ROWS)

                val isWinList = checkWin(column)

                if (isWinList != null) {
                    itemsOnWin.add(isWinList)
                }
            }
        }
        itemsOnWin.forEach {
            resetLineOnWin(it)
        }
        itemsOnWin.clear()
    }

    private fun checkWin(checkedList: List<FieldBrick>): MutableList<FieldBrick>? {

        var checkList = mutableListOf<FieldBrick>()
        var winList = mutableListOf<FieldBrick>()
        var border = GameConfig.WIN_NUMBER_LINE
        var wasWin = false

        checkedList.forEachIndexed { index, cristal ->
            if (wasWin) {
                checkList.forEach {
                    winList.add(it)
                }
                checkList.clear()
            } else {
                checkList.clear()
                checkedList.forEachIndexed { innerIndex, innerItem ->

                    if (index <= innerIndex) {

                        if (cristal.id == innerItem.id && cristal.id != EMPTY_ID) {
                            checkList.add(innerItem)
                            if (checkList.size >= border) {
                                wasWin = true
                            }
                        } else {
                            if (checkList.size >= border) {
                                wasWin = true
                            } else {
                                checkList.clear()
                            }
                        }
                    }
                }
            }
        }
        if (winList.isNotEmpty()) {
            return winList
        } else {
            return null
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
