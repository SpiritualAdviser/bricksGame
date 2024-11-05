package com.example.bricksGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
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
    val navController = rememberNavController()
    NawHostHandler.getInstance().NawHost(navController)
}




