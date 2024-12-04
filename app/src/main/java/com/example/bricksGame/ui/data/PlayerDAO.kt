package com.example.bricksGame.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDAO {

    @Query("SELECT * FROM players")
    fun readAllData(): Flow<MutableList<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(player: Player)

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player: Player)
}

