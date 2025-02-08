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
    private var fieldCords = Cords()
    private var isRun = false

    fun setPlacesFieldOnCollision(placesOnField: MutableList<PlaceOnField>) {
        placeListOnField = placesOnField
    }

    fun setFieldSizeOnCollision(coordinates: LayoutCoordinates) {
        fieldCords.globalWidth = coordinates.size.width
        fieldCords.globalHeight = coordinates.size.height
        fieldCords.globalX = coordinates.positionInWindow().x
        fieldCords.globalY = coordinates.positionInWindow().y
    }

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun resetData() {
        this.placeListOnField.clear()
    }

    private fun checkInField(brick: GameObjects.Brick): Boolean {
        var inFieldX = false
        var inFieldY = false
        val brickCenterX = brick.cords.globalX + brick.cords.globalWidth / 2
        val brickCenterY = brick.cords.globalY + brick.cords.globalHeight / 2

        inFieldX =
            brickCenterX < fieldCords.globalX + fieldCords.globalWidth &&
                    brickCenterX > fieldCords.globalX

        inFieldY =
            brickCenterY < fieldCords.globalY + fieldCords.globalHeight &&
                    brickCenterY > fieldCords.globalY

        return inFieldX && inFieldY
    }

    fun observeCenterObjects(brick: GameObjects.Brick) {

        if (isRun && checkInField(brick)) {

            var xCollision: Boolean
            var yCollision: Boolean

            val brickCenterX = brick.cords.globalX + brick.cords.globalWidth / 2
            val brickCenterY = brick.cords.globalY + brick.cords.globalHeight / 2

            placeListOnField.forEach { placeOnField ->

                xCollision =
                    brickCenterX < placeOnField.cords.globalX + placeOnField.cords.globalWidth &&
                            brickCenterX > placeOnField.cords.globalX

                yCollision =
                    brickCenterY < placeOnField.cords.globalY + placeOnField.cords.globalHeight &&
                            brickCenterY > placeOnField.cords.globalY

                if (xCollision && yCollision) {
                    onCollision(brick, placeOnField)
                } else {
                    outOfCollision(brick, placeOnField)
                }
            }
        }
    }

    private fun onCollision(brick: GameObjects.Brick, placeOnField: PlaceOnField) {

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

    private fun outOfCollision(brick: GameObjects.Brick, placeOnField: PlaceOnField) {

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
}



