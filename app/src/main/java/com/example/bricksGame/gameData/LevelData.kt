package com.example.bricksGame.gameData

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.helper.ScreenSize
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelData @Inject constructor(
    var screenSize: ScreenSize,
    private var gameConfig: GameConfig
) {
    lateinit var brickOnFields: MutableList<FieldBrick>
    lateinit var _bricksList: SnapshotStateList<Brick>
    lateinit var _bonusList: SnapshotStateList<Brick>

    init {
        Log.d("my", "LevelData_init")
    }

    private var activeLevel: Level? = null

    var fieldWidth = 0.dp
    var fieldHeight = 0.dp
    var brickSize = 0.dp

    fun getActiveLevel(): Level? {
        return activeLevel
    }

    fun setBrickOnField(brickOnFieldInner: MutableList<FieldBrick>) {
        brickOnFields = brickOnFieldInner
    }

    fun setActiveLevel(level: Level) {
        activeLevel = level
        onOptionChange()
    }

    fun setBricksList(toMutableStateList: SnapshotStateList<Brick>) {
        _bricksList = toMutableStateList
    }

    fun setBonusList(toMutableStateList: SnapshotStateList<Brick>) {
        _bonusList = toMutableStateList
    }

    fun onOptionChange() {
        Log.d("my", "onOptionChange")
        activeLevel?.let {
            resizeFieldSize(it)
        }
    }

    private fun resizeFieldSize(level: Level) {
        var fieldMAxSize = 0.dp

        if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
            fieldMAxSize = screenSize.screenHeightDp - (gameConfig.PADDING_FIELD.dp * 2)
            brickSize = fieldMAxSize / gameConfig.COLUMNS
        } else {
            fieldMAxSize = screenSize.screenWidthDp - (gameConfig.PADDING_FIELD.dp * 2)
            brickSize = fieldMAxSize / gameConfig.ROWS
        }

        fieldWidth = brickSize * level.fieldRow + gameConfig.BRICK_BORDER_SIZE.dp
        fieldHeight = brickSize * level.fieldColumn + gameConfig.BRICK_BORDER_SIZE.dp
        println()
    }
}