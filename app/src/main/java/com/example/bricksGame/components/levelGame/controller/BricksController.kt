package com.example.bricksGame.components.levelGame.controller

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.example.bricksGame.components.levelGame.models.Brick
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.LevelLogic
import javax.inject.Inject
import kotlin.math.max


class BricksController @Inject constructor(
    private var levelLogic: LevelLogic,
    private var levelData: LevelData,
    private var fieldController: FieldController,
    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
    var soundController: SoundController,
) {
    private var brickId = 0

    fun resetData() {
        brickId = 0
        levelData._bricksList.clear()
        levelData._bricksList = createBricksList().toMutableStateList()
    }

    fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()
        for (i in 0 until gameConfig.MAX_BRICKS_ON_LEVEL) {

            bricksList.add(createBrick())
        }
        return bricksList
    }

    private fun getRandomImage(): Int {
        var maxColors: Int = 0
        maxColors = max(gameConfig.COLUMNS, gameConfig.ROWS)

        if (gameConfig.WIN_NUMBER_LINE == 0) maxColors else maxColors += 1

        if (gameConfig.imagesBricks.elementAtOrNull(maxColors) == null) {
            maxColors = gameConfig.imagesBricks.size - 1
        }
        return gameConfig.imagesBricks[(0..maxColors).random()]
    }

    fun removeBrick(brick: Brick) {
        fieldController.setBricksOnField(brick)
        levelData._bricksList.remove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        if (levelData._bricksList.size <= gameConfig.MIN_BRICKS_TO_ADD_NEXT) {
            for (i in levelData._bricksList.size until gameConfig.MAX_BRICKS_ON_LEVEL) {
                soundController.pushCristal()
                levelData._bricksList.add(createBrick())
            }
        }
    }

    private fun createBrick(): Brick {
        val newBrick = Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = ++brickId,
            position = brickId.toString(),
            assetImage = getRandomImage(),
            gameConfig = gameConfig,
            screenSize = screenSize,
            soundController = soundController,
            levelLogic = levelLogic,
            bricksController = this
        )

        newBrick.borderColor = gameConfig.BRICK_BORDER_COLOR
        newBrick.activeBonusBorder = mutableStateOf(gameConfig.BRICK_BORDER_COLOR)
        return newBrick
    }
}