package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.logic.CollisionOnLevel
import com.example.bricksGame.logic.GameObjectBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class BricksController @Inject constructor(
    @ApplicationContext val context: Context,
    private var collisionOnLevel: CollisionOnLevel,
    private val gameObjectBuilder: GameObjectBuilder
) {
    init {
        Log.d("my", "BricksController_init")
    }

    fun createBricksList(level: Level): MutableList<GameObjects.Brick> {
        return gameObjectBuilder.createBricksList(level)
    }

    fun observeCenterObjects(brick: GameObjects) {
        collisionOnLevel.observeCenterObjects(brick)
    }

    fun onDragEnd(brick: GameObjects.Brick) {
        collisionOnLevel.takeAPlaces(brick)
    }
}