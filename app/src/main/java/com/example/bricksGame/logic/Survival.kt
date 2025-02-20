package com.example.bricksGame.logic

import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Survival @Inject constructor(
    private var levelData: LevelData,
) {

    fun runSurvivalMode(level: Level) {
        println()
        CoroutineScope(Dispatchers.IO).launch {
            levelData.survivalStage.collect {
                it
                println()
            }
        }

    }

    fun getNextStage(level: Level): Level {
     return   Level(
            numberLevel = 1,
            fieldRow = 4,
            fieldColumn = 4,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(2, 3),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 0,
            levelMaxStep = 0
        )


    }
}