package com.example.bricksGame.components.levelGame.models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ScreenSize
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class FieldViewModel @Inject constructor(
    private var levelData: LevelData,
    val gameConfig: GameConfig,
    val screenSize: ScreenSize,
    @ApplicationContext val context: Context
) : ViewModel() {

    init {
        Log.d("my", "FieldViewModel_init")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d("my", "FieldViewModel_onCleared")
    }

    private var activeLevel: Level? = levelData.getActiveLevel()

    var placesOnField: MutableList<PlaceOnField> = levelData.getPlacesOnFields()

    var placeBorderColor: MutableState<Color> = mutableStateOf(gameConfig.BRICK_BORDER_COLOR)

    var placeCorner = gameConfig.BRICK_ROUNDED_CORNER
    var fieldBgColor = gameConfig.FIELD_BG_COLOR
    var placeBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var fieldRows = activeLevel?.fieldRow ?: 0
    var fieldColumns = activeLevel?.fieldColumn ?: 0
    var placeBorderSize = gameConfig.BRICK_BORDER_SIZE.dp

    var fieldWidth = levelData.fieldWidth
    var fieldHeight = levelData.fieldHeight
    var placeSizeOnField = levelData.placeSizeOnField

    fun getBitmapPainter(slot: GameObjects): BitmapPainter {
        return when (slot) {
            is GameObjects.Brick -> slot.baseModel.getBitmapPainter()
            is GameObjects.Bonus -> slot.baseModel.getBitmapPainter()
            is GameObjects.Leaves -> slot.baseModel.getBitmapPainter()
            is GameObjects.Rock -> slot.baseModel.getBitmapPainter()
            is GameObjects.Empty -> slot.baseModel.getBitmapPainter()
        }
    }

    fun onClick(placeOnField: PlaceOnField) {
//        val baseModel = BaseModel(context)
//        baseModel.assetImage = R.drawable.blue_brick
//        val cords: Cords = Cords()
//        val animation: Animation = Animation()
//
//        placeOnField.slot.value = GameObjects.Brick(baseModel, cords, animation)
    }
}
