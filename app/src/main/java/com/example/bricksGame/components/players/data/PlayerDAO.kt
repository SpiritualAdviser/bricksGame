package com.example.bricksGame.components.players.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDAO {

    @Query("SELECT * FROM players")
    fun readAllData(): Flow<List<Player>>

    @Insert
    suspend fun addData(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("DELETE FROM players WHERE playerName == :playerName")
    fun getPlayerByName(playerName: String)
}

