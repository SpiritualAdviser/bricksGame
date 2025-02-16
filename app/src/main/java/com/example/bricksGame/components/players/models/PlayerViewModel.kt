package com.example.bricksGame.components.players.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private var playerRepository: PlayerRepository,
) : ViewModel() {

    var nameNoEmpty = false

    var allPlayers = playerRepository.allPlayers
    var nameNewPlayer = mutableStateOf("")

    var playerScore = playerRepository.playerScore
    var playerAchievements = playerRepository.playerAchievements
    var nameActivePlayer = playerRepository.nameActivePlayer

    init {
        Log.d("my", "PlayerViewModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "PlayerViewModel_onCleared")
    }

    fun setNameOnAddPlayer(name: String) {
        nameNewPlayer.value = name
    }

    fun setActivePlayerOnGame(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            resetPlayers()
            player.isActive = true
            update(player)
        }
    }

    private fun resetPlayers() {
        playerRepository.dataRepository.setInactiveAllPlayers()
    }

    fun addPlayer() {
        if (nameNewPlayer.value.isNotEmpty()) {
            nameNoEmpty = true

            viewModelScope.launch(Dispatchers.IO) {
                playerRepository.addPlayer(nameNewPlayer.value)
                nameNewPlayer.value = ""
                nameNoEmpty = false
            }
        }
    }

    private fun update(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.update(player)
        }
    }

    fun delete(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.dataRepository.delete(player)
        }
    }
}