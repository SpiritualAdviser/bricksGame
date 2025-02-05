package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.logic.CollisionBricksOnLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
    private var fieldController: FieldController,
    val gameConfig: GameConfig,
    val collisionBricksOnLevel: CollisionBricksOnLevel,
) : ViewModel() {

    var brickOnField: MutableList<FieldBrick> = levelData.brickOnFields

    var brickSizePortrait = levelData.brickSizePortrait
//    var brickSizeLandscape = levelData.brickSizeLandscape
//    var fieldMAxWidthSize = levelData.fieldMAxWidthSize

    var brickCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var brickBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = gameConfig.ROWS
    var fieldColumns = gameConfig.COLUMNS
    var brickBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidth = brickSizePortrait * fieldRows
    var fieldHeight = brickSizePortrait * fieldColumns + brickBorderSize


//    var zIndex = levelData.zIndex


    fun changeZIndex(index: Float) {
        fieldController.changeZIndex(index)
    }
}
