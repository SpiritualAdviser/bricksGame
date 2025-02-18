package com.example.bricksGame

import android.app.Application
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.helper.SpriteAnimation
import com.example.bricksGame.internet.PlayerRecordsRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyHiltApp : Application() {

    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var playerRepository: PlayerRepository

    @Inject
    lateinit var spriteAnimation: SpriteAnimation

   @Inject
   lateinit var playerRecordsRepository: PlayerRecordsRepository

    override fun onCreate() {
        super.onCreate()

        spriteAnimation.setAnimationOnGame()
        playerRecordsRepository.getRecords()
    }
}