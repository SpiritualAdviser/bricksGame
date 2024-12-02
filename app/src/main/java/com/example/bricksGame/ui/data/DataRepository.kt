package com.example.bricksGame.ui.data

import com.example.bricksGame.MainActivity
import kotlinx.coroutines.*

object DataRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var playerDatabase: PlayerDatabase? = null
    var playersList: MutableList<Player>? = null

    fun getPlayerDatabase(application: MainActivity) {
        coroutineScope.launch(Dispatchers.IO) {
            playerDatabase = PlayerDatabase.getDatabase(application)
        }
    }

    suspend fun getAllPlayers(): List<Player>? {
        playersList = playerDatabase?.getDao()?.readAllData() as MutableList<Player>?
        return playersList
    }

    suspend fun addPlayer(player: Player) {
        playerDatabase?.getDao()?.addData(player)
    }
}