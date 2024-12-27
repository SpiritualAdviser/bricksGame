package com.example.bricksGame.components.popups.models

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.screenSize

object PopupsViewModel : ViewModel() {

    var showPopupOnFinishGame = mutableStateOf(false)
    var showPopupWinLine = mutableStateOf(false)

    var imageDefaultWinLine = GameConfig.imagesWinLine[0]
    var imageMegaWinLine = GameConfig.imagesWinLine[1]

    var imageAsset = mutableIntStateOf(imageDefaultWinLine)

    var xPopupWinLine = mutableIntStateOf(0)
    var yPopupWinLine = mutableIntStateOf(0)

    var scalePopupWinLine = Animatable(initialValue = 1F)

    private const val WIN_TEXT = "You win!!!"
    private const val LOSE_TEXT = "You lose!!!"
    var textOnWinPopup = "You win!!!"

    fun onWinLine(
        megaWin: Boolean,
        fieldBrick: FieldBrick?,
        winLineSize: Int,
        rowDirection: Boolean
    ) {

        fieldBrick?.let {
            val offset = getOffsetAmount(fieldBrick, winLineSize, rowDirection)

            xPopupWinLine.intValue = offset.getValue("x").toInt()
            yPopupWinLine.intValue = offset.getValue("y").toInt()
        }
        imageAsset.intValue = if (megaWin) imageMegaWinLine else imageDefaultWinLine
        showPopupWinLine.value = true
        println()
    }

    private fun getOffsetAmount(
        fieldBrick: FieldBrick,
        winLineSize: Int,
        rowDirection: Boolean
    ): Map<String, Float> {
        val xPadding: Int = (screenSize.screenWidthPx / 2)
        val yPadding: Int = (screenSize.screenHeightPx / 2)

        var centerXWnLine = 0
        var centerYWnLine = 0

        if (rowDirection) {
            if (winLineSize % 2 == 0) {
                centerXWnLine = 0
                centerYWnLine = fieldBrick.globalHeight / 2
            } else {
                centerXWnLine = fieldBrick.globalWidth / 2
                centerYWnLine = fieldBrick.globalHeight / 2
            }

        } else {
            if (winLineSize % 2 == 0) {
                centerXWnLine = fieldBrick.globalWidth / 2
                centerYWnLine = 0
            } else {
                centerXWnLine = fieldBrick.globalWidth / 2
                centerYWnLine = fieldBrick.globalHeight / 2
            }
        }

        val globalX = (fieldBrick.globalX - xPadding) + centerXWnLine
        val globalY = (fieldBrick.globalY - yPadding) + centerYWnLine

        val dragAmount = mapOf(
            "x" to globalX,
            "y" to globalY
        )
        return dragAmount
    }

    fun setTextOnLevel(wasWin: Boolean) {
        when {
            wasWin -> textOnWinPopup = WIN_TEXT
            else -> textOnWinPopup = LOSE_TEXT
        }
    }

    fun closePopupOnWinLine() {
        showPopupWinLine.value = false
        imageAsset.intValue = imageDefaultWinLine
        scalePopupWinLine=Animatable(initialValue = 1F)
    }

    fun closePopupOnFinishGame() {
        showPopupOnFinishGame.value = false
    }

    fun showPopupOnFinishGame() {
        showPopupOnFinishGame.value = true
    }
}