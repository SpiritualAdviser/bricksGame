package com.example.bricksGame.components.players.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDAO {

    @Query("SELECT * FROM players")
    fun readAllData(): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)
}

