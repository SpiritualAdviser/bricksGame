package com.example.bricksGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.bricksGame.components.levelGame.Bricks
import com.example.bricksGame.ui.helper.NawHostHandler
var newGame = true

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RunGame()
        }
    }
}

@Composable
fun RunGame() {
    val navController = NawHostHandler.getInstance()
    navController.NawHost()
}




