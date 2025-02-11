package com.example.bricksGame.components.popups.controller

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ScreenSize
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopupsController @Inject constructor(
    private var screenSize: ScreenSize,
    gameConfig: GameConfig
) {

    var showPopupOnFinishGame = mutableStateOf(false)
    var showPopupWinLine = mutableStateOf(false)

    private var imageDefaultWinLine = gameConfig.imagesWinLine[0]
    private var imageMegaWinLine = gameConfig.imagesWinLine[1]

    var imageAsset = mutableIntStateOf(imageDefaultWinLine)

    var xPopupWinLine = mutableIntStateOf(0)
    var yPopupWinLine = mutableIntStateOf(0)

    var scalePopupWinLine = Animatable(initialValue = 1F)

    val winImage = gameConfig.imagesWinLevel[0]
    val loseImage = gameConfig.imagesWinLevel[1]
    var levelWin = mutableStateOf(false)


    fun onWinLine(megaWin: Boolean, placeOnField: PlaceOnField) {

        imageAsset.intValue = if (megaWin) imageMegaWinLine else imageDefaultWinLine


        placeOnField.let {
            val offset = getOffsetAmount(placeOnField)

            xPopupWinLine.intValue = offset.getValue("x").toInt()
            yPopupWinLine.intValue = offset.getValue("y").toInt()

        }
        showPopupWinLine.value = true
    }

    private fun getOffsetAmount(
        placeOnField: PlaceOnField,
    ): Map<String, Float> {

        val xPadding: Int = (screenSize.screenWidthPx / 2)
        val yPadding: Int = (screenSize.screenHeightPx / 2)
        val centerXWnLine = placeOnField.cords.globalWidth / 2
        val centerYWnLine = placeOnField.cords.globalHeight / 2

        val globalX = (placeOnField.cords.globalX - xPadding) + centerXWnLine
        val globalY = (placeOnField.cords.globalY - yPadding) + centerYWnLine


        val dragAmount = mapOf(
            "x" to globalX,
            "y" to globalY
        )
        return dragAmount
    }

    fun closePopupOnWinLine() {
        showPopupWinLine.value = false
        imageAsset.intValue = imageDefaultWinLine
//        scalePopupWinLine = Animatable(initialValue = 1F)
    }

    fun closePopupOnFinishGame() {
        showPopupOnFinishGame.value = false
    }

    fun showPopupOnFinishGame(onLevelWin: Boolean) {
        levelWin.value = onLevelWin
        showPopupOnFinishGame.value = true
    }
}