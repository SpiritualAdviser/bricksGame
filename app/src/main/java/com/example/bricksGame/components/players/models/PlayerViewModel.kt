package com.example.bricksGame.components.players.models

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private var playerRepository: PlayerRepository,
) :
    ViewModel() {

    var nameNoEmpty = false

    var allPlayers = playerRepository.allPlayers
    var nameNewPlayer = mutableStateOf("")

    private var activePlayer: Player = playerRepository.getActivePlayer()
    var playerScore = mutableIntStateOf(0)
    var playerAchievements = mutableIntStateOf(activePlayer.achievements)
    var nameActivePlayer = mutableStateOf(activePlayer.playerName)

    init {
        Log.d("my", "PlayerViewModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "PlayerViewModel_onCleared")
    }

    fun setNameOnAddPlayer(name: String) {
        nameNewPlayer.value = name
    }

    fun setActivePlayerOnGame(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPlayers()
            player.isActive = true
            update(player)
        }
//        setGamePlayerParams(player)
    }

    fun addScore(score: Int) {

//        playerScore.value += score
//
//        MapModel.changeLevelTargetOnRound(score)
//
//        if (activePlayer.achievements < playerScore.intValue) {
//            activePlayer.achievements = playerScore.intValue
//            setGamePlayerParams(activePlayer)
//
//            update(activePlayer)
//        }
    }

    fun updatePlayerOnLevelWin() {
//        val currentLevel = MapModel.currentLevel
//        val dataPlayerList = activePlayer.activeLevelList.activeLevelList
//
//        if (gameConfig.CHEAT) {
//            return
//        }
//
//        if (currentLevel != null && !gameConfig.GAME_TYPE_FREE) {
//
//            val currentDataLevel =
//                dataPlayerList.find { it.numberLevel == currentLevel.numberLevel }
//            currentDataLevel?.let {
//                it.numberLevelPasses += 1
//            }
//
//            val nextLevel = dataPlayerList.find { it.numberLevel == currentLevel.numberLevel + 1 }
//
//            if (nextLevel == null) {
//                val nextLevel = listOf(
//                    LevelPlayer(
//                        numberLevel = currentLevel.numberLevel + 1,
//                        numberLevelPasses = 0,
//                        isActive = true
//                    )
//                )
//                val newDataPlayerList = dataPlayerList.plus(nextLevel)
//                activePlayer.activeLevelList.activeLevelList = newDataPlayerList
//            }
//        }
//        update(activePlayer)
    }

    private fun resetPlayers() {
        playerRepository.dataRepository.setInactiveAllPlayers()
    }

    fun addPlayer() {
        if (nameNewPlayer.value.isNotEmpty()) {
            nameNoEmpty = true

            viewModelScope.launch(Dispatchers.IO) {
                playerRepository.addPlayer(nameNewPlayer.value)
                nameNewPlayer.value = ""
                nameNoEmpty = false
            }
        }
    }

    private fun update(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.update(player)
        }
    }

    fun delete(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.dataRepository.delete(player)
        }
    }
}