package com.example.bricksGame.components.players.data

import android.content.Context
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.internet.PlayerRecordsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    @ApplicationContext
    private val context: Context,
    var gameConfig: GameConfig,
    private var playerRecordsRepository: PlayerRecordsRepository
) {

   private var playerDatabase: PlayerDatabase = getPlayerDatabase(context)

    private fun getPlayerDatabase(context: Context): PlayerDatabase {
        return PlayerDatabase.getDatabase(context)
    }

    fun getAllPlayers(): Flow<MutableList<Player>> {
        return playerDatabase.getDao().readAllData()
    }

    fun getActivePlayer(): Player {
        return playerDatabase.getDao().getActivePlayer()
    }

    suspend fun addPlayer(player: Player) {
        playerRecordsRepository.setRecords(player)
        playerDatabase.getDao().addData(player)
    }

    fun delete(player: Player) {
        playerDatabase.getDao().delete(player)
    }

    fun update(player: Player) {
        if (!gameConfig.CHEAT) {
            playerRecordsRepository.setRecords(player)
            playerDatabase.getDao().update(player)
        }
    }

    fun setInactiveAllPlayers() {
        playerDatabase.getDao().setInactiveAllPlayers()
    }

}