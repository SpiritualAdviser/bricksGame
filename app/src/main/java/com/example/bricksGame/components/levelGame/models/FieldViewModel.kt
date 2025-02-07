package com.example.bricksGame.components.levelGame.models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.BrickType
import com.example.bricksGame.gameData.PlaceOnField
import com.example.bricksGame.gameData.LevelData
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

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "FieldViewModel_onCleared")
    }

    fun onClick(placeOnField: PlaceOnField) {

        placeOnField.slot.value=BrickType.Brick(context)

    }
}
