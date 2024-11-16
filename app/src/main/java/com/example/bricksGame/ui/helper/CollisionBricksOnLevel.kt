package com.example.bricksGame.ui.helper

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

    fun observeCenterObjects(brick: Brick) {

        var xCollision: Boolean
        var yCollision: Boolean
        var onTarget: FieldBrick? = null

        val brickX = brick.globalX + brick.globalWidth / 2
        val brickY = brick.globalY + brick.globalHeight / 2

        fieldBricksList.forEach { fieldBrick ->

            xCollision = brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
                    brickX > fieldBrick.globalX

            yCollision = brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
                    brickY > fieldBrick.globalY

            if (xCollision && yCollision) {
                if (!fieldBrick.onCollision) {
                    onTarget = fieldBrick
                }

            } else {
                if (fieldBrick.onCollision) {
                    fieldBrick.onOutCollision(brick)
                    fieldBrick.onCollision = false
                }
            }
        }

        if (onTarget != null) {
            if (!onTarget!!.onCollision) {
                onTarget!!.onCollision = true
                onTarget!!.onTargetCollision(brick)
            }
        }
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

                if (!xCollision && !yCollision) {

                    if (!fieldBrick.onCollision) {
                        return
                    }

                } else {
                    if (fieldBrick.onCollision) {
                        return
                    }
                }
            }
        }
    }
}


