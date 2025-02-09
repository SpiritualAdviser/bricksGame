package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.logic.GameObjectBuilder
import com.example.bricksGame.logic.CollisionOnLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BonusController @Inject constructor(
    @ApplicationContext val context: Context,
    private var collisionOnLevel: CollisionOnLevel,
    private val gameObjectBuilder: GameObjectBuilder
) {

    init {
        Log.d("my", "BonusController_init")
    }

    fun createBonusList(): MutableList<GameObjects.Bonus> {
        return gameObjectBuilder.createBonusList()
    }

    fun observeCenterObjects(bonus: GameObjects) {
        collisionOnLevel.observeCenterObjects(bonus)
    }

    fun onDragEnd(bonus: GameObjects.Bonus) {
        collisionOnLevel.takeAPlaces(bonus)
    }
}