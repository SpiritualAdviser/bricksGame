package com.example.bricksGame.logic.controller

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.Brick
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import javax.inject.Inject
import kotlin.math.max


class BricksController @Inject constructor(
    private var levelData: LevelData,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize
//    var soundController: SoundController,
) {
    private var brickId = 0

//    fun resetData() {
//        brickId = 0
//        levelData._bricksList.clear()
//        levelData._bricksList = createBricksList().toMutableStateList()
//    }

    fun createBricksList(level: Level): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()
        for (i in 0 until level.additionalBrick) {

            bricksList.add(createBrick(level))
        }
        return bricksList
    }

    private fun createBrick(level: Level): Brick {
        val newBrick = Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = ++brickId,
            position = brickId.toString(),
            assetImage = getRandomImage(level),
            borderColor = gameConfig.BRICK_BORDER_COLOR,
            padding =    gameConfig.BRICK_BORDER_SIZE * screenSize.density,
            translationXInit = screenSize.screenWidthPx.toFloat(),
            translationYInit = screenSize.screenHeightPx.toFloat(),
            activeBonusBorder = mutableStateOf(gameConfig.BRICK_BORDER_COLOR),
        )

        return newBrick
    }

    private fun getRandomImage(level: Level): Int {
        var maxColors = max(level.fieldColumn, level.fieldRow)

        if (level.numberOfBricksToWin == 0) maxColors else maxColors += 1

        if (gameConfig.imagesBricks.elementAtOrNull(maxColors) == null) {
            maxColors = gameConfig.imagesBricks.size - 1
        }
        return gameConfig.imagesBricks[(0..maxColors).random()]
    }


    fun removeBrick(brick: Brick) {
//        fieldController.setBricksOnField(brick)

        levelData.bricksListRemove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        val bricksList = levelData.getBricksList()
        val level = levelData.getActiveLevel()

        if (bricksList.size <= gameConfig.MIN_BRICKS_TO_ADD_NEXT) {
            for (i in bricksList.size until gameConfig.MAX_BRICKS_ON_LEVEL) {
                level?.let {
                    levelData.addToBricksList(createBrick(it))
                }
            }
        }
    }
}