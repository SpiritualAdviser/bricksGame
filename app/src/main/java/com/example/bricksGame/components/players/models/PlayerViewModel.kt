package com.example.bricksGame.components.players.models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.bricksGame.components.players.data.PlayerDatabase
import com.example.bricksGame.components.players.data.Player
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : ViewModel() {
    private val readAllData: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase.Companion.getInstance(application).playerDao()
        repository = PlayerRepository(playerDao)
        readAllData = repository.readAllData
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            repository.addPlayer(player)
        }
    }
}