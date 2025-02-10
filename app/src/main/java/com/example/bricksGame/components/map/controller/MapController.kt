package com.example.bricksGame.components.map.controller

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapController @Inject constructor(
    private var playerRepository: PlayerRepository,
    private val gameConfig: GameConfig,
) {

    init {
        Log.d("my", "MapModel_init")
    }

    private var playerLevelsState: SnapshotStateList<Level> = mutableStateListOf<Level>()

    fun setLevelOnMap() {

        val player = playerRepository.getActivePlayer()

        playerLevelsState.clear()
        playerLevelsState.addAll(player.levels.gameLevelList)

        val openLevelsOnPlayer = player.levels.openLevelList

        if (gameConfig.CHEAT) {
            playerLevelsState.forEach {
                it.isActive = true
            }
        } else {
            playerLevelsState.forEach {
                it.isActive = false
            }

            openLevelsOnPlayer.forEach { openLevel ->
                playerLevelsState.find { it.numberLevel == openLevel.numberLevel }?.let {
                    it.isActive = true
                }
            }
        }
    }

    fun getPlayerLevels(): SnapshotStateList<Level> {
        if (playerLevelsState.isEmpty()) {
            setLevelOnMap()
        }
        return playerLevelsState
    }
}