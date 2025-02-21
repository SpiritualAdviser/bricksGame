package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.logic.GameObjectBuilder
import com.example.bricksGame.helper.SpriteAnimation
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FieldController @Inject constructor(
    @ApplicationContext val context: Context,
    private var gameConfig: GameConfig,
    private val gameObjectBuilder: GameObjectBuilder,
    private var buttonController: ButtonController,
    private var spriteAnimation: SpriteAnimation
) {

    init {
        Log.d("my", "FieldController_init")
    }

    fun createPlacesOnFieldList(level: Level): MutableList<PlaceOnField> {
        return gameObjectBuilder.createPlacesOnFieldList(level)
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

    fun setNegativeSlotOnSurvival(placesOnField: List<PlaceOnField>, level: Level) {

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
                baseModel.sprite = spriteAnimation.getSprite(slotOption.spriteName)
                baseModel.life = slotOption.life
                GameObjects.Rock(baseModel)
            }

            GameConfig.NegativeSlot.LEAVES -> {
                baseModel.sprite = spriteAnimation.getSprite(slotOption.spriteName)
                baseModel.life = slotOption.life
                GameObjects.Leaves(baseModel)
            }
        }
        return slot
    }

    fun goToHome() {
        buttonController.navigateToHome()
    }

    fun goToMap() {
        buttonController.navigateToMap()
    }
}