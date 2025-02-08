package com.example.bricksGame.components.players.repository

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.components.players.data.DataRepository
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

    init {
        Log.d("my", "PlayerRepository")
        start()
    }

    var allPlayers = dataRepository.getAllPlayers()

    private var activePlayer: Player = Player(
        playerName = "Player"
    )
    var playerAchievements = mutableIntStateOf(activePlayer.achievements)
    var nameActivePlayer = mutableStateOf(activePlayer.playerName)

    private fun start() {
        allPlayers = dataRepository.getAllPlayers()

        CoroutineScope(Dispatchers.IO).launch {

            allPlayers.collectLatest {
                if (it.isEmpty()) {
                    addPlayer("Player")
                }
                Log.d("my", "on allPlayers.collectLatest${it.size}")
                activePlayer = dataRepository.getActivePlayer()
                updatePlayerParams()
            }
        }
    }

    fun getActivePlayer(): Player {
        return activePlayer
    }

    suspend fun addPlayer(name: String) {
        val levelGameList = levelsConfig.getNewLevelGameList()

        val newPlayer = Player(
            playerName = name,
        )
        newPlayer.levels.gameLevelList = levelGameList

        setActivePlayerOnGame(newPlayer)
    }

    private suspend fun setActivePlayerOnGame(player: Player) {
        dataRepository.setInactiveAllPlayers()
        player.isActive = true
        dataRepository.addPlayer(player)
        activePlayer = player
        updatePlayerParams()
    }

    private fun updatePlayerParams() {
        activePlayer?.let {
            playerAchievements.intValue = it.achievements
            nameActivePlayer.value = it.playerName
        }
    }

    fun update(player: Player) {
        dataRepository.setInactiveAllPlayers()
        player.isActive = true
        dataRepository.update(player)
    }
}