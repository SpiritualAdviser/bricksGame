package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.LevelGame

open class NavHostHandler {

    lateinit var navController: NavHostController

    @Composable
    fun CreateNavHost() {
        this.navController = rememberNavController()

        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable("HomeScreen") { HomeScreen(navController).Run() }
            composable("LevelGame") { LevelGame(navController).Run() }
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

