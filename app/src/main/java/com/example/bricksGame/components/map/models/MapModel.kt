package com.example.bricksGame.components.map.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var levelsConfig: LevelsConfig

    @Inject
    lateinit var gameConfig: GameConfig

    @Inject
    lateinit var buttonController: ButtonController

    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var mapRepository: MapRepository

    val visibility = mutableStateOf(false)

    val levelList = levelsConfig.levelGameList.toMutableStateList()
    var currentLevel: Level? = null

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine: String = ""
    var levelStep = mutableIntStateOf(0)

    fun openLevelOnMap() {
//       val playerLevels = playerViewModel.activePlayer.activeLevelList.activeLevelList
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
//            playerLevels.forEach { level ->
//                levelList.find { it.numberLevel == level.numberLevel }?.let {
//                    it.isActive = true
//                }
//            }
//        }
    }

    fun runLevel(level: Level) {
        currentLevel = level
        mapRepository.currentLevel = currentLevel
        setLevelOptions(level)
        buttonController.navigateToLevelGame()
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