package com.example.bricksGame.components.levelGame.models

/* import com.example.bricksGame.logic.CollisionBricksOnLevel */
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.NegativeBonus
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
    var soundController: SoundController,
) : ViewModel() {

    val EMPTY_ID = "Color.Transparent"
    var brickOnField:MutableList<FieldBrick> = createBricksList()
    var zIndex = mutableFloatStateOf(0F)

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp
    var fieldMAxWidthSize = 0.dp

    private fun setFieldMAxWidthSize() {
        fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
            screenSize.screenHeightDp - (gameConfig.PADDING_FIELD.dp * 2)
        } else {
            screenSize.screenWidthDp - (gameConfig.PADDING_FIELD.dp * 2)
        }
    }

    fun onOptionChange() {
        setFieldMAxWidthSize()
        brickSizePortrait = fieldMAxWidthSize / gameConfig.ROWS
        brickSizeLandscape = fieldMAxWidthSize / gameConfig.COLUMNS

        if (brickSizePortrait > gameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizePortrait = gameConfig.MAX_BRICKS_SIZE.dp
        }

        if (brickSizeLandscape > gameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizeLandscape = gameConfig.MAX_BRICKS_SIZE.dp
        }
    }

    fun resetData() {
        this.brickOnField.clear()
//        CollisionBricksOnLevel.resetData()
        this.brickOnField = createBricksList()
    }

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = gameConfig.ROWS * gameConfig.COLUMNS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % gameConfig.ROWS == 0) {
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
            gameConfig = gameConfig,
        )
    }

    fun addToCollision() {
        brickOnField.forEach() {
//            CollisionBricksOnLevel.addToCollision(fieldBrick = it)
        }
        runCollision()
    }

    fun changeZIndex(index: Float) {
        zIndex.floatValue = index
    }

    private fun runCollision() {
//        CollisionBricksOnLevel.runCollision(true)
    }

    fun setBricksOnField(brick: Brick) {
        val currentFieldBrick = brick.fieldBrickOnCollision
        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
        currentFieldBrick?.id = brick.assetImage.toString()
    }

    fun checkNeedChangeAsset(fieldBrick: FieldBrick) {

        when (fieldBrick.hasOwnerId) {

            gameConfig.NEGATIVE_BONUS_LEAVES -> {

                gameConfig.negativeBonuses.find { it.id == gameConfig.NEGATIVE_BONUS_LEAVES }
                    ?.let { bonus ->
                        setAssetsOnNegativeBonus(fieldBrick, bonus)
                    }
            }

            gameConfig.NEGATIVE_BONUS_ROCK -> {

                gameConfig.negativeBonuses.find { it.id == gameConfig.NEGATIVE_BONUS_ROCK }
                    ?.let { bonus ->
                        setAssetsOnNegativeBonus(fieldBrick, bonus)
                    }
            }

            else -> {
                fieldBrick.assetImage.value = R.drawable.bgfielbrickempty
                fieldBrick.onDestroy = true
                fieldBrick.resetFieldBrick()
            }
        }
    }

    private fun setAssetsOnNegativeBonus(fieldBrick: FieldBrick, bonus: NegativeBonus) {

        if (fieldBrick.hasSprite.value) {
            var animationName = bonus.animationFullLife

            when (fieldBrick.life) {

                1 -> {
                    animationName = bonus.animationOnDamage
                }

                0 -> {
                    fieldBrick.onDestroy = true
                    animationName = bonus.animationOnDestroy
                }
            }

            animationName?.let {
                fieldBrick.startAnimation(animationName)
                playSound(animationName, bonus)
            }

        } else {
            fieldBrick.assetImage.value =
                if (fieldBrick.life == 1) bonus.imageOnDamage else bonus.imageFullLife

            if (fieldBrick.life <= 0) {
                fieldBrick.onDestroy = true
                fieldBrick.resetFieldBrick()
            }
        }
    }

    private fun playSound(animationName: String, bonus: NegativeBonus) {

        if (bonus.id == gameConfig.NEGATIVE_BONUS_ROCK) {
            when (animationName) {
                "crash" -> {
                    soundController.stoneCrack()
                }

                "destroy" -> {
                    soundController.stoneDestroy()
                }
            }
        }

        if (bonus.id == gameConfig.NEGATIVE_BONUS_LEAVES) {
            when (animationName) {
                "destroy" -> {
                    soundController.rustleOfLeaves()
                }
            }
        }
    }
}
