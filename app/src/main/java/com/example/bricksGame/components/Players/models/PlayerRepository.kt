package com.example.bricksGame.components.Players.models

import androidx.lifecycle.LiveData
import com.example.bricksGame.components.Players.data.Player
import com.example.bricksGame.components.Players.data.PlayerDAO

class PlayerRepository(private val playerDAO: PlayerDAO) {

    val readAllData: LiveData<List<Player>> = playerDAO.readAllData()

    suspend fun addPlayer(player: Player){
        playerDAO.addData(player)
    }

}