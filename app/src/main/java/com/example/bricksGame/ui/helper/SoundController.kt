package com.example.bricksGame.ui.helper

import android.content.Context
import android.media.MediaPlayer
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
            mainmeny.setVolume(1f, 1f)
            mainmeny.start()
        }
    }

    fun playLevelTheme() {
        val currentIdSound = playListLevel.random()

        if (mainmeny.isPlaying) {
            mainmeny.stop()
            mainmeny.prepare()
        }

        levelThemeOne.setOnCompletionListener {
            levelThemeTwo.setVolume(1f, 1f)
            levelThemeTwo.start()
        }
        levelThemeTwo.setOnCompletionListener {

            levelThemeOne.setVolume(1f, 1f)
            levelThemeOne.start()
        }

        when (currentIdSound) {
            1 -> {

                if (!levelThemeOne.isPlaying) {
                    levelThemeOne.setVolume(1f, 1f)
                    levelThemeOne.start()
                }
            }

            2 -> {
                if (!levelThemeTwo.isPlaying) {
                    levelThemeTwo.setVolume(1f, 1f)
                    levelThemeTwo.start()
                }
            }
        }
    }

    fun winReel() {
        winReel = MediaPlayer.create(context, R.raw.win_reel)
        clickUi.setVolume(0.6f, 0.6f)
        winReel.start()
    }

    fun pushCristal() {
        pushCristal = MediaPlayer.create(context, R.raw.push_cristal)
        pushCristal.setVolume(0.8f, 0.8f)
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