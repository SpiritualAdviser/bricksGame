package com.example.bricksGame.components.players.models

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bricksGame.MainActivity

import com.example.bricksGame.components.players.data.PlayerDatabase
import com.example.bricksGame.components.players.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(context: MainActivity) : ViewModel() {
    val playerDatabase = PlayerDatabase.getInstance(context)

    var players = mutableStateListOf<Player>()

    @SuppressLint("StaticFieldLeak")
    private val contextVm = context
    var playerName = mutableStateOf("")


    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addPlayer() {
        coroutineScope.launch(Dispatchers.IO) {
            playerDatabase.getDao().addData(
                Player(
                    playerName = playerName.value
                )
            )
        }
    }

    fun getAllData() {
        playerDatabase.getDao().readAllData().asLiveData().observe(contextVm) {

            if (it.isNotEmpty()) {
                players.clear()
            }
            it.forEach {
                players.add(it)
            }
        }
    }

    fun getPlayerByName(name: String) {
        coroutineScope.launch(Dispatchers.IO) {

        }
    }
}