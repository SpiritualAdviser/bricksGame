package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


object PlayerViewModel : ViewModel() {

    var playersList = DataRepository.getAllPlayers() as Flow<MutableList<Player>>

    var nameNewPlayer = mutableStateOf("")
    var newPlayer: Player = Player(
        playerName = "default",
        IsActive = true
    )
    var activePlayer: Player = newPlayer

    fun addActivePlayer(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            resetPlayers()

            player.IsActive = true
            activePlayer = player
            update(player)
        }
    }

    fun resetPlayers() {
        DataRepository.setInactiveAllPlayers()
    }

    fun addPlayer() {
        CoroutineScope(Dispatchers.IO).launch {
            newPlayer.playerName = nameNewPlayer.value
            DataRepository.addPlayer(newPlayer)
            activePlayer = newPlayer

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