package com.example.bricksGame.ui.helper

import com.example.bricksGame.components.Map.models.MapModel
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig

object ButtonController {

    fun navigateToHome() {
        soundController.clickUi()
        soundController.playMainTheme()
        GameConfig.GAME_TYPE_FREE = true
        AppNavigation.getInstance().getNavController().navigate(Routes.HomeScreen.route) {

            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateToLevelGame() {
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

    fun navigateToPlayers() {
        soundController.clickUi()
        AppNavigation.getInstance().getNavController().navigate(Routes.Players.route) {

            popUpTo(Routes.Players.route)
            launchSingleTop = true
        }
    }

    fun navigateToMap() {
        soundController.clickUi()
        soundController.playMainTheme()
        MapModel.openLevelOnMap()
        GameConfig.GAME_TYPE_FREE = false
        AppNavigation.getInstance().getNavController().navigate(Routes.Map.route) {

            popUpTo(Routes.Players.route)
            launchSingleTop = true
        }
    }

    fun navigateToOptions() {
        soundController.clickUi()
        AppNavigation.getInstance().getNavController().navigate(Routes.Options.route) {
            popUpTo(Routes.Options.route)
            launchSingleTop = true
        }
    }
}