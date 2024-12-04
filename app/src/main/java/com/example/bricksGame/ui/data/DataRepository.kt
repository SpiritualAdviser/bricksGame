package com.example.bricksGame.ui.data

import android.content.Context
import com.example.bricksGame.MainActivity
import kotlinx.coroutines.flow.Flow

object DataRepository {

    var playerDatabase: PlayerDatabase? = null
//    var playersList: Flow<MutableList<Player>>? = null

    fun getPlayerDatabase(context: Context) {
        playerDatabase = PlayerDatabase.getDatabase(context)
    }

    fun getAllPlayers(): Flow<MutableList<Player>>? {
      return playerDatabase?.getDao()?.readAllData()
//        return playersList
    }

    suspend fun addPlayer(player: Player) {
        playerDatabase?.getDao()?.addData(player)
    }

    fun delete(player: Player) {
        playerDatabase?.getDao()?.delete(player)
    }
}