package com.example.bricksGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.LevelGame

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
    NawHostHandler(navController)
}

@Composable
fun NawHostHandler(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "mainMeny") {
        composable(Routes.MainMeny.route) { HomeScreen(navController, "Bricks") }
        composable(Routes.LevelGame.route) { LevelGame(navController) }
    }
}

sealed class Routes(val route: String) {
    object MainMeny : Routes("mainMeny")
    object LevelGame : Routes("levelGame")
}



