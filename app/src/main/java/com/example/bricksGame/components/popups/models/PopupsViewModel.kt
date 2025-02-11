package com.example.bricksGame.components.popups.models

import androidx.compose.animation.core.Animatable
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.popups.controller.PopupsController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopupsViewModel @Inject constructor(
    val gameConfig: GameConfig,
    var soundController: SoundController,
    private var popupsController: PopupsController
) : ViewModel() {

    var showPopupOnFinishGame = popupsController.showPopupOnFinishGame
    var showPopupWinLine = popupsController.showPopupWinLine

    var imageAsset = popupsController.imageAsset

    var xPopupWinLine = popupsController.xPopupWinLine
    var yPopupWinLine = popupsController.yPopupWinLine

    var scalePopupWinLine = popupsController.scalePopupWinLine

    var imageAssetOnWinLevel = popupsController.winImage
    var imageAssetOnLoseLevel = popupsController.loseImage

    var levelWin = popupsController.levelWin

    fun closePopupOnWinLine() {
        popupsController.closePopupOnWinLine()
    }
}