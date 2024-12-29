package com.example.bricksGame.helper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.RunHomeScreen
import com.example.bricksGame.components.info.Info
import com.example.bricksGame.components.levelGame.RunLevelGame
import com.example.bricksGame.components.map.Map
import com.example.bricksGame.components.players.PlayerView

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

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun CreateNavHost() {
        _navController = rememberNavController()


        NavHost(navController = _navController,
            startDestination = Routes.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {

            composable(Routes.HomeScreen.route) { RunHomeScreen() }
            composable(Routes.LevelGame.route,
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(300, easing = LinearEasing),
                    )
                },
                exitTransition = {
                    scaleOut(
                        animationSpec = tween(300, easing = LinearEasing),
                    )
                }

            ) { RunLevelGame() }
            composable(Routes.Players.route) { PlayerView() }
            composable(Routes.Info.route) { Info("https://spiritualadviser.github.io/Standcorexam/") }
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
    data object Info : Routes("Description")
    data object Map : Routes("Map")
}


