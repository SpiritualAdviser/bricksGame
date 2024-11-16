package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.models.Brick
import com.example.bricksGame.components.levelGame.models.FieldBrick

object CollisionBricksOnLevel {

    private lateinit var bricksList: MutableList<Brick>
    private lateinit var fieldBricksList: MutableList<FieldBrick>
    private var isRun = false
    private var colision = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun addToCollision(brick: Brick? = null, fieldBrick: FieldBrick? = null) {

        if (brick != null) {
            bricksList.add(brick)
        }
        if (fieldBrick != null) {
            fieldBricksList.add(fieldBrick)
        }
    }

    fun observeObjects() {
        if (isRun) {
            var xCollision = false
            var yCollision = false
            bricksList.forEach { brick ->

                fieldBricksList.forEach { fieldBrick ->
                    if (brick.x + brick.width > fieldBrick.x &&
                        brick.x < fieldBrick.x + fieldBrick.width
                    ) {
                        xCollision = true
                    }
                    if (brick.y + brick.height > fieldBrick.y &&
                        brick.y < fieldBrick.y + fieldBrick.height
                    ) {
                        yCollision = true
                    }

                    colision = xCollision && yCollision
                    println(colision)
                }
            }
        }
    }
}

