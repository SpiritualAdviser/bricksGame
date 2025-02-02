package com.example.bricksGame.components.freeGame

import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.config.LevelsConfig
import javax.inject.Inject

class FreeGameModel @Inject constructor(
    private var levelsConfig: LevelsConfig
) {

    fun onRunLevel(mapModel: MapModel) {
        mapModel.currentLevel = levelsConfig.gameFreeLevel
        mapModel.setLevelOptions(levelsConfig.gameFreeLevel)
    }
}