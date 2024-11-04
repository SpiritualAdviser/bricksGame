package com.example.bricksGame.ui.helper

import androidx.navigation.NavHostController

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
        navController.navigate(Routes.LevelGame.route)
    }
}

private fun openMainMeny(navController: NavHostController) {
    navController.navigate(Routes.MainMeny.route) {
        launchSingleTop = true
        popUpTo(Routes.MainMeny.route)
    }
}