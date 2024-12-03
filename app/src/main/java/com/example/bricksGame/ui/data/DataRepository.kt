package com.example.bricksGame.ui.data

import android.content.Context
import com.example.bricksGame.MainActivity

object DataRepository {

    var playerDatabase: PlayerDatabase? = null
    var playersList: MutableList<Player>? = mutableListOf()

    fun getPlayerDatabase(context: Context) {
        playerDatabase = PlayerDatabase.getDatabase(context)
    }

    fun getAllPlayers(): MutableList<Player>? {
        playersList = playerDatabase?.getDao()?.readAllData()
        return playersList
    }

    suspend fun addPlayer(player: Player) {
        playerDatabase?.getDao()?.addData(player)
    }

    fun delete(player: Player) {
        playerDatabase?.getDao()?.delete(player)
    }
}