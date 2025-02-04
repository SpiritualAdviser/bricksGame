package com.example.bricksGame

import android.app.Application
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.helper.SpriteAnimation
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyHiltApp : Application() {
    @Inject
    lateinit var gameConfig: GameConfig

    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var levelData: LevelData

    @Inject
    lateinit var buttonController: ButtonController

    override fun onCreate() {
        super.onCreate()

        if (!soundController.isRun) {
            soundController.setContext(applicationContext)
            soundController.playMainTheme()
        }

        setSprite()
    }

    private fun setSprite() {
        val animList = listOf("bg_close_brick.json", "bg_close_leaves.json")
        SpriteAnimation.setAnimationOnGame(applicationContext, animList)
    }
}