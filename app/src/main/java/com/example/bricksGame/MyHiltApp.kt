package com.example.bricksGame

import android.app.Application
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.AppNavigation
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyHiltApp : Application(){
    @Inject
    lateinit var gameConfig: GameConfig

    override fun onCreate() {
        super.onCreate()
    }
}