package com.example.bricksGame.helper

import com.example.bricksGame.components.map.controller.MapController
import com.example.bricksGame.config.GameConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ButtonController @Inject constructor(
    var gameConfig: GameConfig,
) {
    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var mapController: MapController

    fun navigateToHome() {

        soundController.clickUi()
        soundController.playMainTheme()
        appNavigation.getNavController().navigate(Routes.HomeScreen.route) {

            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateToLevelGame() {
        soundController.clickUi()
        soundController.playLevelTheme()

        appNavigation.getNavController().navigate(Routes.LevelGame.route) {
            popUpTo(Routes.LevelGame.route)
            launchSingleTop = true
        }
    }

    fun navigateToPlayers() {
        soundController.clickUi()
        appNavigation.getNavController().navigate(Routes.Players.route) {

            popUpTo(Routes.Players.route)
            launchSingleTop = true
        }
    }

    fun navigateToMap() {
        soundController.clickUi()
        soundController.playMainTheme()
        mapController.setLevelOnMap()

        appNavigation.getNavController().navigate(Routes.Map.route) {

            popUpTo(Routes.Map.route)
            launchSingleTop = true
        }
    }

    fun navigateToInfo() {
        soundController.clickUi()
        soundController.onWebView = true
        soundController.soundMuteOnStop()
        appNavigation.getNavController().navigate(Routes.Info.route) {
            popUpTo(Routes.Info.route)
            launchSingleTop = true
        }
    }
}