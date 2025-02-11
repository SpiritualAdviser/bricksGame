package com.example.bricksGame.components.players.repository

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.LevelPlayer
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
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
    private var gameConfig: GameConfig,
) {

    init {
        Log.d("my", "PlayerRepository")
        start()
    }

    var allPlayers = dataRepository.getAllPlayers()

    private var activePlayer: Player = Player(
        playerName = "Player"
    )

    var playerScore = mutableIntStateOf(0)
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

    private fun updatePlayerOnLevel(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepository.update(player)
        }
    }

    fun updatePlayerOnLevelWin(level: Level) {
        if (gameConfig.CHEAT) {
            return
        }

        val gameLevelList = activePlayer.levels.gameLevelList

        if (!gameConfig.GAME_TYPE_FREE) {

            val currentGameLevel =
                gameLevelList.find { it.numberLevel == level.numberLevel }

            currentGameLevel?.let {
                it.numberLevelPasses += 1
            }

            val nextLevel = gameLevelList.find { it.numberLevel == level.numberLevel + 1 }

            nextLevel?.let {

                val newOpenLevel = listOf(
                    LevelPlayer(
                        numberLevel = it.numberLevel,
                        numberLevelPasses = 0,
                        isActive = true
                    )
                )
                activePlayer.levels.openLevelList += newOpenLevel
                updatePlayerOnLevel(activePlayer)
            }
        }
    }

    fun updateOnIncreaseAchievements() {
        activePlayer.achievements = playerAchievements.intValue
        updatePlayerOnLevel(activePlayer)
    }
}