package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.logic.controller.BricksController
import com.example.bricksGame.logic.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.CollisionBricksOnLevel
import com.example.bricksGame.logic.LevelLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class BricksViewModel @Inject constructor(
    val gameConfig: GameConfig,
    var soundController: SoundController,
    val collisionBricksOnLevel: CollisionBricksOnLevel,
    private var levelData: LevelData,

    ) : ViewModel() {

    private var _bricksList = levelData._bricksList
    val bricks
        get() = _bricksList

}








