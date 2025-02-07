package com.example.bricksGame.logic

import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.GameObjects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollisionBricksOnLevel @Inject constructor(
    var gameConfig: GameConfig
) {

    private var fieldBricksList: MutableList<PlaceOnField> = mutableListOf()
    private var isRun = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun resetData() {
        this.fieldBricksList.clear()
    }

    fun addToCollision(placeOnField: PlaceOnField? = null) {
        if (placeOnField != null) {
            fieldBricksList.add(placeOnField)
        }
    }

    fun observeCenterObjects(brick: GameObjects.Brick) {
        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

//            val brickX = brick.globalX + brick.globalWidth / 2
//            val brickY = brick.globalY + brick.globalHeight / 2

//            fieldBricksList.forEachIndexed { indexFieldBrick, fieldBrick ->
//
//                xCollision = brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
//                        brickX > fieldBrick.globalX
//
//                yCollision = brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
//                        brickY > fieldBrick.globalY
//
//                if (xCollision && yCollision) {
//                    onCollision(brick, fieldBrick)
//                } else {
//                    outOfCollision(brick, fieldBrick)
//                }
//            }
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



