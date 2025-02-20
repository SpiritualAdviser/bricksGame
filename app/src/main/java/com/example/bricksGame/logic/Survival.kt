package com.example.bricksGame.logic

import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.gameData.LevelData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Survival @Inject constructor(
    private var levelData: LevelData,
    private var levelsConfig: LevelsConfig,
    private var fieldController: FieldController
) {

    private val levelSurvival = levelsConfig.gameSurvival

    fun onSurvivalMode() {

        CoroutineScope(Dispatchers.Main).launch {
            levelData.survivalStage.collect {
                if (it % 5 == 0) {
                    val placesOnField = levelData.getPlacesOnFields()
                    val nextLevel = getNextStage()
                    fieldController.setNegativeSlotOnSurvival(placesOnField, nextLevel)
                }
            }
        }
    }

    private fun getNextStage(): Level {
        return levelSurvival
    }
}