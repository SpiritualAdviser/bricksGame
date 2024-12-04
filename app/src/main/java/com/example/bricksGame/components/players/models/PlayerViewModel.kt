package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    var playersList = DataRepository.getAllPlayers() as Flow<MutableList<Player>>

    var nameNewPlayer = mutableStateOf("")
    var newPlayer: Player = Player(
        playerName = "default",
        IsActive = true
    )

    fun addPlayer() {
        CoroutineScope(Dispatchers.IO).launch {
            newPlayer.playerName = nameNewPlayer.value
            DataRepository.addPlayer(newPlayer)
        }
    }

    fun update(players: MutableList<Player>?) {

    }

    fun delete(player: Player) {

    }
}