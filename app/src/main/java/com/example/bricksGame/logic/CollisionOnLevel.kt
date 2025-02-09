package com.example.bricksGame.logic

import android.util.Log
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
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

    fun observeCenterObjects(gameObj: GameObjects) {

        when (gameObj) {
            is GameObjects.Brick -> {
                checkCollision(gameObj, gameObj.cords)
            }

            is GameObjects.Bonus -> {
                checkCollision(gameObj, gameObj.cords)
            }

            else -> return
        }
    }

    private fun checkCollision(gameObj: GameObjects, cords: Cords) {

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
                    onCollision(gameObj, placeOnField)
                } else {
                    outOfCollision(gameObj, placeOnField)
                }
            }
        }
    }

    private fun onCollision(gameObj: GameObjects, placeOnField: PlaceOnField) {

        placeOnField.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_HOVER_COLOR

//        if (placeOnField.hasOwnerId == gameConfig.NEGATIVE_BONUS_ROCK ||
//            placeOnField.hasOwnerId == gameConfig.NEGATIVE_BONUS_LEAVES
//        ) {
//            println()
//            return
//        }
//
//        if (brick.position == "Bonus") {
//
//            if (placeOnField.hasOwnerId != null) {
//                placeOnField.hasBonusOwnerId = brick.name
//                brick.hasBonusOwnerId = placeOnField
//                placeOnField.setBorderRed()
//                println("Bonus Collision")
//            }
//        } else {
//            if (placeOnField.hasOwnerId == null) {
//                placeOnField.hasOwnerId = brick.id
//                brick.keepSpace(placeOnField)
//                placeOnField.setBorderRed()
//            }
//        }
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

    fun outOfField() {
        placeListOnField.forEach {
            it.baseModel.activeBorderColor.value = gameConfig.BRICK_BORDER_COLOR
        }
    }
}




