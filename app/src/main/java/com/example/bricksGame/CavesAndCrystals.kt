package com.example.bricksGame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.components.players.models.PlayerViewModel
//import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.ui.helper.AppNavigation
import com.example.bricksGame.ui.helper.ScreenSize
import com.example.bricksGame.ui.helper.SoundController

val screenSize = ScreenSize()

@SuppressLint("StaticFieldLeak")
val soundController = SoundController.getInstance()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val playerViewModel = PlayerViewModel(this)

        enableEdgeToEdge()
        setContent {
            screenSize.GetScreenSize()
            val context = LocalContext.current
            playerViewModel.getAllData()

//           context.deleteDatabase("player_database")
            if (!soundController.isRun) {
                soundController.setContext(context)
                soundController.playMainTheme()
            }
            AppNavigation.getInstance().CreateNavHost(playerViewModel)
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










