package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.SpriteAnimation
import com.example.bricksGame.logic.CollisionOnLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FieldController @Inject constructor(
    private var gameConfig: GameConfig,
    @ApplicationContext val context: Context
) {

    init {
        Log.d("my", "FieldController_init")
    }

    fun createPlacesOnFieldList(level: Level): MutableList<PlaceOnField> {
        val allPlacesOnField = level.fieldRow * level.fieldColumn
        val bricksList: MutableList<PlaceOnField> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allPlacesOnField) {

            if (i != 0 && i % level.fieldRow == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createPlace(positionColumn, positionRow)
            bricksList.add(fieldBrick)
            ++positionRow
        }
        println(bricksList.toString())
        return bricksList
    }

    private fun createPlace(positionColumn: Int, positionRow: Int): PlaceOnField {
        return PlaceOnField(
            position = Pair(positionColumn, positionRow),
            slot = mutableStateOf(GameObjects.Empty(BaseModel(context))),
            baseModel = BaseModel(context)
        )
    }

    fun setNegativeSlotOnField(placesOnField: MutableList<PlaceOnField>, level: Level) {

        level.negativeBonuses.forEachIndexed { indexSlot, numberSlot ->
            val typeOfSlot = gameConfig.negativeBonuses[indexSlot]

            for (i in 0 until numberSlot) {
                val placeOnField = placesOnField.random()
                val slot: GameObjects = createSlot(typeOfSlot)
                placeOnField.slot.value = slot
            }
        }
    }

    private fun createSlot(typeOfSlot: GameConfig.NegativeSlot): GameObjects {
        val baseModel = BaseModel(context)
        val slotOption = typeOfSlot.option

        val slot: GameObjects = when (typeOfSlot) {
            GameConfig.NegativeSlot.ROCK -> {
                baseModel.sprite = SpriteAnimation.getSprite(slotOption.spriteName)
                GameObjects.Rock(baseModel)
            }

            GameConfig.NegativeSlot.LEAVES -> {
                baseModel.sprite = SpriteAnimation.getSprite(slotOption.spriteName)
                GameObjects.Leaves(baseModel)
            }
        }
        return slot
    }

    fun resetData() {
//        levelData.brickOnFields.clear()
//        collisionBricksOnLevel.resetData()
    }


    fun changeZIndex(index: Float) {
//        levelData.zIndex.floatValue = index
    }

}