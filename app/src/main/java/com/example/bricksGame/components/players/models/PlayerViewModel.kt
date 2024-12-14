package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.Map.models.MapModel
import com.example.bricksGame.ui.data.ActiveLevelList
import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.LevelPlayer
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object PlayerViewModel : ViewModel() {
    var newPlayer: Player = Player(
        playerName = "Player",
        isActive = true,
        activeLevelList = ActiveLevelList(),
    )

    var playersList = DataRepository.getAllPlayers() as Flow<MutableList<Player>>
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
        CoroutineScope(Dispatchers.IO).launch {
            var currentPlayer: Player? = DataRepository.getActivePlayer()
            if (currentPlayer == null) {
                currentPlayer = activePlayer
                nameNewPlayer.value = activePlayer.playerName
                addPlayer()
            } else {
                setGamePlayerParams(currentPlayer)
            }
        }
    }

    fun addActivePlayer(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            resetPlayers()

            player.isActive = true

            update(player)
        }
        setGamePlayerParams(player)
    }

    fun setGamePlayerParams(player: Player) {
        activePlayer = player
        nameActivePlayer.value = player.playerName
        playerAchievements.intValue = player.achievements
    }

    fun addScore(score: Int) {
        playerScore.value += score

        if (activePlayer.achievements < playerScore.intValue) {
            activePlayer.achievements = playerScore.intValue
            setGamePlayerParams(activePlayer)

            update(activePlayer)
        }
    }

    fun updatePlayerOnLevelWin() {
        val currentLevel = MapModel.currentLevel
        var dataPlayerList = activePlayer.activeLevelList.activeLevelList


        if (currentLevel != null) {
            dataPlayerList[currentLevel.numberLevel - 1].numberLevelPasses += 1

            val nextLevel = listOf(
                LevelPlayer(
                    numberLevel = currentLevel.numberLevel + 1,
                    numberLevelPasses = 0,
                    isActive = true
                )
            )
            val newDataPlayerList = dataPlayerList.plus(nextLevel)
            activePlayer.activeLevelList.activeLevelList = newDataPlayerList
            update(activePlayer)
        }
    }

    fun resetPlayers() {
        DataRepository.setInactiveAllPlayers()
    }

    fun addPlayer() {
        newPlayer.playerName = nameNewPlayer.value
        CoroutineScope(Dispatchers.IO).launch {
            DataRepository.addPlayer(newPlayer)
            nameNewPlayer.value = ""
        }

        addActivePlayer(newPlayer)
    }

    fun update(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            DataRepository.update(player)
        }
    }

    fun delete(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            DataRepository.delete(player)
        }
    }
}