package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object PlayerViewModel : ViewModel() {
    var newPlayer: Player = Player(
        playerName = "Player",
        isActive = true
    )

    var playersList = DataRepository.getAllPlayers() as Flow<MutableList<Player>>
    var nameNewPlayer = mutableStateOf("")

    var activePlayer: Player = newPlayer
    var playerScore = mutableIntStateOf(0)
    var playerAchievements = mutableIntStateOf(activePlayer.achievements)

    fun onStartLevel() {
        setPlayerOnGame()
    }

    fun setPlayerOnGame() {
        CoroutineScope(Dispatchers.IO).launch {
            var currentPlayer: Player? = DataRepository.getActivePlayer()
            if (currentPlayer == null) {
                currentPlayer = newPlayer
                nameNewPlayer.value = newPlayer.playerName
                addPlayer()
            }
            setGamePlayerParams(currentPlayer)
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
        playerScore.intValue = 0
        nameNewPlayer.value = player.playerName
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