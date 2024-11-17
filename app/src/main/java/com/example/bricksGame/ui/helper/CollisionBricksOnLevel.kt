package com.example.bricksGame.ui.helper

import androidx.compose.ui.util.fastForEachIndexed
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick

object CollisionBricksOnLevel {

    private var fieldBricksList: MutableList<FieldBrick> = mutableListOf()
    private var bricksList: MutableList<Brick> = mutableListOf()

    private var isRun = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun addToCollision(fieldBrick: FieldBrick? = null, brick: Brick? = null) {
        if (fieldBrick != null) {
            fieldBricksList.add(fieldBrick)
        }
        if (brick != null) {
            bricksList.add(brick)
        }
    }


    fun observeCenterObjects(brick: Brick) {
        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

            val brickX = brick.globalX + brick.globalWidth / 2
            val brickY = brick.globalY + brick.globalHeight / 2

            fieldBricksList.asReversed().forEachIndexed() { indexFieldBrick, fieldBrick ->

                xCollision = brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
                        brickX > fieldBrick.globalX

                yCollision = brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
                        brickY > fieldBrick.globalY

                if (xCollision && yCollision) {

                    if (fieldBrick.hasOwnerId == null) {
                        fieldBrick.hasOwnerId = brick.id
                        fieldBrick.onTargetCollision()
                        brick.setTarget(fieldBrick)
                    } else {
                        if (fieldBrick.hasOwnerId == brick.id) {
                            fieldBrick.onTargetCollision()
                            if (brick.collisionTarget == null) {
                                brick.setTarget(fieldBrick)
                            }
                        }
                    }

                } else {
                    if (fieldBrick.hasOwnerId == brick.id) {
                        fieldBrick.onOutCollision()
                        brick.onOutCollision()
                    }
                }
            }
        }
    }
}



