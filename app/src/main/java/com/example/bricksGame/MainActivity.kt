package com.example.bricksGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bricksGame.ui.helper.AppNavigation
import com.example.bricksGame.ui.helper.ScreenSize

val screenSize = ScreenSize()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            screenSize.GetScreenSize()
            AppNavigation.getInstance().CreateNavHost()
        }
    }
}










