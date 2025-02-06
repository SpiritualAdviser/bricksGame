package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.Brick
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.logic.CollisionBricksOnLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BricksViewModel @Inject constructor(
    val gameConfig: GameConfig,
    private val collisionBricksOnLevel: CollisionBricksOnLevel,
    levelData: LevelData,

    ) : ViewModel() {

    init {
        Log.d("my", "BricksViewModel_init")
    }

    var brickBgColor: Color = gameConfig.BRICK_BG_COLOR
    var brickCorner: Int = gameConfig.BRICK_ROUNDED_CORNER
    var brickSize = mutableStateOf(levelData.brickSize)

    private var fieldRows = levelData.getActiveLevel()?.fieldRow ?: gameConfig.ROWS
    var offsetBricksBlock = (brickSize.value * (fieldRows + 2)) / 2


    private var _bricksList = levelData.getBricksList()
    val bricks
        get() = _bricksList

    fun observeCenterObjects(brick: Brick) {
        collisionBricksOnLevel.observeCenterObjects(brick)
    }

    suspend fun takeAPlaces(brick: Brick) {
            brick.takeAPlaces()
    }
}








