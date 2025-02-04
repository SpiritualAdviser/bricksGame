package com.example.bricksGame.components.map.controller

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ButtonController
import javax.inject.Inject

class MapController @Inject constructor(
    private var playerRepository: PlayerRepository,
    private val gameConfig: GameConfig,
    private val buttonController: ButtonController,
    private val levelData: LevelData,
) {

    init {
        Log.d("my", "MapController")
    }

    var playerLevels: SnapshotStateList<Level> = mutableStateListOf()

    fun runLevel(level: Level) {
        levelData.currentLevel = level
        setLevelOptions(level)
        buttonController.navigateToLevelGame()
    }

    private fun setLevelOptions(level: Level) {
//        levelTarget.intValue = level.numberOfScoreToWin
//        levelWinLine =
//            if (level.numberOfBricksToWin == 0) "Full" else level.numberOfBricksToWin.toString()
//        levelStep.intValue = level.levelMaxStep

        gameConfig.ROWS = level.fieldGameRow
        gameConfig.COLUMNS = level.fieldGameColumn
        gameConfig.WIN_NUMBER_LINE = level.numberOfBricksToWin
        gameConfig.SPEED_OPEN_BONUS = level.bonusFillSpeed
        gameConfig.MAX_BRICKS_ON_LEVEL = level.additionalBrick
        gameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL = level.negativeBonuses
        gameConfig.MIN_BRICKS_TO_ADD_NEXT = level.lastBrickToAdd
    }

    fun openLevelOnMap() {
        val player = playerRepository.getActivePlayer()
        this.playerLevels.clear()
        this.playerLevels.addAll(player.levels.gameLevelList)
        val openLevelsOnPlayer = player.levels.openLevelList

        if (gameConfig.CHEAT) {
            this.playerLevels.forEach {
                it.isActive = true
            }
        } else {
            this.playerLevels.forEach {
                it.isActive = false
            }

            openLevelsOnPlayer.forEach { openLevel ->
                this.playerLevels.find { it.numberLevel == openLevel.numberLevel }?.let {
                    it.isActive = true
                }
            }
        }
    }
}