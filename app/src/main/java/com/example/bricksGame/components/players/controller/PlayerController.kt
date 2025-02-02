package com.example.bricksGame.components.players.controller

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.components.map.models.MapRepository
import com.example.bricksGame.components.players.data.ActiveLevelList
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.GameLevelList
import com.example.bricksGame.components.players.data.LevelPlayer
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.LevelsConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerController @Inject constructor(
    private var dataRepository: DataRepository,
    private var levelsConfig: LevelsConfig,
    private var gameConfig: GameConfig,
    private var mapRepository: MapRepository
) {

    private var newPlayer: Player = Player(
        playerName = "Player",
        isActive = true,
        activeLevelList = ActiveLevelList(),
        gameLevelList = GameLevelList()
    )

    var playersList = dataRepository.getAllPlayers()

    var nameNewPlayer = mutableStateOf("")

    var activePlayer: Player = newPlayer
    var playerScore = mutableIntStateOf(0)
    var playerAchievements = mutableIntStateOf(activePlayer.achievements)
    var nameActivePlayer = mutableStateOf(activePlayer.playerName)

    fun onStartLevel() {
        setPlayerOnGame()
        playerScore.intValue = 0
    }

    private fun setPlayerOnGame() {
        CoroutineScope(Dispatchers.IO).launch {
            var currentPlayer: Player? = dataRepository.getActivePlayer()

            if (currentPlayer == null) {
                levelsConfig.setLevelListOnCreatePlayer()

                activePlayer.gameLevelList.gameLevelList = levelsConfig.levelGameList
                currentPlayer = activePlayer
                nameNewPlayer.value = activePlayer.playerName
                addPlayer()
            } else {
                setGamePlayerParams(currentPlayer)
            }
        }
    }

    fun setNameOnAddPlayer(name: String) {
        nameNewPlayer.value = name
    }

    fun setActivePlayerOnGame(player: Player) {
        resetPlayers()
        player.isActive = true
        update(player)
        setGamePlayerParams(player)
    }

    private fun setGamePlayerParams(player: Player) {
        activePlayer = player
        nameActivePlayer.value = player.playerName
        playerAchievements.intValue = player.achievements
        levelsConfig.levelGameList = player.gameLevelList.gameLevelList
    }

    fun addScore(score: Int) {

        playerScore.value += score

//        mapRepository.changeLevelTargetOnRound(score)

        if (activePlayer.achievements < playerScore.intValue) {
            activePlayer.achievements = playerScore.intValue
            setGamePlayerParams(activePlayer)

            update(activePlayer)
        }
    }

    fun updatePlayerOnLevelWin() {
        val currentLevel = mapRepository.currentLevel
        val dataPlayerList = activePlayer.activeLevelList.activeLevelList

        if (gameConfig.CHEAT) {
            return
        }

        if (currentLevel != null && !gameConfig.GAME_TYPE_FREE) {

            val currentDataLevel =
                dataPlayerList.find { it.numberLevel == currentLevel.numberLevel }
            currentDataLevel?.let {
                it.numberLevelPasses += 1
            }

            val nextLevel = dataPlayerList.find { it.numberLevel == currentLevel.numberLevel + 1 }

            if (nextLevel == null) {
                val nextLevel = listOf(
                    LevelPlayer(
                        numberLevel = currentLevel.numberLevel + 1,
                        numberLevelPasses = 0,
                        isActive = true
                    )
                )
                val newDataPlayerList = dataPlayerList.plus(nextLevel)
                activePlayer.activeLevelList.activeLevelList = newDataPlayerList
            }
        }
        update(activePlayer)
    }

    private fun resetPlayers() {
        dataRepository.setInactiveAllPlayers()
    }

    suspend fun addPlayer() {
        newPlayer.playerName = nameNewPlayer.value

        levelsConfig.setLevelListOnCreatePlayer()
        newPlayer.gameLevelList.gameLevelList = levelsConfig.levelGameList

            dataRepository.addPlayer(newPlayer)
            nameNewPlayer.value = ""
        setActivePlayerOnGame(newPlayer)
    }

    private fun update(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            dataRepository.update(player)
        }
    }

    fun delete(player: Player) {
        dataRepository.delete(player)
    }
}