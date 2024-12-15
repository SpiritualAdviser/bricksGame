package com.example.bricksGame.components.Map.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.Level
import com.example.bricksGame.ui.LevelsConfig
import com.example.bricksGame.ui.helper.ButtonController

object MapModel : ViewModel() {

    val levelList = LevelsConfig.gameLevels.toMutableStateList()
    var currentLevel: Level? = null

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine: String = ""
    var levelStep = mutableIntStateOf(0)

    fun openLevelOnMap() {
        val playerLevels = PlayerViewModel.activePlayer.activeLevelList.activeLevelList

        levelList.forEachIndexed { index, level ->
            if (index < playerLevels.size && playerLevels[index].isActive) {
                levelList[index].isActive = true
                levelList[index].numberLevelPasses = level.numberLevelPasses
            } else {
                levelList[index].isActive = false
                levelList[index].numberLevelPasses = 0
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
        levelStep.intValue = level.levelTime

        GameConfig.ROWS = level.fieldGameRow
        GameConfig.COLUMNS = level.fieldGameColumn
        GameConfig.WIN_NUMBER_LINE = level.numberOfBricksToWin
        GameConfig.SPEED_OPEN_BONUS = level.bonusFillSpeed
        GameConfig.MAX_BRICKS_ON_LEVEL = level.additionalBrick
    }

    fun changeLevelTargetOnRound(score: Int) {
        levelTarget.intValue =
            if (levelTarget.intValue - score <= 0) 0 else levelTarget.intValue - score
        levelStep.intValue = if (levelStep.intValue - 1 <= 0) 0 else levelStep.intValue - 1
    }

}