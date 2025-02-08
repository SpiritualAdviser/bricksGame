package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
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
    var brickSize = levelData.placeSizeOnField

    private var fieldRows = levelData.getActiveLevel()?.fieldRow ?: gameConfig.ROWS
    var offsetBricksBlock = (brickSize.value * (fieldRows + 2)) / 2

    private var bricksList: MutableList<GameObjects.Brick> = levelData.getBricksList()

    val bricks
        get() = bricksList

    fun dragging(brick: GameObjects.Brick, dragAmount: Offset) {
        brick.baseModel.zIndex.value = 999F
        viewModelScope.launch {
            brick.cords.x.intValue += dragAmount.x.toInt()
            brick.cords.y.intValue += dragAmount.y.toInt()
        }
    }

    private fun goBack(brick: GameObjects.Brick) {
            brick.cords.x.intValue = 0
            brick.cords.y.intValue = 0
    }

    fun observeCenterObjects(brick: GameObjects.Brick) {
//        collisionBricksOnLevel.observeCenterObjects(brick)
    }

    fun takeAPlaces(brick: GameObjects.Brick) {
//            brick.takeAPlaces()
    }

    fun onDragEnd(brick: GameObjects.Brick) {
        goBack(brick)
        brick.baseModel.zIndex.value = 0F
//       takeAPlaces(brick)
    }
}








