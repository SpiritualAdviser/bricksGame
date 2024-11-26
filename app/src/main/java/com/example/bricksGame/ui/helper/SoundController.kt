package com.example.bricksGame.ui.helper

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.bricksGame.R
import com.example.bricksGame.ui.GameConfig

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
    private lateinit var levelTheme: MediaPlayer
    private lateinit var levelThemeTwo: MediaPlayer
    private lateinit var pushCristal: MediaPlayer
    private lateinit var winReel: MediaPlayer
    private lateinit var currentBgSound: MediaPlayer

    val playListLevel = listOf(1, 2)

    fun setContext(context: Context) {
        isRun = true
        this.context = context

        mainmeny = MediaPlayer.create(context, R.raw.main_meny)

        levelTheme = MediaPlayer.create(context, R.raw.action_level_one)
        levelThemeTwo = MediaPlayer.create(context, R.raw.action_level_two)
    }

    fun soundMute() {
        if (GameConfig.SOUND_MUTED) {
            currentBgSound.pause()
        } else {
            currentBgSound.start()
        }
    }

    fun playMainTheme() {

        if (levelTheme.isPlaying || levelThemeTwo.isPlaying) {
            levelTheme.stop()
            levelTheme.prepare()
            levelThemeTwo.stop()
            levelThemeTwo.prepare()
        }

        if (!mainmeny.isPlaying) {
            mainmeny.isLooping = true
            mainmeny.setVolume(1f, 1f)
            currentBgSound = mainmeny
            if (!GameConfig.SOUND_MUTED) {
                mainmeny.start()
            }
        } else {
            currentBgSound = mainmeny
            if (!GameConfig.SOUND_MUTED) {
                mainmeny.start()
            }
        }
    }

    fun playLevelTheme() {

        val soundPosition = playListLevel.random()

        if (mainmeny.isPlaying) {
            mainmeny.pause()
        }

        levelTheme.setOnCompletionListener {
            currentBgSound = levelThemeTwo
            levelThemeTwo.start()
        }

        levelThemeTwo.setOnCompletionListener {
            currentBgSound = levelTheme
            levelTheme.start()
        }

        when (soundPosition) {
            1 -> {
                if (!levelTheme.isPlaying) {
                    levelTheme.setVolume(1f, 1f)
                    currentBgSound = levelTheme
                    if (!GameConfig.SOUND_MUTED) {
                        levelTheme.start()
                    }
                } else {
                    if (!GameConfig.SOUND_MUTED) {
                        currentBgSound = levelTheme
                        levelTheme.start()
                    }
                }
            }

            2 -> {
                if (!levelThemeTwo.isPlaying) {
                    levelThemeTwo.setVolume(1f, 1f)
                    currentBgSound = levelThemeTwo
                    if (!GameConfig.SOUND_MUTED) {
                        levelThemeTwo.start()
                    }
                } else {
                    if (!GameConfig.SOUND_MUTED) {
                        currentBgSound = levelThemeTwo
                        levelThemeTwo.start()
                    }
                }
            }
        }
    }

    fun winReel() {
        if (GameConfig.SOUND_MUTED) {
            return
        }
        winReel = MediaPlayer.create(context, R.raw.win_reel)
        clickUi.setVolume(0.6f, 0.6f)
        winReel.start()
    }

    fun pushCristal() {
        if (GameConfig.SOUND_MUTED) {
            return
        }
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
        if (GameConfig.SOUND_MUTED) {
            return
        }
        clickUi = MediaPlayer.create(context, R.raw.ui_click)
        clickUi.setVolume(1.5f, 1.5f)
        clickUi.start()
    }
}