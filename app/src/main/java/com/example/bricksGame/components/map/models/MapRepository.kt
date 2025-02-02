package com.example.bricksGame.components.map.models

import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.config.Level
import javax.inject.Inject

class MapRepository @Inject constructor() {
    var currentLevel: Level? = null
    var activePlayer: Player? = null
}