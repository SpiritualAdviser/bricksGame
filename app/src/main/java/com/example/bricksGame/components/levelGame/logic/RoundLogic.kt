package com.example.bricksGame.components.levelGame.logic

import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel.EMPTY_ID
import com.example.bricksGame.components.levelGame.models.FieldViewModel.brickOnField
import com.example.bricksGame.components.levelGame.models.FieldViewModel.numberOfCloseFieldBrickOnLine
import com.example.bricksGame.components.levelGame.models.FieldViewModel.setImageOnField
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.soundController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ButtonController
import kotlin.collections.forEach

object RoundLogic {

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

        winNumberBricks =
            if (checkedList.size < winNumberBricks || winNumberBricks == 0) checkedList.size else winNumberBricks

        startIndex =
            if (checkedList.elementAtOrNull(startIndex) != null) startIndex else checkedList.size - 1

        endIndex =
            if (startIndex <= endIndex && checkedList.elementAtOrNull(endIndex) != null) endIndex else startIndex

        numberOfCloseFieldBrickOnLine = 0

        for (i in startIndex..endIndex) {
            if (wasWin) {
                break
            }
            val currentBrick = checkedList[i]

            if (checkNeedToCompareBrick(currentBrick)) {

                checkedList.forEach {
                    if (needAddToTemporaryListList(currentBrick, it)) {
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

    fun needAddToTemporaryListList(currentBrick: FieldBrick, brick: FieldBrick): Boolean {
        if (GameConfig.WIN_LINE_DESTROY_NEGATIVE_BONUS && isClosedBrick(brick)
        ) {
            ++numberOfCloseFieldBrickOnLine
            return true
        } else {
            return currentBrick.id == brick.id && brick.id != EMPTY_ID
        }
    }

    fun checkNeedToCompareBrick(currentBrick: FieldBrick): Boolean {
        return if (isClosedBrick(currentBrick)) {
            false
        } else {
            currentBrick.id != EMPTY_ID
        }
    }

    fun isClosedBrick(fieldBrick: FieldBrick): Boolean {
        return fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_LIVES || fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_ROCK
    }

    private fun checkWin(
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>,
        winNumberBricks: Int,
    ): Boolean {
        var isWin = false
        val indexColoredBrickList: MutableList<Int> = mutableListOf()

        if (temporaryList.size >= (winNumberBricks + numberOfCloseFieldBrickOnLine)) {

            temporaryList.forEachIndexed { index, brick ->
                if (!isClosedBrick(brick)) {
                    indexColoredBrickList.add(index)

                } else {
                    isWin = canAddToWinList(indexColoredBrickList, temporaryList, winList)
                }
            }
            isWin = canAddToWinList(indexColoredBrickList, temporaryList, winList)

        } else {
            isWin = false
        }
        temporaryList.clear()
        return isWin
    }

    fun canAddToWinList(
        indexColoredBrickList: MutableList<Int>,
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>
    ): Boolean {
        var isWin = false

        if (indexColoredBrickList.size >= GameConfig.WIN_NUMBER_LINE) {
            needAddCloseBrickOrNot(temporaryList, winList, indexColoredBrickList)

            indexColoredBrickList.forEach { indexColoredBrick ->
                winList.add(temporaryList[indexColoredBrick])
            }

            isWin = true
        }
        indexColoredBrickList.clear()
        return isWin
    }

    fun needAddCloseBrickOrNot(
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>,
        indexColoredBrickList: MutableList<Int>,

        ) {
        val firstBrick = temporaryList.getOrNull(indexColoredBrickList.first() - 1)
        val lastBrick = temporaryList.getOrNull(indexColoredBrickList.last() + 1)

        if (firstBrick != null && isClosedBrick(firstBrick)) {
            winList.add(firstBrick)
        }
        if (lastBrick != null && isClosedBrick(lastBrick)) {
            winList.add(lastBrick)
        }
    }

    fun resetLineOnWin(lineList: List<FieldBrick>, onBonus: Boolean = false) {
        soundController.winReel()
        addScore(lineList)
        if (!onBonus) {
            BonusViewModel.setAlpha(GameConfig.SPEED_OPEN_BONUS * lineList.size)
        }

        lineList.forEach { wonItem ->
            brickOnField.forEach {

                if (wonItem.position.toString() == it.position.toString()) {
                    resetOrNotFieldBrick(it)
                }
            }
        }
        if (!GameConfig.GAME_TYPE_FREE) {
            checkEndLevel()
        }
    }

    private fun addScore(lineList: List<FieldBrick>) {
        var score = 0
        var overBonus = 1

        lineList.forEach {
            if (!isClosedBrick(it)) {
                if (score < GameConfig.WIN_NUMBER_LINE) {
                    ++score
                } else {
                    ++overBonus
                }
            }
        }

        PlayerViewModel.addScore(score * overBonus)
    }

    fun resetOrNotFieldBrick(fieldBrick: FieldBrick) {
        if (fieldBrick.life > 0) {
            fieldBrick.life -= 1
            fieldBrick.hasBonusOwnerId = null
        } else {
            fieldBrick.resetFieldBrick()
        }
        setImageOnField(fieldBrick)
    }

    fun checkEndLevel() {
        val currentLevel = MapModel.currentLevel

        if (currentLevel != null && PlayerViewModel.playerScore.intValue >= currentLevel.numberOfScoreToWin) {
            PlayerViewModel.updatePlayerOnLevelWin()

            ButtonController.navigateToMap()
        }
    }
}