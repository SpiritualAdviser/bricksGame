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
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor(
    val dataRepository: DataRepository,
    private val levelsConfig: LevelsConfig,
) {

    init {
        Log.d("my", "PlayerRepository")
        start()
    }

    var allPlayers = dataRepository.getAllPlayers()
    var levelGameList = levelsConfig.levelGameList

    private var activePlayer: Player = Player(
        playerName = "Player",
        gameLevelList = GameLevelList()
    )

    private fun start() {
        levelsConfig.setLevelListOnCreatePlayer()
        allPlayers = dataRepository.getAllPlayers()

        CoroutineScope(Dispatchers.IO).launch {

            allPlayers.collectLatest {
                if (it.isEmpty()) {
                    addPlayer("Player")
                }
                Log.d("my", "on allPlayers.collectLatest${it.size}")
            }
        }
    }

    fun getActivePlayer(): Player {
        return activePlayer
    }

    suspend fun addPlayer(name: String) {

        val newPlayer = Player(
            playerName = name,
            gameLevelList = GameLevelList(gameLevelList = levelGameList)
        )
        setActivePlayerOnGame(newPlayer)
    }

    private suspend fun setActivePlayerOnGame(player: Player) {
        activePlayer = player
        dataRepository.setInactiveAllPlayers()
        player.isActive = true
        dataRepository.addPlayer(player)
    }

    fun update(player: Player) {
        dataRepository.setInactiveAllPlayers()
        player.isActive = true
        dataRepository.update(player)
    }
}