package com.example.bricksGame.ui.helper

import android.content.Context
import android.media.MediaPlayer
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
    private lateinit var levelThemeTree: MediaPlayer
//    private lateinit var levelThemeFour: MediaPlayer
    private lateinit var levelThemeFive: MediaPlayer
//    private lateinit var levelThemeSix: MediaPlayer
    private lateinit var levelThemeSeven: MediaPlayer
    private lateinit var pushCristal: MediaPlayer
    private lateinit var winReel: MediaPlayer
    private lateinit var currentBgSound: MediaPlayer
    private lateinit var playListLevel: List<MediaPlayer>

    fun setContext(context: Context) {
        isRun = true
        this.context = context

        mainmeny = MediaPlayer.create(context, R.raw.main_meny)

        levelTheme = MediaPlayer.create(context, R.raw.action_level_one)
        levelThemeTwo = MediaPlayer.create(context, R.raw.action_level_two)
        levelThemeTree = MediaPlayer.create(context, R.raw.action_level_tree)
//        levelThemeFour = MediaPlayer.create(context, R.raw.action_level_four)
        levelThemeFive = MediaPlayer.create(context, R.raw.action_level_five)
//        levelThemeSix = MediaPlayer.create(context, R.raw.action_level_six)
        levelThemeSeven = MediaPlayer.create(context, R.raw.action_level_seven)

        setLoopOnLevel()

        playListLevel =
            listOf(
                levelTheme,
                levelThemeTwo,
                levelThemeTree,
//                levelThemeFour,
                levelThemeFive,
//                levelThemeSix,
                levelThemeSeven
            )
    }

    fun soundMuteOnStop() {
        if (!GameConfig.SOUND_MUTED) {
            currentBgSound.pause()
        }
    }

    fun soundMuteOnRestart() {

        if (!GameConfig.SOUND_MUTED) {
            currentBgSound.start()
        }
    }

    fun soundMute() {
        GameConfig.SOUND_MUTED = !GameConfig.SOUND_MUTED

        if (GameConfig.SOUND_MUTED) {
            currentBgSound.pause()
        } else {
            currentBgSound.start()
        }
    }

    fun playMainTheme() {

        playListLevel.forEach {
            if (it.isPlaying) {
                it.stop()
                it.prepare()
            }
        }
        mainmeny.isLooping = true
        currentBgSound = mainmeny
        if (!GameConfig.SOUND_MUTED) {
            mainmeny.start()
        }

//        if (!mainmeny.isPlaying) {
//            mainmeny.isLooping = true
//            mainmeny.setVolume(1f, 1f)
//            currentBgSound = mainmeny
//            if (!GameConfig.SOUND_MUTED) {
//                mainmeny.start()
//            }
//        } else {
//            currentBgSound = mainmeny
//            if (!GameConfig.SOUND_MUTED) {
//                mainmeny.start()
//            }
//        }
    }

    fun playLevelTheme() {

        if (mainmeny.isPlaying) {
            mainmeny.pause()
        }
        currentBgSound = playListLevel.random()

        if (!GameConfig.SOUND_MUTED) {
            currentBgSound.start()
        }

//        if (!currentBgSound.isPlaying) {
//            if (!GameConfig.SOUND_MUTED) {
//                currentBgSound.start()
//            }
//        } else {
//            if (!GameConfig.SOUND_MUTED) {
//                currentBgSound.start()
//            }
//        }
    }

    private fun setLoopOnLevel() {

//        levelTheme,
//        levelThemeTwo,
//        levelThemeTree,
//        levelThemeFour,
//        levelThemeFive,
//        levelThemeSix,
//        levelThemeSeven


        levelTheme.setOnCompletionListener {
            currentBgSound = levelThemeTwo
            levelThemeTwo.start()
        }

        levelThemeTwo.setOnCompletionListener {
            currentBgSound = levelThemeTree
            levelThemeTree.start()
        }

        levelThemeTree.setOnCompletionListener {
            currentBgSound = levelThemeFive
            levelThemeFive.start()
        }

//        levelThemeFour.setOnCompletionListener {
//            currentBgSound = levelThemeFive
//            levelThemeFive.start()
//        }

        levelThemeFive.setOnCompletionListener {
            currentBgSound = levelThemeSeven
            levelThemeSeven.start()
        }

//        levelThemeSix.setOnCompletionListener {
//            currentBgSound = levelThemeSeven
//            levelThemeSeven.start()
//        }

        levelThemeSeven.setOnCompletionListener {
            currentBgSound = levelTheme
            levelTheme.start()
        }
    }

    fun winReel() {
        if (GameConfig.SOUND_MUTED) {
            return
        }
        winReel = MediaPlayer.create(context, R.raw.win_reel)
        winReel.setVolume(0.5f, 0.5f)
        winReel.start()
    }

    fun pushCristal() {
        if (GameConfig.SOUND_MUTED) {
            return
        }
        pushCristal = MediaPlayer.create(context, R.raw.push_cristal)
        pushCristal.setVolume(0.7f, 0.7f)
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