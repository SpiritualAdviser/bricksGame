package com.example.bricksGame.logic

import android.util.Log
import com.example.bricksGame.components.levelGame.controller.BonusController
import com.example.bricksGame.components.levelGame.controller.BricksController
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ButtonController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartLevelLogic @Inject constructor(
    var gameConfig: GameConfig,
    private var buttonController: ButtonController,
    private var levelData: LevelData,
    private var fieldController: FieldController,
    private var bricksController: BricksController,
    private var bonusController: BonusController,
    private var collisionOnLevel: CollisionOnLevel,
    private var roundLogic: RoundLogic,
    private val levelLogic: LevelLogic,
    private var levelsConfig: LevelsConfig,
    private var survival: Survival
) {

    init {
        Log.d("my", "StartLevelLogic_init")
    }

    private var activeLevel: Level? = null

    private var placesOnField:
            MutableList<PlaceOnField>? = null


    fun onStartFreeGame() {
        survival.onSurvivalMode()
        val level = levelsConfig.levelsSurvival[0]
        onStartLevel(level, true)
    }

    fun onStartLevel(level: Level, freeGame: Boolean = false) {
        levelData.freeGame = freeGame
        activeLevel = level
        activeLevel?.let {
            createLevelResources(it)
            levelLogic.onStartLevel(it)
            goToLevel()
        }
    }

    private fun createLevelResources(level: Level) {
        levelData.setActiveLevel(level)
        roundLogic.setActiveLevel(level)

        placesOnField = fieldController.createPlacesOnFieldList(level)
        val bricksOnLevel = bricksController.createBricksList(level)
        val bonusesOnLevel = bonusController.createBonusList()
        placesOnField?.let {

            levelData.setPlacesOnField(it)
            levelData.setBricksList(bricksOnLevel)
            levelData.setBonusList(bonusesOnLevel)

            fieldController.setNegativeSlotOnField(it, level)
            collisionOnLevel.setPlacesFieldOnCollision(it)
            collisionOnLevel.runCollision(true)
        }
    }


    private fun goToLevel() {
        buttonController.navigateToLevelGame()
    }

    fun goToHome() {
        buttonController.navigateToHome()
    }

    fun goToMap() {
        buttonController.navigateToMap()
    }
}