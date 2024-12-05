package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


object PlayerViewModel : ViewModel() {

    var playersList = DataRepository.getAllPlayers() as Flow<MutableList<Player>>

    var nameNewPlayer = mutableStateOf("")
    var newPlayer: Player = Player(
        playerName = "default",
        IsActive = true
    )
    var activePlayer = newPlayer

    fun addPlayer() {

        CoroutineScope(Dispatchers.IO).launch {
            newPlayer.playerName = nameNewPlayer.value
            DataRepository.addPlayer(newPlayer)
            activePlayer = newPlayer
        }
    }

    fun update() {
        CoroutineScope(Dispatchers.IO).launch {
            playersList.collect {
                it.forEach {
                    if (it.id != activePlayer.id) {
                        it.playerName = "update"
                        it.IsActive = false
                        DataRepository.update(it)
                    }
                }
            }
        }
    }

    fun delete(player: Player) {
        CoroutineScope(Dispatchers.IO).launch {
            DataRepository.delete(player)
        }
    }
}