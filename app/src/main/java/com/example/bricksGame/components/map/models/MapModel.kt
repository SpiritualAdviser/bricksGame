package com.example.bricksGame.components.map.models

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.logic.LevelLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapModel @Inject constructor(
    private var playerRepository: PlayerRepository,
    private val gameConfig: GameConfig,
    private var levelLogic: LevelLogic,
) : ViewModel() {

    init {
        Log.d("my", "MapModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "MapModel_onCleared")
    }

    var playerLevels: SnapshotStateList<Level> = openLevelOnMap()

    private fun openLevelOnMap(): SnapshotStateList<Level> {
        val player = playerRepository.getActivePlayer()
        val levelsState = mutableStateListOf<Level>()

        levelsState.clear()
        levelsState.addAll(player.levels.gameLevelList)
        val openLevelsOnPlayer = player.levels.openLevelList

        if (gameConfig.CHEAT) {
            levelsState.forEach {
                it.isActive = true
            }
        } else {
            levelsState.forEach {
                it.isActive = false
            }

            openLevelsOnPlayer.forEach { openLevel ->
                levelsState.find { it.numberLevel == openLevel.numberLevel }?.let {
                    it.isActive = true
                }
            }
        }
        return levelsState
    }

    fun runLevel(level: Level) {
        levelLogic.onStartLevel(level)
    }

    fun goToHome() {
        levelLogic.goToHome()
    }

//    fun changeLevelTargetOnRound(score: Int) {
//        levelTarget.intValue =
//            if (levelTarget.intValue - score <= 0) 0 else levelTarget.intValue - score
//    }
//
//    fun changeLevelStepOnRound() {
//        levelStep.intValue = if (levelStep.intValue - 1 <= 0) 0 else levelStep.intValue - 1
//    }
}