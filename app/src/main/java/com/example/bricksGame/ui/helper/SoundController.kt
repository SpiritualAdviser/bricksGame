package com.example.bricksGame.ui.helper

import android.content.Context
import android.media.MediaPlayer
import com.example.bricksGame.R

class SoundController {
    private lateinit var context: Context
    private lateinit var mainmeny: MediaPlayer
    private lateinit var mainmenyOn: MediaPlayer
    private lateinit var clickUi: MediaPlayer
    private lateinit var levelThemeOne: MediaPlayer
    private lateinit var levelThemeTwo: MediaPlayer
    private lateinit var pushCristal: MediaPlayer
    private lateinit var winReel: MediaPlayer
    private lateinit var cristalAdd: MediaPlayer

    val playListLevel = listOf(1, 2)

    fun setContext(context: Context) {
        this.context = context

        mainmeny = MediaPlayer.create(context, R.raw.main_meny)
        mainmenyOn = MediaPlayer.create(context, R.raw.main_menyon)

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

        if (!mainmenyOn.isPlaying) {
            mainmenyOn.isLooping = true
            mainmenyOn.setVolume(0.8f, 0.8f)
            mainmenyOn.start()
        }
    }

    fun playLevelTheme() {
        val currentIdSound = playListLevel.random()

        if (mainmenyOn.isPlaying) {
            mainmenyOn.stop()
            mainmenyOn.prepare()
        }

        when (currentIdSound) {
            1 -> {
                if (!levelThemeOne.isPlaying) {
                    mainmenyOn.isLooping = true
                    levelThemeOne.setVolume(0.5f, 0.5f)
                    levelThemeOne.start()
                }
            }

            2 -> {
                if (!levelThemeTwo.isPlaying) {
                    mainmenyOn.isLooping = true
                    levelThemeTwo.setVolume(0.5f, 0.5f)
                    levelThemeTwo.start()
                }
            }
        }
    }

    fun winReel() {
        winReel = MediaPlayer.create(context, R.raw.win_reel)
        clickUi.setVolume(1.2f, 1.2f)
        winReel.start()
    }

    fun pushCristal() {
        pushCristal = MediaPlayer.create(context, R.raw.push_cristal)
        pushCristal.setVolume(1.2f, 1.2f)
        pushCristal.start()
    }

    fun cristalAdd() {
        cristalAdd = MediaPlayer.create(context, R.raw.cristal_add)
        cristalAdd.setVolume(1f, 1f)
        cristalAdd.start()
    }

    fun clickUi() {
        clickUi = MediaPlayer.create(context, R.raw.ui_click)
        clickUi.setVolume(1.5f, 1.5f)
        clickUi.start()
    }
}