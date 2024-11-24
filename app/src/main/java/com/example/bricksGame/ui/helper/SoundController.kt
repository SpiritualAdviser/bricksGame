package com.example.bricksGame.ui.helper

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.NavHostController
import com.example.bricksGame.R

class SoundController private constructor() {

    var isRun = false

    companion object {
        private var instance: SoundController? = null

        fun getInstance(): SoundController {
            if (instance == null)
                synchronized(SoundController::class.java) {

                    if (instance == null) {
                        instance = SoundController()
                    }
                }
            return requireNotNull(instance)
        }
    }

    private lateinit var context: Context
    private lateinit var mainmeny: MediaPlayer
    private lateinit var clickUi: MediaPlayer
    private lateinit var levelThemeOne: MediaPlayer
    private lateinit var levelThemeTwo: MediaPlayer
    private lateinit var pushCristal: MediaPlayer
    private lateinit var winReel: MediaPlayer

    val playListLevel = listOf(1, 2)

    fun setContext(context: Context) {
        isRun = true
        this.context = context

        mainmeny = MediaPlayer.create(context, R.raw.main_meny)

        levelThemeOne = MediaPlayer.create(context, R.raw.action_level_one)
        levelThemeTwo = MediaPlayer.create(context, R.raw.action_level_two)
    }

    fun playMainTheme() {

        if (levelThemeOne.isPlaying || levelThemeTwo.isPlaying) {
            levelThemeOne.stop()
            levelThemeOne.prepare()
            levelThemeTwo.stop()
            levelThemeTwo.prepare()
        }

        if (!mainmeny.isPlaying) {
            mainmeny.isLooping = true
            mainmeny.setVolume(0.8f, 0.8f)
            mainmeny.start()
        }
    }

    fun playLevelTheme() {
        val currentIdSound = playListLevel.random()

        if (mainmeny.isPlaying) {
            mainmeny.stop()
            mainmeny.prepare()
        }

        when (currentIdSound) {
            1 -> {
                if (!levelThemeOne.isPlaying) {
                    levelThemeOne.isLooping = true
                    levelThemeOne.setVolume(0.5f, 0.5f)
                    levelThemeOne.start()
                }
            }

            2 -> {
                if (!levelThemeTwo.isPlaying) {
                    levelThemeTwo.isLooping = true
                    levelThemeTwo.setVolume(0.5f, 0.5f)
                    levelThemeTwo.start()
                }
            }
        }
    }

    fun winReel() {
        winReel = MediaPlayer.create(context, R.raw.win_reel)
        clickUi.setVolume(1f, 1f)
        winReel.start()
    }

    fun pushCristal() {
        pushCristal = MediaPlayer.create(context, R.raw.push_cristal)
        pushCristal.setVolume(1f, 1f)
        pushCristal.start()
    }

//    fun cristalAdd() {
//        cristalAdd = MediaPlayer.create(context, R.raw.cristal_add)
//        cristalAdd.setVolume(1f, 1f)
//        cristalAdd.start()
//    }

    fun clickUi() {
        clickUi = MediaPlayer.create(context, R.raw.ui_click)
        clickUi.setVolume(1.5f, 1.5f)
        clickUi.start()
    }
}