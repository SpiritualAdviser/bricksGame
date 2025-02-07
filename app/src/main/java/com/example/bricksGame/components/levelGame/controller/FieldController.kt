package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.NegativeBonus
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.logic.CollisionBricksOnLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FieldController @Inject constructor(
    private val
    gameConfig: GameConfig,
    private val collisionBricksOnLevel: CollisionBricksOnLevel,
//    var soundController: SoundController,
    @ApplicationContext val context: Context
) {

    init {
        Log.d("my", "FieldController_init")
    }

    fun createPlacesOnFieldList(level: Level): MutableList<PlaceOnField> {
        val allPlacesOnField = level.fieldRow * level.fieldColumn
        val bricksList: MutableList<PlaceOnField> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allPlacesOnField) {

            if (i != 0 && i % level.fieldRow == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createBrick(positionColumn, positionRow)
            bricksList.add(fieldBrick)
            ++positionRow
        }
        println(bricksList.toString())
        return bricksList
    }

    private fun createBrick(positionColumn: Int, positionRow: Int): PlaceOnField {
        return PlaceOnField(
            position = Pair(positionColumn, positionRow),
            slot = mutableStateOf(GameObjects.Empty(BaseModel(context))),
        )
    }

//    fun setBricksOnField(brick: Brick) {
//        val currentFieldBrick = brick.fieldBrickOnCollision
//        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
//        currentFieldBrick?.id = brick.assetImage.toString()
//    }


    fun resetData() {
//        levelData.brickOnFields.clear()
//        collisionBricksOnLevel.resetData()
    }


    fun addToCollision() {
//        levelData.brickOnFields.forEach() {
//            collisionBricksOnLevel.addToCollision(fieldBrick = it)
//        }
//        runCollision()
    }

    fun changeZIndex(index: Float) {
//        levelData.zIndex.floatValue = index
    }

    private fun runCollision() {
//        collisionBricksOnLevel.runCollision(true)
    }

    fun checkNeedChangeAsset(placeOnField: PlaceOnField) {

//        when (fieldBrick.hasOwnerId) {
//
//            gameConfig.NEGATIVE_BONUS_LEAVES -> {
//
//                gameConfig.negativeBonuses.find { it.id == gameConfig.NEGATIVE_BONUS_LEAVES }
//                    ?.let { bonus ->
//                        setAssetsOnNegativeBonus(fieldBrick, bonus)
//                    }
//            }
//
//            gameConfig.NEGATIVE_BONUS_ROCK -> {
//
//                gameConfig.negativeBonuses.find { it.id == gameConfig.NEGATIVE_BONUS_ROCK }
//                    ?.let { bonus ->
//                        setAssetsOnNegativeBonus(fieldBrick, bonus)
//                    }
//            }
//
//            else -> {
//                fieldBrick.assetImage.value = R.drawable.bgfielbrickempty
//                fieldBrick.onDestroy = true
//                fieldBrick.resetFieldBrick()
//            }
//        }
    }

    private fun setAssetsOnNegativeBonus(placeOnField: PlaceOnField, bonus: NegativeBonus) {

//        if (fieldBrick.hasSprite.value) {
//            var animationName = bonus.animationFullLife
//
//            when (fieldBrick.life) {
//
//                1 -> {
//                    animationName = bonus.animationOnDamage
//                }
//
//                0 -> {
//                    fieldBrick.onDestroy = true
//                    animationName = bonus.animationOnDestroy
//                }
//            }
//
//            animationName?.let {
//                fieldBrick.startAnimation(animationName)
//                playSound(animationName, bonus)
//            }
//
//        } else {
//            fieldBrick.assetImage.value =
//                if (fieldBrick.life == 1) bonus.imageOnDamage else bonus.imageFullLife
//
//            if (fieldBrick.life <= 0) {
//                fieldBrick.onDestroy = true
//                fieldBrick.resetFieldBrick()
//            }
//        }
    }

    private fun playSound(animationName: String, bonus: NegativeBonus) {

//        if (bonus.id == gameConfig.NEGATIVE_BONUS_ROCK) {
//            when (animationName) {
//                "crash" -> {
//                    soundController.stoneCrack()
//                }
//
//                "destroy" -> {
//                    soundController.stoneDestroy()
//                }
//            }
//        }
//
//        if (bonus.id == gameConfig.NEGATIVE_BONUS_LEAVES) {
//            when (animationName) {
//                "destroy" -> {
//                    soundController.rustleOfLeaves()
//                }
//            }
//        }
    }
}