package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick

object CollisionBricksOnLevel {

    private var fieldBricksList: MutableList<FieldBrick> = mutableListOf()
    private var bricksList: MutableList<Brick> = mutableListOf()

    private var isRun = false
    private var onTargetFieldBricks: MutableList<FieldBrick> = mutableListOf()

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

    fun observeCenterObjects() {
        if (isRun) {

            var xCollision: Boolean
            var yCollision: Boolean

            bricksList.forEach { brick ->
                val brickX = brick.globalX + brick.globalWidth / 2
                val brickY = brick.globalY + brick.globalHeight / 2

                fieldBricksList.forEach { fieldBrick ->

                    xCollision = brickX < fieldBrick.globalX + fieldBrick.globalWidth &&
                            brickX > fieldBrick.globalX

                    yCollision = brickY < fieldBrick.globalY + fieldBrick.globalHeight &&
                            brickY > fieldBrick.globalY

                    if (xCollision && yCollision) {
                        println("collision")
                        if (!fieldBrick.onCollision) {
                            fieldBrick.onCollision = true
                            onTargetFieldBricks.add(fieldBrick)
                            fieldBrick.onTargetCollision()
                        }
                    } else {

                        onTargetFieldBricks.forEachIndexed { index, item ->
                            if (item.onCollision) {
                                println("outCl")
                                item.onCollision = false
                                item.onOutCollision()
                                onTargetFieldBricks.removeAt(index)
                            }
                        }
                    }
//                    println(onTargetFieldBricks.size)
                }
            }
        }
    }
}


