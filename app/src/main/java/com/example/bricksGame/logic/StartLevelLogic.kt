package com.example.bricksGame.logic

import android.util.Log
import com.example.bricksGame.components.levelGame.controller.BonusController
import com.example.bricksGame.components.levelGame.controller.BricksController
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.gameData.LevelData
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
    private var levelsConfig: LevelsConfig
) {

    init {
        Log.d("my", "StartLevelLogic_init")
    }

    private var activeLevel: Level? = null

    fun onStartFreeGame() {
        val level = levelsConfig.gameFreeLevel
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

        val placesOnField = fieldController.createPlacesOnFieldList(level)
        val bricksOnLevel = bricksController.createBricksList(level)
        val bonusesOnLevel = bonusController.createBonusList()

        levelData.setPlacesOnField(placesOnField)
        levelData.setBricksList(bricksOnLevel)
        levelData.setBonusList(bonusesOnLevel)

        fieldController.setNegativeSlotOnField(placesOnField, level)
        collisionOnLevel.setPlacesFieldOnCollision(placesOnField)
        collisionOnLevel.runCollision(true)
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