package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel

object ButtonController {

    fun navigateHome() {
        AppNavigation.getInstance().getNavController().navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateLevelGame() {
        AppNavigation.getInstance().getNavController().navigate(Routes.LevelGame.route) {
            BricksViewModel.resetData()
            FieldViewModel.resetData()
            popUpTo(Routes.LevelGame.route)
            launchSingleTop = true
        }
    }
}