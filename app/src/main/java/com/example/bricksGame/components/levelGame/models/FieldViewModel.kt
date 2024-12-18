package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.components.levelGame.logic.CollisionBricksOnLevel
import com.example.bricksGame.screenSize

object FieldViewModel : ViewModel() {

    const val EMPTY_ID = "Color.Transparent"
    var brickOnField = createBricksList()
    var numberOfCloseFieldBrickOnLine = 0
    var zIndex = mutableFloatStateOf(0F)

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp
    var fieldMAxWidthSize = 0.dp

    private fun setFieldMAxWidthSize() {
        fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
            screenSize.screenHeightDp - (GameConfig.PADDING_FIELD.dp * 2)
        } else {
            screenSize.screenWidthDp - (GameConfig.PADDING_FIELD.dp * 2)
        }
    }

    fun onOptionChange() {
        setFieldMAxWidthSize()
        brickSizePortrait = fieldMAxWidthSize / GameConfig.ROWS
        brickSizeLandscape = fieldMAxWidthSize / GameConfig.COLUMNS

        if (brickSizePortrait > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizePortrait = GameConfig.MAX_BRICKS_SIZE.dp
        }

        if (brickSizeLandscape > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizeLandscape = GameConfig.MAX_BRICKS_SIZE.dp
        }
    }

    fun resetData() {
        this.brickOnField.clear()
        CollisionBricksOnLevel.resetData()
        this.brickOnField = createBricksList()
    }

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = GameConfig.ROWS * GameConfig.COLUMNS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % GameConfig.ROWS == 0) {
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
        )
    }

    fun addToCollision() {
        brickOnField.forEach() {
            CollisionBricksOnLevel.addToCollision(fieldBrick = it)
        }
        runCollision()
    }

    fun changeZIndex(index: Float) {
        zIndex.floatValue = index
    }

    private fun runCollision() {
        CollisionBricksOnLevel.runCollision(true)
    }

    fun setBricksOnField(brick: Brick) {
        val currentFieldBrick = brick.fieldBrickOnCollision
        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
        currentFieldBrick?.id = brick.assetImage.toString()
    }

    fun setImageOnField(fieldBrick: FieldBrick) {

        when (fieldBrick.hasOwnerId) {
            GameConfig.NEGATIVE_BONUS_LIVES -> {
                fieldBrick.assetImage.value = GameConfig.imagesNegativeBonuses[0]
            }

            GameConfig.NEGATIVE_BONUS_ROCK -> {
                fieldBrick.assetImage.value = GameConfig.imagesNegativeBonuses[1]
            }

            else -> fieldBrick.assetImage.value = R.drawable.bgfielbrickempty
        }
    }

}
