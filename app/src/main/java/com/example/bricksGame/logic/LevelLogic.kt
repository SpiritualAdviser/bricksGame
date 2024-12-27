package com.example.bricksGame.logic

import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.levelGame.models.Brick
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel.EMPTY_ID
import com.example.bricksGame.components.levelGame.models.FieldViewModel.brickOnField
import com.example.bricksGame.components.levelGame.models.FieldViewModel.setImageOnField
import com.example.bricksGame.components.map.models.MapModel.currentLevel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel.updatePlayerOnLevelWin
import com.example.bricksGame.components.popups.models.PopupsViewModel
import com.example.bricksGame.soundController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ButtonController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.forEach
import kotlin.math.floor

object LevelLogic {

    var levelRows = mutableListOf<List<FieldBrick>>()
    var levelColumns = mutableListOf<List<FieldBrick>>()
    var wasWin = false

    fun onStartLevel() {
        BricksViewModel.resetData()
        FieldViewModel.resetData()
        BonusViewModel.setNegativeBonusOnLevelField()
        setRowsAndColumnOnLevel()
        FieldViewModel.addToCollision()
    }

    private fun setRowsAndColumnOnLevel() {
        var column: List<FieldBrick>
        var row: List<FieldBrick>
        levelRows.clear()
        levelColumns.clear()

        for (index in 0 until GameConfig.ROWS) {
            row = brickOnField.filter { index == it.position.second }
            levelRows.add(row)
        }

        for (index in 0 until GameConfig.COLUMNS) {
            column = brickOnField.filter { index == it.position.first }
            levelColumns.add(column)
        }
    }

    fun checkRoundOnBonus(winningPositions: MutableList<Pair<Int, Int>>, onBonus: Boolean) {
        onEndRound(winningPositions, onBonus)
    }

    fun checkRound(brick: Brick) {
        val columnIndex = brick.fieldBrickOnCollision?.position?.first
        val rowIndex = brick.fieldBrickOnCollision?.position?.second
        val winningPositions = mutableListOf<Pair<Int, Int>>()

        rowIndex?.run {
            levelRows.getOrNull(rowIndex)?.run {
                winningPositions.addAll(getWinningPositions(this))
            }
        }

        columnIndex?.run {
            levelColumns.getOrNull(columnIndex)?.run {
                winningPositions.addAll(getWinningPositions(this))
            }
        }

        onEndRound(winningPositions)
    }

    private fun onEndRound(
        winningPositions: MutableList<Pair<Int, Int>>,
        onBonus: Boolean = false
    ) {
        if (winningPositions.isNotEmpty()) {

            onWin(winningPositions, onBonus)
        }
        checkEndLevel()
    }

    private fun onWin(winningPositions: MutableList<Pair<Int, Int>>, onBonus: Boolean) {
        soundController.winReel()
        addScore(winningPositions)
        resetLineOnWin(winningPositions, onBonus)
    }

    private fun getWinningPositions(bricks: List<FieldBrick>): MutableList<Pair<Int, Int>> {
        var winIndexesOnLine = listOf<Int>()
        var indexesOnField: MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>()

        val numberWinLine = getNumberWinLine(bricks)
        var startIndex = numberWinLine - 1
        var endIndex = getEndIndex(bricks, startIndex, numberWinLine)
        wasWin = false

        for (index in startIndex..endIndex) {
            if (!wasWin) {
                bricks.getOrNull(index)?.let { brick ->
                    if (!isClosedBrick(brick) && brick.id != EMPTY_ID) {
                        winIndexesOnLine = runComparator(brick, bricks, numberWinLine)
                    }
                }
            }
        }

        winIndexesOnLine.forEach {
            indexesOnField.add(bricks[it].position)
        }
        return indexesOnField
    }

    private fun runComparator(
        brick: FieldBrick,
        bricks: List<FieldBrick>,
        numberWinLine: Int,
    ): MutableList<Int> {
        val indexList = mutableListOf<Int>()

        bricks.forEachIndexed { index, fieldBrick ->
            if (!wasWin) {
                if (brick.id == fieldBrick.id) {
                    indexList.add(index)
                } else {
                    wasWin = checkWin(indexList, numberWinLine)
                }
            }
        }
        wasWin = checkWin(indexList, numberWinLine)

        if (wasWin) {
            getClosedFieldBricks(indexList, bricks)
        }

        return indexList
    }

    private fun checkWin(indexList: MutableList<Int>, numberWinLine: Int): Boolean {
        return if (indexList.size >= numberWinLine) {
            true
        } else {
            indexList.clear()
            false
        }
    }

    private fun getEndIndex(bricks: List<FieldBrick>, startIndex: Int, numberWinLine: Int): Int {
        return if (bricks.size - numberWinLine <= startIndex) {
            startIndex
        } else {
            bricks.size - numberWinLine
        }
    }

