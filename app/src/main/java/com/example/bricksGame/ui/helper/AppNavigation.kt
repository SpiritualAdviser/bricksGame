package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.players.PlayerView
import com.example.bricksGame.components.gameMeny.RunHomeScreen
import com.example.bricksGame.components.levelGame.RunLevelGame
import com.example.bricksGame.components.players.models.PlayerViewModel

//import com.example.bricksGame.components.players.models.PlayerViewModel

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
    fun CreateNavHost(playerViewModel: PlayerViewModel) {
        _navController = rememberNavController()

        NavHost(navController = _navController, startDestination = Routes.HomeScreen.route) {
            composable(Routes.HomeScreen.route) { RunHomeScreen() }
            composable(Routes.LevelGame.route) { RunLevelGame() }
            composable(Routes.Players.route) { PlayerView(playerViewModel) }
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
}


