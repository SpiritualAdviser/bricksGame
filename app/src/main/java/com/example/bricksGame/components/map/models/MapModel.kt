package com.example.bricksGame.components.map.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.helper.ButtonController

object MapModel : ViewModel() {

    val levelList = LevelsConfig.levelGameList.toMutableStateList()
    var currentLevel: Level? = null

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine: String = ""
    var levelStep = mutableIntStateOf(0)

    fun openLevelOnMap() {
        val playerLevels = PlayerViewModel.activePlayer.activeLevelList.activeLevelList

        if (GameConfig.CHEAT) {
            levelList.forEach {
                it.isActive = true
            }
        } else {
            levelList.forEach {
                it.isActive = false
            }

            playerLevels.forEach { level ->
                levelList.find { it.numberLevel == level.numberLevel }?.let {
                    it.isActive = true
                }
            }
        }
    }

    fun runLevel(level: Level) {
        currentLevel = level
        setLevelOptions(level)
        ButtonController.navigateToLevelGame()
    }

    fun setLevelOptions(level: Level) {
        levelTarget.intValue = level.numberOfScoreToWin
        levelWinLine =
            if (level.numberOfBricksToWin == 0) "Full" else level.numberOfBricksToWin.toString()
        levelStep.intValue = level.levelMaxStep

        GameConfig.ROWS = level.fieldGameRow
        GameConfig.COLUMNS = level.fieldGameColumn
        GameConfig.WIN_NUMBER_LINE = level.numberOfBricksToWin
        GameConfig.SPEED_OPEN_BONUS = level.bonusFillSpeed
        GameConfig.MAX_BRICKS_ON_LEVEL = level.additionalBrick
        GameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL = level.negativeBonuses
        GameConfig.MIN_BRICKS_TO_ADD_NEXT = level.lastBrickToAdd
    }

    fun changeLevelTargetOnRound(score: Int) {
        levelTarget.intValue =
            if (levelTarget.intValue - score <= 0) 0 else levelTarget.intValue - score
    }

    fun changeLevelStepOnRound() {
        levelStep.intValue = if (levelStep.intValue - 1 <= 0) 0 else levelStep.intValue - 1
    }

}