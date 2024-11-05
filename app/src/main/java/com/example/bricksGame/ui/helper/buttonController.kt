package com.example.bricksGame.ui.helper

import androidx.navigation.NavHostController
import com.example.bricksGame.newGame

val buttonController = ButtonController()

class ButtonController {
    private val navController = NawHostHandler.getInstance().getNavController()

    fun buttonLisener(name: String) {
        when (name) {
            "mainMeny"->openMainMeny(navController)
            "levelGame"->openLevelGame(navController)
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

    navController.navigate(Routes.MainMeny.route) {
        launchSingleTop = true
        popUpTo(Routes.MainMeny.route)
    }
}