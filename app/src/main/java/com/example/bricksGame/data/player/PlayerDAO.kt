package com.example.bricksGame.data.player

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface PlayerDAO {

    @Query("SELECT * FROM player")
    fun readAllData(): LiveData<List<Player>>

    @Query("SELECT * FROM player WHERE id=:id")
    fun getById(id: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(player: Player)

    @Update
    fun update(player: Player)

    @Update
    fun delete(player: Player)
}

