package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.LevelGame

class NavHostHandler private constructor() {

    private lateinit var navController: NavHostController

    companion object {

        private var instanceHandler: NavHostHandler? = null

        fun getInstance(): NavHostHandler {
            if (instanceHandler == null)
                synchronized(NavHostHandler::class.java) {

                    if (instanceHandler == null) {
                        instanceHandler = NavHostHandler()
                    }
                }
            return requireNotNull(instanceHandler)
        }
    }

    @Composable
    fun CreateNavHost(navController: NavHostController) {
        this.navController = navController

        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable("HomeScreen") { HomeScreen("Bricks") }
            composable("LevelGame") { LevelGame() }
        }
    }

    @Composable
    fun getNavController(): NavHostController {
        return navController
    }
//
//    sealed class Routes(val route: String) {
//        data object HomeScreen : Routes("HomeScreen")
//        data object LevelGame : Routes("LevelGame")
//    }
}

