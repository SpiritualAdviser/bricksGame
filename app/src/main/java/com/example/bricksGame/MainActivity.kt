package com.example.bricksGame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.components.options.models.OptionsViewModel
import com.example.bricksGame.components.players.data.ActiveLevelList
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.config.Level
import com.example.bricksGame.config.LevelsConfig
import com.example.bricksGame.helper.AppNavigation
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.LevelBuilder
import com.example.bricksGame.ui.theme.AppTheme
import com.google.gson.Gson

val screenSize = ScreenSize()

@SuppressLint("StaticFieldLeak")
val soundController = SoundController.getInstance()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        setContent {
            screenSize.GetScreenSize()
            val context = LocalContext.current

            OptionsViewModel.setOption()

   context.deleteDatabase("player_database")
            DataRepository.getPlayerDatabase(this)
            PlayerViewModel.setPlayerOnGame()


            if (!soundController.isRun) {
                soundController.setContext(context)
                soundController.playMainTheme()
            }
            AppTheme {
                AppNavigation.getInstance().CreateNavHost()
            }
        }
    }

    override fun onStop() {
        super.onStop()

        if (!isChangingConfigurations) {
            soundController.soundMuteOnStop()
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (soundController.isRun) {
            soundController.soundMuteOnRestart()
        }
    }
}














