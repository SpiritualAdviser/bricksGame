package com.example.bricksGame.ui.helper

import androidx.compose.ui.unit.Dp
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick

object CollisionBricksOnLevel {

    private var fieldBricksList: MutableList<FieldBrick> = mutableListOf()
    private var isRun = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun addToCollision(fieldBrick: FieldBrick) {
        fieldBricksList.add(fieldBrick)
    }

    fun observeObjects(brick: Brick) {

        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

            fieldBricksList.forEach { fieldBrick ->
                xCollision = false
                yCollision = false
                if (brick.globalX < fieldBrick.globalX + fieldBrick.globalWidth &&
                    brick.globalX + brick.globalWidth > fieldBrick.globalX
                ) {
                    xCollision = true
                }
                if (brick.globalY < fieldBrick.globalY + fieldBrick.globalHeight &&
                    brick.globalY + brick.globalHeight > fieldBrick.globalY
                ) {
                    yCollision = true
                }

                if (xCollision && yCollision) {

                    if (!fieldBrick.onCollision) {
                        fieldBrick.onCollision = true
                        println("colision")
                        fieldBrick.onTargetCollision(brick)
                    }

                } else {
                    if (fieldBrick.onCollision) {
                        fieldBrick.onCollision = false
                        println("Notcolision")
                        fieldBrick.onOutCollision(brick)
                    }
                }
            }
        }
    }

    fun observeCenterObjects(brick: Brick) {

        var xCollision: Boolean
        var yCollision: Boolean

        val brickX = brick.globalX + brick.globalWidth / 2
        val brickY = brick.globalY + brick.globalHeight / 2

        fieldBricksList.forEach { fieldBrick ->
            xCollision = false
            yCollision = false
            if (brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
                brickX > fieldBrick.globalX
            ) {
                xCollision = true
            }
            if (brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
                brickY > fieldBrick.globalY
            ) {
                yCollision = true
            }

            if (xCollision && yCollision) {

                if (!fieldBrick.onCollision) {
                    fieldBrick.onCollision = true
                    println("colision")
                    fieldBrick.onTargetCollision(brick)
                }

            } else {
                if (fieldBrick.onCollision) {
                    fieldBrick.onCollision = false
                    println("Notcolision")
                    fieldBrick.onOutCollision(brick)
                }
            }
        }
    }
}

