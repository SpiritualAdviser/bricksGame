package com.example.bricksGame.components.players.repository

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.players.data.ActiveLevelList
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.GameLevelList
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.LevelsConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor(
    val dataRepository: DataRepository,
    private val levelsConfig: LevelsConfig,
) {
    private var newPlayer: Player = Player(
        playerName = "Player",
        isActive = true,
        activeLevelList = ActiveLevelList(),
        gameLevelList = GameLevelList()
    )

    init {
        Log.d("PlayerViewModel", "GameData_init")
    }

    var allPlayers = dataRepository.getAllPlayers()
    var levelGameList = levelsConfig.levelGameList

    private var activePlayer: Player = newPlayer

    fun start() {
        levelsConfig.setLevelListOnCreatePlayer()
        allPlayers = dataRepository.getAllPlayers()

        CoroutineScope(Dispatchers.IO).launch {
            allPlayers?.collectLatest {
                if (it.isEmpty()) {
                    addPlayer("Player")
                }
                println()
            }
        }
    }

    fun getActivePlayer(): Player {
        return activePlayer
    }

    suspend fun addPlayer(name: String) {

        val newPlayer = newPlayer
        newPlayer.apply {
            playerName = name
            gameLevelList.gameLevelList = levelGameList
        }
        setActivePlayerOnGame(newPlayer)
    }

    private suspend fun setActivePlayerOnGame(player: Player) {
        dataRepository.setInactiveAllPlayers()
        player.isActive = true
        dataRepository.addPlayer(newPlayer)
        activePlayer = player
    }
}