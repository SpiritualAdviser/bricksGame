package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.components.map.models.MapRepository
import com.example.bricksGame.components.players.data.ActiveLevelList
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.GameLevelList
import com.example.bricksGame.components.players.data.LevelPlayer
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.LevelsConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private var dataRepository: DataRepository) : ViewModel() {

    @Inject
    lateinit var levelsConfig: LevelsConfig

    @Inject
    lateinit var gameConfig: GameConfig

    @Inject
    lateinit var mapRepository: MapRepository

    var nameNoEmpty = false

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

    fun setPlayerOnGame() {
        viewModelScope.launch(Dispatchers.IO) {
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
        if (name.isNotEmpty()) {
            nameNoEmpty = true
            nameNewPlayer.value = name
        } else {
            return
        }
    }

    fun setActivePlayerOnGame(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPlayers()

            player.isActive = true

            update(player)
        }
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
        val currentLevel =  mapRepository.currentLevel
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

    fun addPlayer() {

        newPlayer.playerName = nameNewPlayer.value

        levelsConfig.setLevelListOnCreatePlayer()
        newPlayer.gameLevelList.gameLevelList = levelsConfig.levelGameList

        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.addPlayer(newPlayer)
            nameNewPlayer.value = ""
        }
        setActivePlayerOnGame(newPlayer)
        nameNoEmpty = false
    }

    private fun update(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.update(player)
        }
    }

    fun delete(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.delete(player)
        }
    }
}