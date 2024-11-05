package com.example.bricksGame.ui.helper

import androidx.navigation.NavHostController
import com.example.bricksGame.newGame

val buttonController = ButtonController()

class ButtonController {

    fun buttonLisener(name: String, navController:NavHostController) {
        when (name) {
            "HomeScreen"->openMainMeny(navController)
            "LevelGame"->openLevelGame(navController)
        }
    }

    private fun openLevelGame(navController: NavHostController) {
        newGame = true
        navController.navigate(Routes.LevelGame.route) {
            launchSingleTop = true
        }
    }
}

private fun openMainMeny(navController: NavHostController) {

    navController.navigate(Routes.HomeScreen.route) {
        launchSingleTop = true
        popUpTo(Routes.HomeScreen.route)
    }
}