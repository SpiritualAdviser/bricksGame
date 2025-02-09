package com.example.bricksGame.logic

import android.content.Context
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoundLogic @Inject constructor(
    @ApplicationContext val context: Context,
    private var gameConfig: GameConfig,
    private val levelData: LevelData,
    private val gameObjectBuilder: GameObjectBuilder,
    private val levelLogic: LevelLogic,
    private val soundController: SoundController
) {
    private var activeLevel: Level? = null

    fun setActiveLevel(level: Level) {
        activeLevel = level
    }

    fun onCollision(gameObj: GameObjects, placeOnField: PlaceOnField, onTakePlace: Boolean) {
        sortOnCollision(gameObj, placeOnField, onTakePlace)
    }

    fun outOfCollision(gameObj: GameObjects, placeOnField: PlaceOnField) {
        placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR
    }

    private fun sortOnCollision(
        gameObj: GameObjects,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (gameObj) {
            is GameObjects.Bonus -> onBonus(gameObj, placeOnField, onTakePlace)
            is GameObjects.Brick -> onBrick(gameObj, placeOnField, onTakePlace)
            is GameObjects.Empty -> return
            is GameObjects.Leaves -> return
            is GameObjects.Rock -> return
        }
    }

    private fun onBrick(
        gameObj: GameObjects.Brick,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (placeOnField.slot.value) {
            is GameObjects.Empty -> changeField(gameObj, placeOnField, onTakePlace)
            else -> return
        }
    }

    private fun onBonus(
        gameObj: GameObjects.Bonus,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (placeOnField.slot.value) {
            is GameObjects.Brick -> changeField(gameObj, placeOnField, onTakePlace)
            else -> return
        }
    }

    private fun changeField(
        gameObj: GameObjects,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_HOVER_COLOR

        if (onTakePlace) {
            placeOnField.slot.value = gameObj
            placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR
            resetOnTakePlace(gameObj, placeOnField)
            soundController.pushCristal()
        }
    }

    private fun resetOnTakePlace(gameObj: GameObjects, placeOnField: PlaceOnField) {
        when (gameObj) {
            is GameObjects.Bonus -> resetOnBonus(gameObj, placeOnField)
            is GameObjects.Brick -> resetOnBrick(gameObj, placeOnField)
            is GameObjects.Empty -> return
            is GameObjects.Leaves -> return
            is GameObjects.Rock -> return
        }
    }

    private fun resetOnBonus(gameObj: GameObjects.Bonus, placeOnField: PlaceOnField) {
        gameObj.baseModel.alpha.value = gameConfig.INIT_ALPHA_BONUS
        placeOnField.slot.value = GameObjects.Empty(BaseModel(context))
    }

    private fun resetOnBrick(gameObj: GameObjects.Brick, placeOnField: PlaceOnField) {

        activeLevel?.let { level ->
            levelData.removeBricksList(gameObj)

            if (levelData.getBricksList().size <= level.lastBrickToAdd) {
                gameObjectBuilder.updateBricksList(level)
            }
            levelLogic.checkRound(gameObj, placeOnField)
        }
    }
}