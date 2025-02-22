package com.example.bricksGame.components.levelGame.models

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.levelGame.controller.FieldController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ScreenSize
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize,
    private var fieldController: FieldController,
    @ApplicationContext val context: Context,
) : ViewModel() {

    init {
        Log.d("my", "FieldViewModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "FieldViewModel_onCleared")
    }

    var freeGame = levelData.freeGame

    private var activeLevel: Level? = levelData.getActiveLevel()

    var placesOnField: MutableList<PlaceOnField> = levelData.getPlacesOnFields()

//    var placeBorderColor: MutableState<Color> = mutableStateOf(gameConfig.BRICK_BORDER_COLOR)

    var placeCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var placeBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = activeLevel?.fieldRow ?: 0
    var fieldColumns = activeLevel?.fieldColumn ?: 0
    var placeBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidth = levelData.fieldWidth
    var fieldHeight = levelData.fieldHeight
    var placeSizeOnField = levelData.placeSizeOnField

    var levelTarget = levelData.levelTarget
    var levelWinLine = levelData.levelWinLine
    var levelStep = levelData.levelStep

    fun getBitmapPainter(slot: GameObjects): BitmapPainter {
        return when (slot) {
            is GameObjects.Brick -> slot.baseModel.getBitmapPainter()
            is GameObjects.Bonus -> slot.baseModel.getBitmapPainter()
            is GameObjects.Leaves -> slot.baseModel.getBitmapPainter()
            is GameObjects.Rock -> slot.baseModel.getBitmapPainter()
            is GameObjects.Empty -> slot.baseModel.getBitmapPainter()
        }
    }

    fun setGloballyPosition(placeOnField: PlaceOnField, coordinates: LayoutCoordinates) {
        placeOnField.cords.globalWidth = coordinates.size.width
        placeOnField.cords.globalHeight = coordinates.size.height
        placeOnField.cords.globalX = coordinates.positionInWindow().x
        placeOnField.cords.globalY = coordinates.positionInWindow().y
    }

    fun onClick(placeOnField: PlaceOnField) {
//        popupsController.onWinLine(true, placeOnField)
//        CoroutineScope(Dispatchers.Main).launch {
//            popupsController.showPopupOnFinishGame(true)
//            delay(1800)
//            popupsController.closePopupOnFinishGame()
//        }
//        when (val slot=placeOnField.slot.value) {
//
//            is GameObjects.Leaves -> slot.baseModel.startAnimation("destroy")
//            else -> return
//
//        }
//        runAnimationScale(placeOnField)
    }

    fun navigateToHome() {
        fieldController.goToHome()
    }

    fun navigateToMap() {
        fieldController.goToMap()
    }

    private fun runAnimationScale(placeOnField: PlaceOnField) {

        viewModelScope.launch(Dispatchers.Main) {
            placeOnField.animation.scaleAnimation.snapTo(0.8F)
            placeOnField.animation.wasAnimated.value = !placeOnField.animation.wasAnimated.value
        }
    }
}