    private fun getNumberWinLine(bricks: List<FieldBrick>): Int {
        var winLine = 0
        currentLevel?.numberOfBricksToWin?.let {
            if (it == 0 || it > bricks.size) {
                winLine = bricks.size
            } else {
                winLine = it
            }
        }
        return winLine
    }

    private fun isClosedBrick(fieldBrick: FieldBrick): Boolean {
        return fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_LIVES || fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_ROCK
    }

    private fun getClosedFieldBricks(indexList: MutableList<Int>, bricks: List<FieldBrick>) {
        val firstIndexBrick = indexList.first() - 1
        val lastIndexBrick = indexList.last() + 1

        bricks.getOrNull(firstIndexBrick)?.run {
            if (isClosedBrick(bricks[firstIndexBrick])) {
                indexList.add(firstIndexBrick)
            }
        }

        bricks.getOrNull(lastIndexBrick)?.run {
            if (isClosedBrick(bricks[lastIndexBrick])) {
                indexList.add(lastIndexBrick)
            }
        }
    }

    private fun resetLineOnWin(
        winningPositions: MutableList<Pair<Int, Int>>,
        onBonus: Boolean = false
    ) {
        if (!onBonus) {
            BonusViewModel.setAlpha(GameConfig.SPEED_OPEN_BONUS * winningPositions.size)
        }

        winningPositions.forEach { winPosition ->

            brickOnField.find { it.position == winPosition }?.let {
               onResetFieldBrick(it)
            }
        }
    }

    private fun addScore(winningPositions: MutableList<Pair<Int, Int>>) {
        var score = winningPositions.size
        val numberWin =
            if (GameConfig.WIN_NUMBER_LINE == 0) winningPositions.size else GameConfig.WIN_NUMBER_LINE

        var overBonus = winningPositions.size - (numberWin - 1)

        if (overBonus > 0) {
            PlayerViewModel.addScore(numberWin * overBonus)

            if (score > numberWin) {
                CoroutineScope(Dispatchers.Main).launch {
                    popupOnWinLine(true, winningPositions)
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    popupOnWinLine(false, winningPositions)
                }
            }

        } else {
            PlayerViewModel.addScore(score)

            CoroutineScope(Dispatchers.Main).launch {
                popupOnWinLine(false, winningPositions)
            }
        }
    }

    private fun  onResetFieldBrick(fieldBrick: FieldBrick) {
        if (fieldBrick.life > 0) {
            --fieldBrick.life
            fieldBrick.hasBonusOwnerId = null
        } else {
            fieldBrick.resetFieldBrick()
        }
        setImageOnField(fieldBrick)
    }

    private fun checkEndLevel() {
        var stepsOnLevel = MapModel.levelStep.intValue
        val noPlaceOnFieldGame = brickOnField.all { it.id != EMPTY_ID }

        if (GameConfig.GAME_TYPE_FREE) {

            if (noPlaceOnFieldGame) {
                CoroutineScope(Dispatchers.Main).launch {
                    closeLevel(false)
                }
            }
            return
        }

        if (stepsOnLevel > 0 && !noPlaceOnFieldGame) {
            if (checkWinLevelOrNot()) {

                CoroutineScope(Dispatchers.Main).launch {
                    closeLevel(checkWinLevelOrNot())
                }
            }

        } else {

            CoroutineScope(Dispatchers.Main).launch {
                closeLevel(checkWinLevelOrNot())
            }
        }
    }

    private fun checkWinLevelOrNot(): Boolean {
        val currentLevel = currentLevel
        return currentLevel != null && PlayerViewModel.playerScore.intValue >= currentLevel.numberOfScoreToWin
    }

    private suspend fun popupOnWinLine(
        megaWin: Boolean = false,
        winningPositions: MutableList<Pair<Int, Int>>
    ) {
        winningPositions.sortedWith(compareBy({ it.first }, { it.second }))

        val centerIndexPosition = floor(winningPositions.size.toFloat() / 2).toInt()
        val rowDirection = winningPositions.first().first == winningPositions.last().first

        val fieldBrick = brickOnField.find { it.position == winningPositions[centerIndexPosition] }

        delay(100)
        PopupsViewModel.onWinLine(megaWin, fieldBrick, winningPositions.size, rowDirection)
        delay(400)
        PopupsViewModel.closePopupOnWinLine()
    }

    private suspend fun closeLevel(onWin: Boolean) {
        if (onWin) {
            updatePlayerOnLevelWin()
        }
        PopupsViewModel.setImageOnLevelEnd(onWin)
        delay(300)
        PopupsViewModel.showPopupOnFinishGame()
        delay(1800)

        if (GameConfig.GAME_TYPE_FREE) {
            ButtonController.navigateToHome()
        } else {
            ButtonController.navigateToMap()
        }

        delay(300)
       PopupsViewModel.closePopupOnFinishGame()
    }
}