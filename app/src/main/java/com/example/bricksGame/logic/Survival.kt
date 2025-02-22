package com.example.bricksGame.logic

import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class Survival @Inject constructor(
    private var levelData: LevelData,
    private var levelsConfig: LevelsConfig,
    private var fieldController: FieldController,
    private var levelLogic: LevelLogic
) {

    private val levelsSurvival = levelsConfig.levelsSurvival
    private var currentStage = 0
    private var currentNumberOfBricksToWin = 0
    private var stageCoroutineScope: Job? = null

    fun onSurvivalMode() {
        resetLevel()
        stageCoroutineScope?.cancel()

        stageCoroutineScope = CoroutineScope(Dispatchers.IO).launch {

            levelData.survivalStage.collect() {
                val randomStage = listOf(2).random()

                if (it % randomStage == 0) {
                    setNextStage()
                }
            }
        }
    }

    private fun resetLevel() {
        levelsSurvival.first().negativeBonuses = levelsSurvival.last().negativeBonuses
        levelsSurvival.first().numberOfBricksToWin = levelsSurvival.last().numberOfBricksToWin
        levelsSurvival.first().bonusFillSpeed = levelsSurvival.last().bonusFillSpeed
    }

    private fun setNextStage() {

        val levelSurvival = levelsSurvival.getOrNull(currentStage)

        levelSurvival?.let { level ->

            val placeOnField = levelData.getPlacesOnFields()
            val emptyPlaceOnField =
                placeOnField.filter { it.slot.value is GameObjects.Empty }

            when (val a = placeOnField.size - emptyPlaceOnField.size) {

                in 0 until (placeOnField.size * 0.4).toInt() -> {
                    level.bonusFillSpeed = 0.005f
                    level.negativeBonuses = listOf(2, 2)
                    level.numberOfBricksToWin = 5
                }

                in (placeOnField.size * 0.4).toInt() until (placeOnField.size * 0.7).toInt() -> {
                    level.negativeBonuses = listOf(1, 1)
                    level.bonusFillSpeed = 0.008f
                    level.numberOfBricksToWin = 4
                }

                in (placeOnField.size * 0.7).toInt()..(placeOnField.size * 0.9).toInt() -> {
                    level.negativeBonuses = listOf(1, 0)
                    level.numberOfBricksToWin = 3
                    level.bonusFillSpeed = 0.015f
                }

                else -> {
                    level.numberOfBricksToWin = 3
                    level.negativeBonuses = listOf(0, 0)
                    level.bonusFillSpeed = 0.025f
                }
            }
            levelData.levelWinLine.intValue = level.numberOfBricksToWin


            fieldController.setNegativeSlotOnSurvival(emptyPlaceOnField, level)

            if (currentNumberOfBricksToWin != level.numberOfBricksToWin) {
                checkAllField(placeOnField, level)
                levelLogic.canPlaySoundWin = true
                currentNumberOfBricksToWin = level.numberOfBricksToWin
            } else {
                levelLogic.checkEndLevel()
            }
        }
    }

    private fun checkAllField(placeOnField: MutableList<PlaceOnField>, level: Level) {

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
    }
}