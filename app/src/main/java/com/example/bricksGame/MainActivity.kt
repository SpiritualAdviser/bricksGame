package com.example.bricksGame

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
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.AppNavigation
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.helper.SpriteAnimation
import com.example.bricksGame.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var screenSize: ScreenSize

    @Inject
    lateinit var levelData: LevelData

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        setContent {

            val context = LocalContext.current
            if (!soundController.isRun) {
                soundController.setContext(context)
                soundController.playMainTheme()
            }

            screenSize.GetScreenSize()
            levelData.onOptionChange()
            AppTheme {
                appNavigation.CreateNavHost()
            }
        }
    }

    override fun onStop() {
        super.onStop()
//        bricksController.canRunTranslation.value = false need Check Ivan!!!
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

    override fun attachBaseContext(newBase: Context?) {
        val newOverride = Configuration(newBase?.resources?.configuration)
        if (newOverride.fontScale >= 1.3f)
            newOverride.fontScale = 1.3f
        applyOverrideConfiguration(newOverride)
        super.attachBaseContext(newBase)
    }
}














