package com.example.bricksGame.components.players.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.map.models.MapRepository
import com.example.bricksGame.components.players.controller.PlayerController
import com.example.bricksGame.components.players.data.ActiveLevelList
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.GameLevelList
import com.example.bricksGame.components.players.data.LevelPlayer
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.LevelsConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private var playerController: PlayerController) :
    ViewModel() {

    var nameNoEmpty = false
    var playersList = playerController.playersList
    var nameNewPlayer = playerController.nameNewPlayer

    private var activePlayer: Player = playerController.activePlayer

    var playerScore = playerController.playerScore
    var playerAchievements = playerController.playerAchievements
    var nameActivePlayer = playerController.nameActivePlayer

    fun setNameOnAddPlayer(name: String) {
        if (name.isNotEmpty()) {
            nameNoEmpty = true
            playerController.setNameOnAddPlayer(name)
        } else {
            return
        }
    }

    fun setActivePlayerOnGame(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerController.setActivePlayerOnGame(player)
        }
    }

    fun addPlayer() {
        viewModelScope.launch(Dispatchers.IO) {
            playerController.addPlayer()
            nameNoEmpty = false
        }
    }

    fun delete(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerController.delete(player)
        }
    }
}