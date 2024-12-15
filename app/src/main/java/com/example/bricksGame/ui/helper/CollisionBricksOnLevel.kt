package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.ui.GameConfig


object CollisionBricksOnLevel {

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

        if (fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_ROCK ||
            fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_LIVES) {
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

//        if (fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_ROCK || fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_LIVES) {
//            return
//        }

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



