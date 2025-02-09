package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Animatable
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.Animation
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.Cords
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.logic.CollisionOnLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.max

class BricksController @Inject constructor(
    private var levelData: LevelData,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize,
    private var collisionOnLevel: CollisionOnLevel,
    @ApplicationContext val context: Context
) {
    init {
        Log.d("my", "BricksController_init")
    }

    private var brickId = 0L

    fun createBricksList(level: Level): MutableList<GameObjects.Brick> {
        brickId = 0L
        val bricksList: MutableList<GameObjects.Brick> = mutableListOf()
        for (i in 0 until level.additionalBrick) {

            bricksList.add(createBrick(level))
        }
        return bricksList
    }

    private fun createBrick(level: Level): GameObjects.Brick {
        val baseModel = BaseModel(context)
        baseModel.id = ++brickId
        val newBrick = GameObjects.Brick(
            baseModel = baseModel,
            cords = Cords(),
            animation = Animation(
                translationX = Animatable(initialValue = screenSize.screenWidthPx.toFloat()),
                translationY = Animatable(initialValue = screenSize.screenHeightPx.toFloat())
            )
        )

        newBrick.baseModel.assetImage = getRandomImage(level)

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

    fun observeCenterObjects(brick: GameObjects) {
        collisionOnLevel.observeCenterObjects(brick)
    }

    fun onDragEnd(brick: GameObjects.Brick) {
        collisionOnLevel.takeAPlaces(brick)
    }


//
//
//    fun removeBrick(brick: Brick) {
////        fieldController.setBricksOnField(brick)
//
//        levelData.bricksListRemove(brick)
//        this.checkIfNeedNewBricksList()
//    }
//
//    private fun checkIfNeedNewBricksList() {
//        val bricksList = levelData.getBricksList()
//        val level = levelData.getActiveLevel()
//
//        if (bricksList.size <= gameConfig.MIN_BRICKS_TO_ADD_NEXT) {
//            for (i in bricksList.size until gameConfig.MAX_BRICKS_ON_LEVEL) {
//                level?.let {
//                    levelData.addToBricksList(createBrick(it))
//                }
//            }
//        }
//    }
}