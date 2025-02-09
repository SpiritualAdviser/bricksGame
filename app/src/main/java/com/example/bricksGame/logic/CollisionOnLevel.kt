package com.example.bricksGame.logic

import android.util.Log
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.Cords
import com.example.bricksGame.gameObjects.GameObjects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollisionOnLevel @Inject constructor(
    var gameConfig: GameConfig
) {
    init {
        Log.d("my", "CollisionOnLevel_init")
    }

    private var placeListOnField: MutableList<PlaceOnField> = mutableListOf()
    private var isRun = false

    fun setPlacesFieldOnCollision(placesOnField: MutableList<PlaceOnField>) {
        placeListOnField = placesOnField
    }

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun observeCenterObjects(gameObj: GameObjects, onTakePlace: Boolean = false) {

        when (gameObj) {
            is GameObjects.Brick -> {
                checkCollision(gameObj, gameObj.cords, onTakePlace)
            }

            is GameObjects.Bonus -> {
                checkCollision(gameObj, gameObj.cords, onTakePlace)
            }

            else -> return
        }
    }

    private fun checkCollision(gameObj: GameObjects, cords: Cords, onTakePlace: Boolean) {

        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

            val brickCenterX = cords.globalX + cords.globalWidth / 2
            val brickCenterY = cords.globalY + cords.globalHeight / 2

            placeListOnField.forEach { placeOnField ->

                xCollision =
                    brickCenterX < placeOnField.cords.globalX + placeOnField.cords.globalWidth &&
                            brickCenterX > placeOnField.cords.globalX

                yCollision =
                    brickCenterY < placeOnField.cords.globalY + placeOnField.cords.globalHeight &&
                            brickCenterY > placeOnField.cords.globalY

                if (xCollision && yCollision) {
                    onCollision(gameObj, placeOnField, onTakePlace)
                } else {
                    outOfCollision(gameObj, placeOnField)
                }
            }
        }
    }

    private fun onCollision(
        gameObj: GameObjects,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (gameObj) {
            is GameObjects.Bonus -> onBonus(gameObj, placeOnField, onTakePlace)
            is GameObjects.Brick -> onBrick(gameObj, placeOnField, onTakePlace)
            is GameObjects.Empty -> return
            is GameObjects.Leaves -> return
            is GameObjects.Rock -> return
        }
    }

    private fun onBrick(
        gameObj: GameObjects.Brick,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (placeOnField.slot.value) {
            is GameObjects.Empty -> changeField(gameObj, placeOnField, onTakePlace)
            else -> return
        }
    }

    private fun onBonus(
        gameObj: GameObjects.Bonus,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        when (placeOnField.slot.value) {
            is GameObjects.Brick -> changeField(gameObj, placeOnField, onTakePlace)
            else -> return
        }
    }

    private fun changeField(
        gameObj: GameObjects,
        placeOnField: PlaceOnField,
        onTakePlace: Boolean
    ) {
        placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_HOVER_COLOR

        if (onTakePlace) {
            placeOnField.slot.value = gameObj
            println()
            placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR
        }
    }

    private fun outOfCollision(gameObj: GameObjects, placeOnField: PlaceOnField) {

        placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR

//        if (brick.position == "Bonus") {
//
//            if (brick.hasBonusOwnerId != null && brick.name == placeOnField.hasBonusOwnerId) {
//                brick.hasBonusOwnerId = null
//                placeOnField.hasBonusOwnerId = null
//                placeOnField.setBorderBlack()
//                println("Bonus utOfCollision")
//            }
//
//        }
//
//        if (placeOnField.hasOwnerId != null && placeOnField.hasOwnerId == brick.id) {
//            println(placeOnField.position.toString())
//
//            if (placeOnField.position.toString() == brick.placeOnFeeldOnCollision?.position.toString()) {
//                brick.freeSpace()
//            }
//            placeOnField.hasOwnerId = null
//            placeOnField.setBorderBlack()
//        }
    }

    fun takeAPlaces(gameObj: GameObjects) {
        observeCenterObjects(gameObj, onTakePlace = true)
    }

    fun outOfField() {
        placeListOnField.forEach {
            it.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR
        }
    }
}




