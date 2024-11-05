package com.example.bricksGame.ui.helper

import androidx.navigation.NavHostController
import com.example.bricksGame.newGame

val buttonController = ButtonController()

class ButtonController {

    fun buttonListener(name: String, navController:NavHostController) {
        when (name) {
            "HomeScreen"->openMainMany(navController)
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

private fun openMainMany(navController: NavHostController) {

    navController.navigate(Routes.HomeScreen.route) {
        launchSingleTop = true
        popUpTo(Routes.HomeScreen.route)
    }
}