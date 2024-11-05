package com.example.bricksGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.LevelGame

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
//    NavHost(navController = navController, startDestination = "HomeScreen") {
//
//        composable("HomeScreen") {
//            HomeScreen("555", navController)
//        }
//
//        composable("LevelGame") {
//            LevelGame(navController)
//        }
//    }


    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) { HomeScreen("55", navController) }
        composable(Routes.LevelGame.route) { LevelGame(navController) }
    }
}

sealed class Routes(val route: String) {

    object HomeScreen : Routes("HomeScreen")
    object LevelGame : Routes("LevelGame")
}




