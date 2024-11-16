package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.models.Brick
import com.example.bricksGame.components.levelGame.models.FieldBrick
import kotlinx.coroutines.CoroutineScope

object CollisionBricksOnLevel {

    private var fieldBricksList: MutableList<FieldBrick> = mutableListOf()
    private var isRun = false
    private var colision = false

    fun runCollision(state: Boolean) {
        isRun = state
    }

    fun addToCollision(fieldBrick: FieldBrick) {
        fieldBricksList.add(fieldBrick)
    }

    fun observeObjects(brick: Brick) {

        if (isRun) {
            var xCollision = false
            var yCollision = false

            fieldBricksList.forEach { fieldBrick ->
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
                    println("colision")
                }
            }
        }
    }
}

