package com.example.bricksGame

import android.app.Application
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.GameData
import com.example.bricksGame.helper.AppNavigation
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
    lateinit var dataRepository: DataRepository

    @Inject
    lateinit var gameData: GameData

    override fun onCreate() {
        super.onCreate()

        if (!soundController.isRun) {
            soundController.setContext(applicationContext)
        }

        setSprite()
        dataRepository.getPlayerDatabase(applicationContext)

        gameData.start()
    }

    private fun setSprite() {
        val animList = listOf("bg_close_brick.json", "bg_close_leaves.json")
        SpriteAnimation.setAnimationOnGame(applicationContext, animList)
    }
}