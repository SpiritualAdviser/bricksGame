package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.DataRepository.playerDatabase
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object PlayerViewModel : ViewModel() {

    var playersList: MutableList<Player> = mutableStateListOf()
    var nameNewPlayer = mutableStateOf("")
    var newPlayer: Player = Player(
        playerName = "default",
        IsActive = true
    )

    fun addPlayer(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            newPlayer.playerName = name

            DataRepository.addPlayer(newPlayer)
            getAllPlayers()
        }
    }

    fun getAllPlayers() {
        CoroutineScope(Dispatchers.IO).launch {
            val playersList: Deferred<MutableList<Player>?> =
                CoroutineScope(Dispatchers.IO).async() {
                    DataRepository.getAllPlayers()
                }
            update(playersList.await())
        }
    }

    fun update(players: MutableList<Player>?) {
        playersList.clear()
        players?.forEach {
            playersList.add(it)
        }
    }

    fun delete(player: Player) {
        playerDatabase?.getDao()?.delete(player)
    }
}