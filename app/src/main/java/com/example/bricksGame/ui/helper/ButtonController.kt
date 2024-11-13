package com.example.bricksGame.ui.helper

//import com.example.bricksGame.components.levelGame.models.BricksViewModel

object ButtonController {

    fun navigateHome() {
        AppNavigation.getInstance().getNavController().navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateLevelGame() {
//        BricksViewModel.createBricksList()
        AppNavigation.getInstance().getNavController().navigate(Routes.LevelGame.route) {
            popUpTo(Routes.LevelGame.route)
            launchSingleTop = true
        }
    }
}