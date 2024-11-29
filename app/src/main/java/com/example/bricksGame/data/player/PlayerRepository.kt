package com.example.bricksGame.data.player

import androidx.lifecycle.LiveData

class PlayerRepository(private val playerDAO: PlayerDAO) {

    val readAllData: LiveData<List<Player>> = playerDAO.readAllData()

    suspend fun addPlayer(player: Player){
        playerDAO.addData(player)
    }

}