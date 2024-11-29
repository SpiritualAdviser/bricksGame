package com.example.bricksGame

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.example.bricksGame.components.levelGame.models.PlayerViewModel
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.AppNavigation
import com.example.bricksGame.ui.helper.ScreenSize
import com.example.bricksGame.ui.helper.SoundController

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
//            GameConfig.gameData = openOrCreateDatabase("gameData", MODE_PRIVATE, null)
//
//            if (openOrCreateDatabase("gameData", MODE_PRIVATE, null) == null) {
//                createData()
//            }
//            GameConfig.gameData.close()
//            val cursor = GameConfig.gameData.rawQuery("SELECT * FROM " + "player", null)
//
//            println(cursor)
        }
    }

//    private fun createData() {
//        val playerData = ContentValues()
//        playerData.put("name", PlayerViewModel.playerName)
//        playerData.put("playerAchievements", PlayerViewModel.playerAchievements)
//        GameConfig.gameData.insert("player", null, playerData)
//        GameConfig.gameData.close()
////        val cursor = GameConfig.gameData.rawQuery("SELECT * FROM " + "player", null)
////
////        println(cursor)
//    }



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










