package com.example.bricksGame.helper

import com.example.bricksGame.components.freeGame.FreeGameModel
import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.logic.LevelLogic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ButtonController @Inject constructor(
    private var levelLogic: LevelLogic,
    var gameConfig: GameConfig,
    private var freeGameModel: FreeGameModel,

    ) {
    @Inject
    lateinit var soundController: SoundController

    @Inject
    lateinit var appNavigation: AppNavigation

    fun navigateToHome() {

        soundController.clickUi()
        soundController.playMainTheme()
        appNavigation.getNavController().navigate(Routes.HomeScreen.route) {

            popUpTo(Routes.HomeScreen.route)
            launchSingleTop = true
        }
    }

    fun navigateToLevelGame(onFree: Boolean = false) {
        gameConfig.GAME_TYPE_FREE = onFree
        soundController.clickUi()
        soundController.playLevelTheme()

        levelLogic.onStartLevel()
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

    fun navigateToMap(mapModel: MapModel? = null) {
        soundController.clickUi()
        soundController.playMainTheme()

        mapModel?.openLevelOnMap()
        appNavigation.getNavController().navigate(Routes.Map.route) {

            popUpTo(Routes.Map.route)
            launchSingleTop = true
        }
    }

    fun navigateFreeGame(mapModel: MapModel) {
        soundController.clickUi()
        soundController.playMainTheme()
        mapModel.openLevelOnMap()

        freeGameModel.onRunLevel(mapModel)
        navigateToLevelGame(true)
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