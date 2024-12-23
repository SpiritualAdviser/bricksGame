package com.example.bricksGame.components.players.data

import android.content.Context
import com.example.bricksGame.config.GameConfig
import kotlinx.coroutines.flow.Flow

object DataRepository {

    var playerDatabase: PlayerDatabase? = null
//    var playersList: Flow<MutableList<Player>>? = null

    fun getPlayerDatabase(context: Context) {
        playerDatabase = PlayerDatabase.getDatabase(context)
    }

    fun getAllPlayers(): Flow<MutableList<Player>>? {
        return playerDatabase?.getDao()?.readAllData()
    }

    fun getActivePlayer(): Player? {
        return playerDatabase?.getDao()?.getActivePlayer()
    }

    suspend fun addPlayer(player: Player) {
        playerDatabase?.getDao()?.addData(player)
    }

    fun delete(player: Player) {
        playerDatabase?.getDao()?.delete(player)
    }

    fun update(player: Player) {
        if (!GameConfig.CHEAT){
            playerDatabase?.getDao()?.update(player)
        }
    }

    fun setInactiveAllPlayers() {
        playerDatabase?.getDao()?.setInactiveAllPlayers()
    }

}