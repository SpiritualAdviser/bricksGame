package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.LevelGame

open class NawHostHandler private constructor() {

    companion object {
        private var instance: NawHostHandler? = null

        fun getInstance(): NawHostHandler {
            if (instance == null)
                synchronized(NawHostHandler::class.java) {

                    if (instance == null) {
                        instance = NawHostHandler()
                    }
                }
            return requireNotNull(instance)
        }
    }

    @Composable
    fun NawHost(navController:NavHostController) {

        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable(Routes.HomeScreen.route) { HomeScreen("Bricks", navController) }
            composable(Routes.LevelGame.route) { LevelGame(navController) }
        }
    }
}

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("HomeScreen")
    data object LevelGame : Routes("LevelGame")
}

