package com.example.bricksGame.ui.helper

object ButtonController {

    fun navigateHome() {
        AppNavigation.getInstance().getNavController().navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateLevelGame() {
        AppNavigation.getInstance().getNavController().navigate(Routes.LevelGame.route) {
            popUpTo(Routes.LevelGame.route)
            launchSingleTop = true
        }
    }
}