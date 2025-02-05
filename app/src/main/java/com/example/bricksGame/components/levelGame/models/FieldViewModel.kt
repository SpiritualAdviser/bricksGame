package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.logic.controller.FieldController
import com.example.bricksGame.config.GameConfig
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

    var brickOnField: MutableList<FieldBrick> = levelData.brickOnFields
    var zIndex = mutableFloatStateOf(0F)

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp
    var fieldMAxWidthSize = 0.dp

    var brickCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var brickBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = gameConfig.ROWS
    var fieldColumns = gameConfig.COLUMNS
    var brickBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidth = 0.dp
    var fieldHeight = 0.dp

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

        fieldWidth = brickSizePortrait * fieldRows
        fieldHeight = brickSizePortrait * fieldColumns + brickBorderSize
    }

    fun changeZIndex(index: Float) {
        zIndex.floatValue = index
    }
}
