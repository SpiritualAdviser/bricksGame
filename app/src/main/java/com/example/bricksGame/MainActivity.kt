package com.example.bricksGame

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.players.data.DataRepository
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.helper.AppNavigation
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.helper.SpriteAnimation
import com.example.bricksGame.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

val screenSize = ScreenSize()

@SuppressLint("StaticFieldLeak")
val soundController = SoundController.getInstance()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var myClass:MyClass

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        setContent {
            myClass.numInt
            screenSize.GetScreenSize()
            val context = LocalContext.current

            LaunchedEffect(null) {
            DataRepository.getPlayerDatabase(context)

            PlayerViewModel.setPlayerOnGame()
        }

            setSprite()

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

    private fun setSprite() {
        val animList = listOf("bg_close_brick.json", "bg_close_leaves.json")
        SpriteAnimation.setAnimationOnGame(applicationContext, animList)
    }

    override fun attachBaseContext(newBase: Context?) {
        val newOverride = Configuration(newBase?.resources?.configuration)
        if (newOverride.fontScale >= 1.3f)
            newOverride.fontScale = 1.3f
        applyOverrideConfiguration(newOverride)
        super.attachBaseContext(newBase)
    }
}














