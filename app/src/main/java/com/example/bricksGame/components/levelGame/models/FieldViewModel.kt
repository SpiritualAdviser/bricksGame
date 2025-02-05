package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.logic.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.FieldBrick
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.logic.CollisionBricksOnLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
//    private var fieldController: FieldController,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize
) : ViewModel() {
    private var activeLevel: Level? = levelData.getActiveLevel()

    var brickOnField: MutableList<FieldBrick> = levelData.brickOnFields
    var zIndex = mutableFloatStateOf(0F)

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp
    var fieldMAxWidthSize = 0.dp

    var brickCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var brickBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = activeLevel?.fieldRow ?: 0
    var fieldColumns = activeLevel?.fieldColumn ?: 0
    var brickBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidthPortrait = 0.dp
    var fieldHeightPortrait = 0.dp

    var fieldWidthLandscape = 0.dp
    var fieldHeightLandscape = 0.dp

    init {
        Log.d("my", "FieldViewModel_init")
        setFieldMAxWidthSize()
        setBrickSize()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "FieldViewModel_onCleared")
    }

    private fun setFieldMAxWidthSize() {
        fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
            screenSize.screenHeightDp - (gameConfig.PADDING_FIELD.dp * 2)
        } else {
            screenSize.screenWidthDp - (gameConfig.PADDING_FIELD.dp * 2)
        }
    }

    private fun setBrickSize() {
        brickSizePortrait = fieldMAxWidthSize / gameConfig.ROWS
        brickSizeLandscape = fieldMAxWidthSize / gameConfig.COLUMNS

        if (brickSizePortrait > gameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizePortrait = gameConfig.MAX_BRICKS_SIZE.dp
        }

        if (brickSizeLandscape > gameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizeLandscape = gameConfig.MAX_BRICKS_SIZE.dp
        }

        fieldWidthPortrait = brickSizePortrait * fieldRows
        fieldHeightPortrait = brickSizePortrait * fieldColumns

        fieldWidthLandscape = brickSizeLandscape * fieldRows
        fieldHeightLandscape = brickSizeLandscape * fieldColumns
    }

    fun changeZIndex(index: Float) {
        zIndex.floatValue = index
    }
}
