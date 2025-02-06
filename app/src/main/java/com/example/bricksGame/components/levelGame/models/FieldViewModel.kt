package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.FieldBrick
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize,

) : ViewModel() {

    init {
        Log.d("my", "FieldViewModel_init")
    }

    private var activeLevel: Level? = levelData.getActiveLevel()

    var brickOnField: MutableList<FieldBrick> = levelData.getBrickOnFields()

    var brickCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var brickBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = activeLevel?.fieldRow ?: 0
    var fieldColumns = activeLevel?.fieldColumn ?: 0
    var brickBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidth = levelData.fieldWidth
    var fieldHeight = levelData.fieldHeight
    var brickSize = levelData.brickSize

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "FieldViewModel_onCleared")
    }
}
