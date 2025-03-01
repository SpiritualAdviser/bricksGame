package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.levelGame.controller.BricksController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BricksViewModel @Inject constructor(
    val gameConfig: GameConfig,
    levelData: LevelData,
    private var bricksController: BricksController,
) : ViewModel() {

    init {
        Log.d("my", "BricksViewModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "BricksViewModel_onCleared")
    }

    var brickBgColor: Color = gameConfig.BRICK_BG_COLOR
    var brickCorner: Int = gameConfig.BRICK_ROUNDED_CORNER
    var brickSize = levelData.placeSizeOnField

    private var fieldRows = levelData.getActiveLevel()?.fieldRow ?: gameConfig.ROWS
    var offsetBricksBlock = (brickSize.value * (fieldRows + 2)) / 2

    private var bricksList: MutableList<GameObjects.Brick> = levelData.getBricksList()

    var canRunTranslation = mutableStateOf(false)

    val bricks
        get() = bricksList

    fun dragging(brick: GameObjects, dragAmount: Offset) {
        when (brick) {
            is GameObjects.Brick
                -> {
                brick.baseModel.zIndex.value = 999F

                viewModelScope.launch {
                    brick.cords.x.intValue += dragAmount.x.toInt()
                    brick.cords.y.intValue += dragAmount.y.toInt()
                    observeCenterObjects(brick)
                }
            }

            else -> return
        }
    }

    private fun goBack(brick: GameObjects.Brick) {
        brick.cords.x.intValue = 0
        brick.cords.y.intValue = 0
    }

    private fun observeCenterObjects(brick: GameObjects) {
        viewModelScope.launch {
            bricksController.observeCenterObjects(brick)
        }
    }

    fun takeAPlaces(brick: GameObjects.Brick) {
//            brick.takeAPlaces()
    }

    fun onDragEnd(brick: GameObjects.Brick) {
        goBack(brick)
        brick.baseModel.zIndex.value = 0F
        bricksController.onDragEnd(brick)

//        viewModelScope.launch {
//            delay(30)
//            bricksController.onDragEnd()
//        }
    }

    fun onDragCancel(brick: GameObjects.Brick) {
//        goBack(brick)
//        brick.baseModel.zIndex.value = 0F
//       takeAPlaces(brick)
    }

    fun setGloballyPosition(brick: GameObjects.Brick, coordinates: LayoutCoordinates) {
        brick.cords.globalWidth = coordinates.size.width
        brick.cords.globalHeight = coordinates.size.height
        brick.cords.globalX = coordinates.positionInWindow().x
        brick.cords.globalY = coordinates.positionInWindow().y
    }

    fun runAnimationTranslation(brick: GameObjects.Brick, index: Int) {
        brick.animation.delayTranslation = 250 * (index + 1)

        viewModelScope.launch(Dispatchers.Main) {

            delay((brick.animation.delayTranslation + 200).toLong())
            brick.animation.wasAnimated.value = true
        }
    }
}








