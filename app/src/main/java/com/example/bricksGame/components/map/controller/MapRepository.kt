package com.example.bricksGame.components.map.controller

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.helper.SoundController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepository @Inject constructor(
    var gameConfig: GameConfig,
    var soundController: SoundController,
    var levelsConfig: LevelsConfig,
) {

    val visibility = mutableStateOf(false)

    val levelList = levelsConfig.levelGameList.toMutableStateList()
    var currentLevel: Level? = null

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine: String = ""
    var levelStep = mutableIntStateOf(0)

    fun openLevelOnMap() {
//        val playerLevels = playerController.activePlayer
//
//        if (gameConfig.CHEAT) {
//            levelList.forEach {
//                it.isActive = true
//            }
//        } else {
//            levelList.forEach {
//                it.isActive = false
//            }
//
//            playerLevels.activeLevelList?.activeLevelList?.forEach { level ->
//                levelList.find { it.numberLevel == level.numberLevel }?.let {
//                    it.isActive = true
//                }
//            }
//        }
    }

    fun runLevel(level: Level) {
        currentLevel = level
        setLevelOptions(level)
//        playerController.buttonController.navigateToLevelGame()
    }

    fun setLevelOptions(level: Level) {

        levelTarget.intValue = level.numberOfScoreToWin
        levelWinLine =
            if (level.numberOfBricksToWin == 0) "Full" else level.numberOfBricksToWin.toString()
        levelStep.intValue = level.levelMaxStep

        gameConfig.ROWS = level.fieldGameRow
        gameConfig.COLUMNS = level.fieldGameColumn
        gameConfig.WIN_NUMBER_LINE = level.numberOfBricksToWin
        gameConfig.SPEED_OPEN_BONUS = level.bonusFillSpeed
        gameConfig.MAX_BRICKS_ON_LEVEL = level.additionalBrick
        gameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL = level.negativeBonuses
        gameConfig.MIN_BRICKS_TO_ADD_NEXT = level.lastBrickToAdd
    }

    fun changeLevelTargetOnRound(score: Int) {
        levelTarget.intValue =
            if (levelTarget.intValue - score <= 0) 0 else levelTarget.intValue - score
    }

    fun changeLevelStepOnRound() {
        levelStep.intValue = if (levelStep.intValue - 1 <= 0) 0 else levelStep.intValue - 1
    }
}