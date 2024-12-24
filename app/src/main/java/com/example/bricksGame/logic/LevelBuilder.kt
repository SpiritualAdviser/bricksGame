package com.example.bricksGame.logic

import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import kotlin.math.ceil
import kotlin.math.floor

class LevelBuilder {
    private val gameFixLevels = LevelsConfig.gameFixLevels

    private val intervalComplication: Double =
        GameConfig.MAX_LEVELS_ON_GAME.toDouble() / (GameConfig.MAX_LINE_FIELD_ON_GAME - GameConfig.MIN_LINE_FIELD_ON_GAME)

    fun getLevelGameList(levelGameList: MutableList<Level>) {

        for (levelNumber in 1..GameConfig.MAX_LEVELS_ON_GAME) {
            val level = createLevel(levelNumber)
            levelGameList.add(level)
        }
    }

    private fun createLevel(levelNumber: Int): Level {

        val fieldGameRow = getFieldGameRow(levelNumber)
        val fieldGameColumn = getFieldGameColumn(fieldGameRow)
        val numberOfBricksToWin = getNumberOfBricksToWin(fieldGameRow, fieldGameColumn)
        val additionalBrick = getAdditionalBrick()
        val lastBrickToAdd = getLastBrickToAdd(additionalBrick)
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
        val additionalLine: Double = levelNumber / intervalComplication

        var min = GameConfig.MIN_LINE_FIELD_ON_GAME + floor(additionalLine)
        var max = min + 1

        if (max >= GameConfig.MAX_LINE_FIELD_ON_GAME) {
            min = GameConfig.MIN_LINE_FIELD_ON_GAME + 2.toDouble()
            max = GameConfig.MAX_LINE_FIELD_ON_GAME.toDouble()
        }

        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getFieldGameColumn(fieldGameRow: Int): Int {

        var min = fieldGameRow
        var max = fieldGameRow + 2
        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getNumberOfBricksToWin(fieldGameRow: Int, fieldGameColumn: Int): Int {
        var maxLine = if (fieldGameRow > fieldGameColumn) fieldGameRow else fieldGameColumn
        maxLine = if (maxLine > 5) 5 else maxLine

        val minLine = if (maxLine >= 5) 4 else GameConfig.MIN_WIN_NUMBER_LINE

        return (Math.random() * (maxLine - minLine) + minLine).toInt()
    }

    private fun getAdditionalBrick(): Int {
        val min = 3
        val max = 4
        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getLastBrickToAdd(additionalBrick: Int): Int {
        val min = 0
        val max = additionalBrick
        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getBonusFillSpeed(
        levelNumber: Int
    ): Float {
        val stepFillBonus = GameConfig.MAX_SPEED_OPEN_BONUS / GameConfig.MAX_LEVELS_ON_GAME
        val min = GameConfig.MAX_SPEED_OPEN_BONUS - levelNumber * stepFillBonus
        return min.toFloat()
    }

    private fun getNegativeBonuses(
        levelNumber: Int,
        fieldGameRow: Int,
        fieldGameColumn: Int
    ): MutableList<Int> {
        val bonuses = mutableListOf<Int>()

        val maxClosedPlaces =
            ((fieldGameRow.toFloat() * fieldGameColumn.toFloat()) / 100) * GameConfig.MAX_CLOSED_PERCENT_GAME_FIELD

        val stepCloseBonus: Double = maxClosedPlaces / GameConfig.MAX_LEVELS_ON_GAME.toDouble()

        val min = ceil(levelNumber * stepCloseBonus/ GameConfig.negativeBonuses.size/2)

        val max = ceil(levelNumber * stepCloseBonus/ GameConfig.negativeBonuses.size)

        var numberBonus = 0

        for (bonus in 0 until GameConfig.negativeBonuses.size) {
            numberBonus = if (bonus == 0) {
                (Math.random() * (max - min) + min).toInt()
            } else {
                (Math.random() * (max - min) + min).toInt()
            }

            bonuses.add(numberBonus)
        }

        return bonuses
    }

    private fun getNumberOfScoreToWin(
        levelNumber: Int,
        fieldGameRow: Int,
        fieldGameColumn: Int
    ): Int {

        val scoreStep =
            (GameConfig.MAX_SCORE_ON_GAME - GameConfig.MIN_SCORE_ON_GAME) / GameConfig.MAX_LEVELS_ON_GAME

        val min = GameConfig.MIN_SCORE_ON_GAME + levelNumber * scoreStep
        val max = min + levelNumber * scoreStep

        return (Math.random() * (max - min) + min).toInt()
    }

    private fun getLevelMaxStep(levelNumber: Int, numberOfScoreToWin: Int): Int {
        val intervalComplicationSteps = numberOfScoreToWin / GameConfig.MAX_LEVELS_ON_GAME

        val additionalSteps =
            (GameConfig.MAX_LEVELS_ON_GAME / 2) - levelNumber * intervalComplicationSteps

        val min = numberOfScoreToWin + additionalSteps
        val max = numberOfScoreToWin + additionalSteps + levelNumber * intervalComplicationSteps

        return (Math.random() * (max - min) + min).toInt()
    }
}