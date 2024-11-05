package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bricksGame.components.gameMeny.HomeScreen
import com.example.bricksGame.components.levelGame.Bricks
import com.example.bricksGame.components.levelGame.LevelGame

open class NawHostHandler private constructor() {
    private lateinit var navController: NavHostController

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
    fun NawHost() {
        navController = rememberNavController()

        NavHost(navController = navController, startDestination = "mainMeny") {
            composable(Routes.MainMeny.route) { HomeScreen("Bricks") }
            composable(Routes.LevelGame.route) { LevelGame() }
        }
    }

    fun getNavController(): NavHostController {
        return navController
    }
}

sealed class Routes(val route: String) {
    object MainMeny : Routes("mainMeny")
    object LevelGame : Routes("levelGame")
}

