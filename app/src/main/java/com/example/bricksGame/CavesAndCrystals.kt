package com.example.bricksGame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.ui.helper.AppNavigation
import com.example.bricksGame.ui.helper.ScreenSize
import com.example.bricksGame.ui.helper.SoundController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

val screenSize = ScreenSize()

@SuppressLint("StaticFieldLeak")
val soundController = SoundController.getInstance()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            screenSize.GetScreenSize()
            val context = LocalContext.current
            if (!soundController.isRun) {
                soundController.setContext(context)
                soundController.playMainTheme()
            }
            AppNavigation.getInstance().CreateNavHost()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStop() {
        super.onStop()
        GlobalScope.launch {
            soundController.soundMuteOnStop()
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (soundController.isRun) {
            soundController.soundMuteOnRestart()
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()
        if (soundController.isRun) {
            soundController.onContentChanged()
        }
    }
}










