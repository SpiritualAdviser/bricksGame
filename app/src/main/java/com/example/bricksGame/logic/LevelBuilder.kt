package com.example.bricksGame.logic

import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.floor

class LevelBuilder @Inject constructor() {

    @Inject
    lateinit var gameConfig: GameConfig
    private lateinit var gameFixLevels: List<Level>

    fun setLevelGameList(levelGameList: MutableList<Level>, gameFixLevels: List<Level>) {

        for (levelNumber in 1..gameConfig.MAX_LEVELS_ON_GAME) {
            this.gameFixLevels = gameFixLevels
            val level = createLevel(levelNumber)
            levelGameList.add(level)
        }
    }

    private fun createLevel(levelNumber: Int): Level {

        val fieldGameRow = getFieldGameRow(levelNumber)
        val fieldGameColumn = getFieldGameColumn(fieldGameRow)
        val numberOfBricksToWin = getNumberOfBricksToWin(fieldGameRow, fieldGameColumn)
        val additionalBrick = getAdditionalBrick(numberOfBricksToWin, fieldGameRow)
        val lastBrickToAdd = getLastBrickToAdd(additionalBrick, numberOfBricksToWin, fieldGameRow)
        val bonusFillSpeed = getBonusFillSpeed(levelNumber)
        val negativeBonuses = getNegativeBonuses(levelNumber, fieldGameRow, fieldGameColumn)
        val numberOfScoreToWin = getNumberOfScoreToWin(levelNumber, fieldGameRow, fieldGameColumn)
        val levelMaxStep = getLevelMaxStep(levelNumber, numberOfScoreToWin)

        return gameFixLevels.find { it.numberLevel == levelNumber } ?: Level(
            numberLevel = levelNumber,
            fieldGameRow = fieldGameRow,
            fieldGameColumn = fieldGameColumn,
            numberOfBricksToWin = numberOfBricksToWin,
            additionalBrick = additionalBrick,
            lastBrickToAdd = lastBrickToAdd,
            numberOfScoreToWin = numberOfScoreToWin,
            levelMaxStep = levelMaxStep,
            bonusFillSpeed = bonusFillSpeed,
            negativeBonuses = negativeBonuses,
        )
    }

    private fun getFieldGameRow(levelNumber: Int): Int {
        val intervalComplication: Double =
            gameConfig.MAX_LEVELS_ON_GAME.toDouble() / (gameConfig.MAX_LINE_FIELD_ON_GAME - gameConfig.MIN_LINE_FIELD_ON_GAME)
        val additionalLine: Double = levelNumber / intervalComplication

        var min = gameConfig.MIN_LINE_FIELD_ON_GAME + floor(additionalLine)
        var max = min + 3

        if (max > gameConfig.MAX_LINE_FIELD_ON_GAME) {
            min = gameConfig.MIN_LINE_FIELD_ON_GAME.toDouble() + 2
            max = gameConfig.MAX_LINE_FIELD_ON_GAME.toDouble() + 1
        }

        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getFieldGameColumn(fieldGameRow: Int): Int {
        var min = fieldGameRow
        var max = fieldGameRow + 2

        if (fieldGameRow == gameConfig.MIN_LINE_FIELD_ON_GAME) {
            min = fieldGameRow + 1
            max = fieldGameRow + 2
        }
        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getNumberOfBricksToWin(fieldGameRow: Int, fieldGameColumn: Int): Int {
        var maxLine = if (fieldGameRow > fieldGameColumn) fieldGameRow else fieldGameColumn

        when (maxLine) {
            3 -> maxLine = 3
            4 -> maxLine = 3
            5 -> maxLine = 4
            else -> {
                maxLine = 5
            }
        }

        val minLine = if (maxLine > 4) 4 else gameConfig.MIN_WIN_NUMBER_LINE

        return (Math.random() * (maxLine - minLine) + minLine).toInt()
    }

    private fun getAdditionalBrick(numberOfBricksToWin: Int, fieldGameRow: Int): Int {
        var min = 3
        var max = 4

        if (fieldGameRow == numberOfBricksToWin) {
            min = 3
            max = 5
        }

        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getLastBrickToAdd(
        additionalBrick: Int,
        numberOfBricksToWin: Int,
        fieldGameRow: Int
    ): Int {

        var min = 2
        var max = additionalBrick

        if (numberOfBricksToWin == fieldGameRow - 1) {
            min = 1
            max = additionalBrick - 1
        }

        if (fieldGameRow - numberOfBricksToWin > 2) {
            min = 0
            max = 1
        }


        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getBonusFillSpeed(
        levelNumber: Int
    ): Float {
        val stepFillBonus = gameConfig.MAX_SPEED_OPEN_BONUS / gameConfig.MAX_LEVELS_ON_GAME
        val max = gameConfig.MAX_SPEED_OPEN_BONUS - levelNumber * stepFillBonus
        val min = max / 2

        return (Math.random() * (max - min) + min).toFloat()
    }

    private fun getNegativeBonuses(
        levelNumber: Int,
        fieldGameRow: Int,
        fieldGameColumn: Int
    ): MutableList<Int> {
        val bonuses = mutableListOf<Int>()

        val maxClosedPlaces =
            ((fieldGameRow.toFloat() * fieldGameColumn.toFloat()) * gameConfig.MAX_CLOSED_PERCENT_GAME_FIELD) / 100

        val percent = levelNumber / gameConfig.MAX_LEVELS_ON_GAME.toDouble() * 100

        val min = ceil((maxClosedPlaces * percent / 100) / gameConfig.negativeBonuses.size).toInt()

        val max = if (min + 2 > maxClosedPlaces) maxClosedPlaces.toInt() else min + 1

        var numberBonus: Int = 0

        for (bonus in 0 until gameConfig.negativeBonuses.size) {
            numberBonus = (Math.random() * (max - min) + min).toInt()

            bonuses.add(numberBonus)
        }

        return bonuses
    }

    private fun getNumberOfScoreToWin(
        levelNumber: Int,
        fieldGameRow: Int,
        fieldGameColumn: Int
    ): Int {
        val fieldSize = fieldGameRow * fieldGameColumn
        val scoreStep: Double =
            (gameConfig.MAX_SCORE_ON_GAME - gameConfig.MIN_SCORE_ON_GAME) / gameConfig.MAX_LEVELS_ON_GAME.toDouble()

        val min = fieldSize + gameConfig.MIN_SCORE_ON_GAME + levelNumber * scoreStep

        val max = fieldSize + min + levelNumber * scoreStep

        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getLevelMaxStep(levelNumber: Int, numberOfScoreToWin: Int): Int {
        val intervalComplicationSteps: Double =
            gameConfig.MAX_SCORE_ON_GAME / gameConfig.MAX_LEVELS_ON_GAME.toDouble()

        val partOfLevels = gameConfig.MAX_LEVELS_ON_GAME / 3

        val increase = (partOfLevels - levelNumber) / intervalComplicationSteps

        var min: Int = numberOfScoreToWin
        var max = numberOfScoreToWin

        if (levelNumber < partOfLevels) {
            min = numberOfScoreToWin + increase.toInt()
            max = min + increase.toInt()
        } else {
            min = numberOfScoreToWin + increase.toInt()
            max = numberOfScoreToWin
        }

        return (Math.random() * (max - min) + min).toInt()
    }
}