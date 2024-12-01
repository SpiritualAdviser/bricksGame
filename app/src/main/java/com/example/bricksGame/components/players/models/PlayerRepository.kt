package com.example.bricksGame.components.players.models

import androidx.lifecycle.LiveData
import com.example.bricksGame.components.players.data.PlayerDAO
import com.example.bricksGame.components.players.data.Player


class PlayerRepository(private val playerDAO: PlayerDAO) {

    val readAllData: LiveData<List<Player>> = playerDAO.readAllData()

    suspend fun addPlayer(player: Player){
        playerDAO.addData(player)
    }

}