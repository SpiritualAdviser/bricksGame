package com.example.bricksGame.components.levelGame.models

import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
    private var fieldController: FieldController,
    val gameConfig: GameConfig,
) : ViewModel() {

    var brickOnField: MutableList<FieldBrick> = fieldController.createBricksList()
    var zIndex = levelData.zIndex

    var brickSizePortrait = levelData.brickSizePortrait
    var brickSizeLandscape = levelData.brickSizeLandscape
    var fieldMAxWidthSize = levelData.fieldMAxWidthSize

    fun changeZIndex(index: Float) {
        fieldController.changeZIndex(index)
    }
}
