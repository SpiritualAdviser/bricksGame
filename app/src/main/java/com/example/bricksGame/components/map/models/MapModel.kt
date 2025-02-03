package com.example.bricksGame.components.map.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ButtonController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapModel @Inject constructor(
    playerRepository: PlayerRepository,
    private val gameConfig: GameConfig,
    private val buttonController: ButtonController,
    private val dataRepository: DataRepository,
    private val levelData: LevelData,
) : ViewModel() {

    val visibility = mutableStateOf(false)

    val levelList = playerRepository.levelGameList

    var currentLevel: Level? = null

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine: String = ""
    var levelStep = mutableIntStateOf(0)

    fun openLevelOnMap() {

        viewModelScope.launch(Dispatchers.IO) {
            val player = dataRepository.getActivePlayer()
            if (gameConfig.CHEAT) {
                levelList.forEach {
                    it.isActive = true
                }
            } else {
                levelList.forEach {
                    it.isActive = false
                }

                player?.let { playerLet ->
                    playerLet.activeLevelList.activeLevelList.forEach { activeLevel ->
                        levelList.find { it.numberLevel == activeLevel.numberLevel }?.let {
                            it.isActive = true
                        }
                    }
                }
            }
        }
    }

    fun runLevel(level: Level) {
        currentLevel = level
        levelData.currentLevel = level
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