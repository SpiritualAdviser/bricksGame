package com.example.bricksGame.gameData

import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.LevelsConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameData @Inject constructor(
    private val dataRepository: DataRepository,
    private val levelsConfig: LevelsConfig
) {

    var allPlayers = dataRepository.getAllPlayers()
    var levelGameList = levelsConfig.levelGameList

    lateinit var brickOnFields: MutableList<FieldBrick>

    fun start() {
        levelsConfig.setLevelListOnCreatePlayer()
        allPlayers = dataRepository.getAllPlayers()
    }

    fun setBrickOnField(brickOnFieldInner: MutableList<FieldBrick>) {
        brickOnFields = brickOnFieldInner
    }

    suspend fun addPlayer(newPlayer: Player) {
        dataRepository.addPlayer(newPlayer)
    }
}