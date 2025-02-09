package com.example.bricksGame.components.levelGame.models

import android.util.Log
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.components.levelGame.controller.BonusController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor(
    val gameConfig: GameConfig,
    private var bonusController: BonusController,
    private var levelData: LevelData,
) : ViewModel() {

    init {
        Log.d("my", "BonusViewModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "BonusViewModel_onCleared")
    }

    private var bonusList = levelData.getBonusList()

    var bonusCorner: Int = gameConfig.BRICK_ROUNDED_CORNER
    var bonusSize = levelData.placeSizeOnField
    var bonusBgColor = gameConfig.BRICK_BG_FIELD_COLOR
    var bonusBorderSize = gameConfig.BRICK_BORDER_SIZE.dp
    var zIndexBonusBlock: MutableFloatState = mutableFloatStateOf(0f)

    private var fieldRows = levelData.getActiveLevel()?.fieldRow ?: gameConfig.ROWS
    var offsetBonusBlock = -(bonusSize.value * (fieldRows + 2)) / 2

    val bonuses
        get() = bonusList


    fun dragging(bonus: GameObjects, dragAmount: Offset) {
        when (bonus) {
            is GameObjects.Bonus
                -> {
                bonus.baseModel.zIndex.value = 999F
                zIndexBonusBlock.floatValue = 999F
                viewModelScope.launch {
                    bonus.cords.x.intValue += dragAmount.x.toInt()
                    bonus.cords.y.intValue += dragAmount.y.toInt()
                    observeCenterObjects(bonus)
                }
            }

            else -> return
        }
    }

    private fun goBack(bonus: GameObjects.Bonus) {
        bonus.cords.x.intValue = 0
        bonus.cords.y.intValue = 0
    }

    private fun observeCenterObjects(bonus: GameObjects.Bonus) {
        bonusController.observeCenterObjects(bonus)
    }

    fun takeAPlaces(bonus: GameObjects.Bonus) {
//            brick.takeAPlaces()
    }

    fun onDragEnd(bonus: GameObjects.Bonus) {
        goBack(bonus)
        bonus.baseModel.zIndex.value = 0F
        zIndexBonusBlock.floatValue = 0F
        bonusController.onDragEnd(bonus)

    }

    fun setGloballyPosition(bonus: GameObjects.Bonus, coordinates: LayoutCoordinates) {
        bonus.cords.globalWidth = coordinates.size.width
        bonus.cords.globalHeight = coordinates.size.height
        bonus.cords.globalX = coordinates.positionInWindow().x
        bonus.cords.globalY = coordinates.positionInWindow().y
    }

}


