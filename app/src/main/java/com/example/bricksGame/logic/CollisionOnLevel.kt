package com.example.bricksGame.logic

import android.util.Log
import com.example.bricksGame.gameObjects.Cords
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollisionOnLevel @Inject constructor(
    private val roundLogic: RoundLogic
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
        roundLogic.onCollision(gameObj, placeOnField, onTakePlace)
    }

    private fun outOfCollision(gameObj: GameObjects, placeOnField: PlaceOnField) {
        roundLogic.outOfCollision(gameObj, placeOnField)
    }

    fun takeAPlaces(gameObj: GameObjects) {
        observeCenterObjects(gameObj, onTakePlace = true)
    }
}




