package com.example.bricksGame.logic

import com.example.bricksGame.components.levelGame.models.Brick
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.config.GameConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollisionBricksOnLevel @Inject constructor(
    var gameConfig: GameConfig
) {

    private var fieldBricksList: MutableList<FieldBrick> = mutableListOf()
    private var isRun = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun resetData() {
        this.fieldBricksList.clear()
    }

    fun addToCollision(fieldBrick: FieldBrick? = null) {
        if (fieldBrick != null) {
            fieldBricksList.add(fieldBrick)
        }
    }

    fun observeCenterObjects(brick: Brick) {
        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

            val brickX = brick.globalX + brick.globalWidth / 2
            val brickY = brick.globalY + brick.globalHeight / 2

            fieldBricksList.forEachIndexed { indexFieldBrick, fieldBrick ->

                xCollision = brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
                        brickX > fieldBrick.globalX

                yCollision = brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
                        brickY > fieldBrick.globalY

                if (xCollision && yCollision) {
                    onCollision(brick, fieldBrick)
                } else {
                    outOfCollision(brick, fieldBrick)
                }
            }
        }
    }

    private fun onCollision(brick: Brick, fieldBrick: FieldBrick) {

        if (fieldBrick.hasOwnerId == gameConfig.NEGATIVE_BONUS_ROCK ||
            fieldBrick.hasOwnerId == gameConfig.NEGATIVE_BONUS_LEAVES
        ) {
            println()
            return
        }

        if (brick.position == "Bonus") {

            if (fieldBrick.hasOwnerId != null) {
                fieldBrick.hasBonusOwnerId = brick.name
                brick.hasBonusOwnerId = fieldBrick
                fieldBrick.setBorderRed()
                println("Bonus Collision")
            }
        } else {
            if (fieldBrick.hasOwnerId == null) {
                fieldBrick.hasOwnerId = brick.id
                brick.keepSpace(fieldBrick)
                fieldBrick.setBorderRed()
            }
        }
    }

    private fun outOfCollision(brick: Brick, fieldBrick: FieldBrick) {

        if (brick.position == "Bonus") {

            if (brick.hasBonusOwnerId != null && brick.name == fieldBrick.hasBonusOwnerId) {
                brick.hasBonusOwnerId = null
                fieldBrick.hasBonusOwnerId = null
                fieldBrick.setBorderBlack()
                println("Bonus utOfCollision")
            }

        }

        if (fieldBrick.hasOwnerId != null && fieldBrick.hasOwnerId == brick.id) {
            println(fieldBrick.position.toString())

            if (fieldBrick.position.toString() == brick.fieldBrickOnCollision?.position.toString()) {
                brick.freeSpace()
            }
            fieldBrick.hasOwnerId = null
            fieldBrick.setBorderBlack()
        }
    }
}



