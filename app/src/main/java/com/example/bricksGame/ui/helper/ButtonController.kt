package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.soundController

object ButtonController {

    fun navigateHome() {
        soundController.clickUi()
        soundController.playMainTheme()
        AppNavigation.getInstance().getNavController().navigate(Routes.HomeScreen.route) {

            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateLevelGame() {
        soundController.clickUi()
        soundController.playLevelTheme()
        BricksViewModel.resetData()
        FieldViewModel.resetData()
        PlayerViewModel.onStartLevel()

        AppNavigation.getInstance().getNavController().navigate(Routes.LevelGame.route) {
            popUpTo(Routes.LevelGame.route)
            launchSingleTop = true
        }
    }

    fun navigatePlayers() {
        soundController.clickUi()
        AppNavigation.getInstance().getNavController().navigate(Routes.Players.route) {

            popUpTo(Routes.Players.route)
            launchSingleTop = true
        }
    }

    fun navigateOptions() {
        soundController.clickUi()
        AppNavigation.getInstance().getNavController().navigate(Routes.Options.route) {
            popUpTo(Routes.Options.route)
            launchSingleTop = true
        }
    }
}