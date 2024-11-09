package com.example.bricksGame

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.HomeScreenView
import com.example.bricksGame.components.levelGame.LevelGameView

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
            composable(Routes.HomeScreen.route) { HomeScreenView().Run() }
            composable(Routes.LevelGame.route) { LevelGameView().Run() }
        }
    }

    fun getNavController(): NavController {
        return _navController
    }
}

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("HomeScreen")
    data object LevelGame : Routes("LevelGame")
}


