package com.example.bricksGame.logic.controller

import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.Brick
import com.example.bricksGame.gameData.FieldBrick
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.NegativeBonus
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.CollisionBricksOnLevel
import javax.inject.Inject

class FieldController @Inject constructor(
    private val
    gameConfig: GameConfig,
    private val collisionBricksOnLevel: CollisionBricksOnLevel,
//    var soundController: SoundController,
) {

//    private fun setFieldMAxWidthSize() {
//        levelData.fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
//            screenSize.screenHeightDp - (gameConfig.PADDING_FIELD.dp * 2)
//        } else {
//            screenSize.screenWidthDp - (gameConfig.PADDING_FIELD.dp * 2)
//        }
//    }
//
//    fun onOptionChange() {
//        setFieldMAxWidthSize()
//        levelData.brickSizePortrait = levelData.fieldMAxWidthSize / gameConfig.ROWS
//        levelData.brickSizeLandscape = levelData.fieldMAxWidthSize / gameConfig.COLUMNS
//
//        if (levelData.brickSizePortrait > gameConfig.MAX_BRICKS_SIZE.dp) {
//            levelData.brickSizePortrait = gameConfig.MAX_BRICKS_SIZE.dp
//        }
//
//        if (levelData.brickSizeLandscape > gameConfig.MAX_BRICKS_SIZE.dp) {
//            levelData.brickSizeLandscape = gameConfig.MAX_BRICKS_SIZE.dp
//        }
//
//        println()
//    }

    fun resetData() {
//        levelData.brickOnFields.clear()
//        collisionBricksOnLevel.resetData()
    }

    fun createBricksList(level: Level): MutableList<FieldBrick> {
        val allBrickOnField = level.fieldRow * level.fieldColumn
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

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

    private fun createBrick(positionColumn: Int, positionRow: Int): FieldBrick {
        return FieldBrick(
            position = Pair(positionColumn, positionRow),
            borderDefaultColor = gameConfig.BRICK_BORDER_COLOR,
            borderHooverColor = gameConfig.BRICK_BORDER_HOVER_COLOR,
        )
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

    fun setBricksOnField(brick: Brick) {
        val currentFieldBrick = brick.fieldBrickOnCollision
        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
        currentFieldBrick?.id = brick.assetImage.toString()
    }

    fun checkNeedChangeAsset(fieldBrick: FieldBrick) {

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

    private fun setAssetsOnNegativeBonus(fieldBrick: FieldBrick, bonus: NegativeBonus) {

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