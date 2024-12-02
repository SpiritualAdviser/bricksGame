package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.data.DataRepository
import com.example.bricksGame.ui.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object PlayerViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    var playersList: MutableList<Player> = mutableStateListOf()
    var nameNewPlayer = mutableStateOf("")

    suspend fun addPlayer(name: String) {
        DataRepository.addPlayer(
            Player(
                playerName = name,
                score = 0,
                achievements = 0,
                IsActive = false
            )
        )
        getAllPlayers()
    }

    fun getAllPlayers() {
        playersList.clear()
        coroutineScope.launch(Dispatchers.IO) {
            DataRepository.getAllPlayers()?.forEach {
                playersList.add(it)
            }
        }
    }
}