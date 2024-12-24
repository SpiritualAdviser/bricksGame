package com.example.bricksGame.components.freeGame

import com.example.bricksGame.components.map.models.MapModel.currentLevel
import com.example.bricksGame.components.map.models.MapModel.setLevelOptions
import com.example.bricksGame.config.LevelsConfig

object FreeGameModel {

    fun onRunLevel() {
        currentLevel = LevelsConfig.gameFreeLevel
        setLevelOptions(LevelsConfig.gameFreeLevel)
    }
}