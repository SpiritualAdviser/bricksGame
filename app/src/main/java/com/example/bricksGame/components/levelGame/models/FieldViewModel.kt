package com.example.bricksGame.components.levelGame.models

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
    var zIndex = levelData.zIndex

    var brickSizePortrait = levelData.brickSizePortrait
    var brickSizeLandscape = levelData.brickSizeLandscape
    var fieldMAxWidthSize = levelData.fieldMAxWidthSize

    fun changeZIndex(index: Float) {
        fieldController.changeZIndex(index)
    }
}
