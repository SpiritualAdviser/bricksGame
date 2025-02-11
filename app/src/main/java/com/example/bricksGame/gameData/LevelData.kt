package com.example.bricksGame.gameData

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ScreenSize
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelData @Inject constructor(
    var screenSize: ScreenSize,
    private var gameConfig: GameConfig
) {
    init {
        Log.d("my", "LevelData_init")
    }

    private var placesOnField: MutableList<PlaceOnField> = mutableListOf()
    private var bricksList: MutableList<GameObjects.Brick> = mutableListOf()
    private var bonusList: MutableList<GameObjects.Bonus> = mutableListOf()

    private var activeLevel: Level? = null

    var fieldWidth = mutableStateOf(0.dp)
    var fieldHeight = mutableStateOf(0.dp)
    var placeSizeOnField = mutableStateOf(0.dp)

    var levelTarget = mutableIntStateOf(0)
    var levelWinLine = mutableIntStateOf(0)
    var levelStep = mutableIntStateOf(0)

    fun getActiveLevel(): Level? {
        return activeLevel
    }

    fun getPlacesOnFields(): MutableList<PlaceOnField> {
        return placesOnField
    }

    fun getBonusList(): MutableList<GameObjects.Bonus> {
        return bonusList
    }

    fun getBricksList(): MutableList<GameObjects.Brick> {
        return bricksList
    }

    fun setPlacesOnField(placeOnFieldInner: MutableList<PlaceOnField>) {
        placesOnField = placeOnFieldInner
    }

    fun setActiveLevel(level: Level) {
        activeLevel = level
        levelTarget.intValue = level.numberOfScoreToWin
        levelWinLine.intValue = level.numberOfBricksToWin
        levelStep.intValue = level.levelMaxStep
        onOptionChange()
    }

    fun setBricksList(toMutableStateList: MutableList<GameObjects.Brick>) {
        bricksList = toMutableStateList
    }

    fun setBonusList(bonusesOnLevel: MutableList<GameObjects.Bonus>) {
        bonusList = bonusesOnLevel
    }

    fun addToBricksList(brick: GameObjects.Brick) {
        bricksList.add(brick)
    }

    fun removeBricksList(brick: GameObjects.Brick) {
        bricksList.remove(brick)
    }

    fun onOptionChange() {
        Log.d("my", "onOptionChange")
        activeLevel?.let {
            resizeFieldSize(it)
        }
    }

    private fun resizeFieldSize(level: Level) {
        var fieldMAxSize = 0.dp
        var placeSize = 0.dp
        var maxPlaceSize = gameConfig.MAX_BRICKS_SIZE

        if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
            fieldMAxSize = screenSize.screenHeightDp - (gameConfig.PADDING_FIELD.dp * 2)
            maxPlaceSize = screenSize.screenHeightDp / 7
            placeSize = fieldMAxSize / level.fieldColumn


        } else {
            fieldMAxSize = screenSize.screenWidthDp - (gameConfig.PADDING_FIELD.dp * 2)
            maxPlaceSize = screenSize.screenWidthDp / 7
            placeSize = fieldMAxSize / level.fieldRow
        }

        placeSizeOnField.value =
            if (placeSize < maxPlaceSize) placeSize else maxPlaceSize

        fieldWidth.value = placeSizeOnField.value * level.fieldRow + gameConfig.BRICK_BORDER_SIZE.dp
        fieldHeight.value =
            placeSizeOnField.value * level.fieldColumn + gameConfig.BRICK_BORDER_SIZE.dp

    }
}