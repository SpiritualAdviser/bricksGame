package com.example.bricksGame

import android.app.Application
import androidx.compose.ui.text.intl.Locale
import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.helper.SpriteAnimation
import com.example.bricksGame.internet.PlayerRecordsRepository
import com.example.bricksGame.localization.Dictionary
import com.example.bricksGame.localization.Localization
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
        val currentLocale = Locale.current.language
        val systemLanguage = when (currentLocale) {
            "ru" -> Dictionary().ru
            "de" -> Dictionary().de
            "en" -> Dictionary().en
            else -> Dictionary().en
        }

        super.onCreate()
        Localization.runTranslation(systemLanguage)
        spriteAnimation.setAnimationOnGame()
        playerRecordsRepository.getRecords()
    }
}