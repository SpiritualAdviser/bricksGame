package com.example.bricksGame.logic

import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Survival @Inject constructor(
    private var levelData: LevelData,
    private var levelsConfig: LevelsConfig,
    private var fieldController: FieldController,
    private var levelLogic: LevelLogic
) {

    private val levelsSurvival = levelsConfig.levelsSurvival
    private var currentStage = 0
    private var currentNumberOfBricksToWin = 0

    fun onSurvivalMode() {

        CoroutineScope(Dispatchers.Main).launch {

            levelData.survivalStage.collect {
                val randomStage = listOf(2).random()

                if (it % randomStage == 0) {
                    setNextStage()
                }
            }
        }
    }

    private fun setNextStage() {

        val levelSurvival = levelsSurvival.getOrNull(currentStage)

        levelSurvival?.let { level ->

            val placeOnField = levelData.getPlacesOnFields()
            val emptyPlaceOnField =
                placeOnField.filter { it.slot.value is GameObjects.Empty }

            when (val a = placeOnField.size - emptyPlaceOnField.size) {

                in 0 until (placeOnField.size * 0.2).toInt() -> {
                    level.bonusFillSpeed = 0.01f
                    level.negativeBonuses = listOf(2, 1)
                    level.numberOfBricksToWin = 5
                }

                in (placeOnField.size * 0.2).toInt() until (placeOnField.size * 0.4).toInt() -> {
                    level.negativeBonuses = listOf(1, 1)
                    level.bonusFillSpeed = 0.02f
                    level.numberOfBricksToWin = 5
                }

                in (placeOnField.size * 0.4).toInt() until (placeOnField.size * 0.6).toInt() -> {
                    level.numberOfBricksToWin = 4
                    level.negativeBonuses = listOf(0, 1)
                    level.bonusFillSpeed = 0.03f
                }

                in (placeOnField.size * 0.6).toInt()..(placeOnField.size * 0.8).toInt() -> {
                    level.negativeBonuses = listOf(1, 0)
                    level.numberOfBricksToWin = 3
                    level.bonusFillSpeed = 0.04f
                }

                else -> {
                    level.numberOfBricksToWin = 3
                    level.negativeBonuses = listOf(0, 0)
                    level.bonusFillSpeed = 0.05f
                }
            }
            levelData.levelWinLine.intValue = level.numberOfBricksToWin

            fieldController.setNegativeSlotOnSurvival(emptyPlaceOnField, levelSurvival)


            if (currentNumberOfBricksToWin != level.numberOfBricksToWin) {

                placeOnField.forEach { place ->
                    val updatedPlaces = levelData.getPlacesOnFields()
                    val placeToReset = updatedPlaces.find { it.position == place.position }

                    placeToReset?.let {
                        when (val innerSlot = it.slot.value) {
                            is GameObjects.Brick -> {
                                if (!innerSlot.baseModel.needReset) {
                                    levelLogic.checkRound(it, true)
                                }
                            }

                            else -> {}
                        }
                    }
                }
                currentNumberOfBricksToWin = level.numberOfBricksToWin
            } else {
                levelLogic.checkEndLevel()
            }
        }
    }
}