package com.example.bricksGame.logic

import android.util.Log
import com.example.bricksGame.components.levelGame.controller.BonusController
import com.example.bricksGame.components.levelGame.controller.BricksController
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ButtonController
import javax.inject.Inject

class StartLevelLogic @Inject constructor(
    var gameConfig: GameConfig,
    private var buttonController: ButtonController,
    private var levelData: LevelData,
    private var fieldController: FieldController,
    private var bricksController: BricksController,
    private var bonusController: BonusController,
    private var collisionOnLevel: CollisionOnLevel,
    private var roundLogic: RoundLogic
) {

    init {
        Log.d("my", "LevelLogic_init")
    }

    private var activeLevel: Level? = null
    private var levelRows = mutableListOf<List<PlaceOnField>>()
    private var levelColumns = mutableListOf<List<PlaceOnField>>()

    fun onStartLevel(level: Level) {
        activeLevel = level
        activeLevel?.let {
            createLevelResources(it)
            setRowsAndColumnsOnLevel(it)
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

    private fun setRowsAndColumnsOnLevel(level: Level) {
        var column: List<PlaceOnField>
        var row: List<PlaceOnField>
        levelRows.clear()
        levelColumns.clear()

        for (index in 0 until level.fieldRow) {
            row = levelData.getPlacesOnFields().filter { index == it.position.second }
            levelRows.add(row)
        }

        for (index in 0 until level.fieldColumn) {
            column = levelData.getPlacesOnFields().filter { index == it.position.first }
            levelColumns.add(column)
        }
    }

    fun goToLevel() {
        buttonController.navigateToLevelGame()
    }

    fun goToHome() {
        buttonController.navigateToHome()
    }
}