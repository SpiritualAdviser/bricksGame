package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.Map.Map
import com.example.bricksGame.components.players.PlayerView
import com.example.bricksGame.components.gameMeny.RunHomeScreen
import com.example.bricksGame.components.levelGame.RunLevelGame
import com.example.bricksGame.components.options.Options

class AppNavigation private constructor() {

    private lateinit var _navController: NavHostController

    companion object {
        private var instance: AppNavigation? = null

        fun getInstance(): AppNavigation {
            if (instance == null)
                synchronized(AppNavigation::class.java) {

                    if (instance == null) {
                        instance = AppNavigation()
                    }
                }
            return requireNotNull(instance)
        }
    }

    @Composable
    fun CreateNavHost() {
        _navController = rememberNavController()

        NavHost(navController = _navController, startDestination = Routes.HomeScreen.route) {
            composable(Routes.HomeScreen.route) { RunHomeScreen() }
            composable(Routes.LevelGame.route) { RunLevelGame() }
            composable(Routes.Players.route) { PlayerView() }
            composable(Routes.Options.route) { Options() }
            composable(Routes.Map.route) { Map() }
        }
    }

    fun getNavController(): NavController {
        return _navController
    }
}

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("HomeScreen")
    data object LevelGame : Routes("LevelGame")
    data object Players : Routes("Players")
    data object Options : Routes("Options")
    data object Map : Routes("Options")
}


