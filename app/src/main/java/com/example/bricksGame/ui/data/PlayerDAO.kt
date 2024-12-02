package com.example.bricksGame.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDAO {

    @Query("SELECT * FROM players")
    suspend fun readAllData(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(player: Player)

    @Update
    suspend fun update(player: Player)

    @Delete
    suspend fun delete(player: Player)
}

