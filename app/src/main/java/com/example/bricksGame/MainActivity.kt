package com.example.bricksGame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.helper.AppNavigation
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

val screenSize = ScreenSize()

@SuppressLint("StaticFieldLeak")
val soundController = SoundController.getInstance()

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        setContent {
            screenSize.GetScreenSize()
            val context = LocalContext.current

            CoroutineScope(Dispatchers.Main).launch {
                DataRepository.getPlayerDatabase(context)

                PlayerViewModel.setPlayerOnGame()
            }

//   context.deleteDatabase("player_database")
//            DataRepository.getPlayerDatabase(this)
//
//
//            PlayerViewModel.setPlayerOnGame()


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
        AnimationsBrick.canRunTranslation.value = false
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














