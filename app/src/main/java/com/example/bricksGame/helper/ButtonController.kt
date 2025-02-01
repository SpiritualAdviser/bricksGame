package com.example.bricksGame.helper

import com.example.bricksGame.config.GameConfig

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ButtonController @Inject constructor() {

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
//        GameConfig.GAME_TYPE_FREE = onFree
//        soundController.clickUi()
//        soundController.playLevelTheme()

//        FieldViewModel.onOptionChange()
//        PlayerViewModel.onStartLevel()

//        LevelLogic.onStartLevel()
//        AppNavigation.getInstance().getNavController().navigate(Routes.LevelGame.route) {
//            popUpTo(Routes.LevelGame.route)
//            launchSingleTop = true
//        }
    }

    fun navigateToPlayers() {
////        soundController.clickUi()
//        AppNavigation.getInstance().getNavController().navigate(Routes.Players.route) {
//
//            popUpTo(Routes.Players.route)
//            launchSingleTop = true
//        }
    }

    fun navigateToMap() {
//        soundController.clickUi()
//        soundController.playMainTheme()
//
//        MapModel.openLevelOnMap()
//        AppNavigation.getInstance().getNavController().navigate(Routes.Map.route) {
//
//            popUpTo(Routes.Map.route)
//            launchSingleTop = true
//        }
    }

    fun navigateFreeGame() {
//        soundController.clickUi()
//        soundController.playMainTheme()
//        FreeGameModel.onRunLevel()
//        navigateToLevelGame(true)
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