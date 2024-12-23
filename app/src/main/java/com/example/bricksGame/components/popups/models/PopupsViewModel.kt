package com.example.bricksGame.components.popups.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.screenSize

object PopupsViewModel : ViewModel() {

    var showPopupOnFinishGame = mutableStateOf(false)
    var showPopupWinLine = mutableStateOf(false)

    var xPopupWinLine = mutableIntStateOf(0)
    var yPopupWinLine = mutableIntStateOf(0)

    var textWinLine = mutableStateOf("")

    var textDefaultWinLine = "WOOW"
    var textMegaWinLine = "WOOW WOOW WOOW"

    private const val WIN_TEXT = "You win!!!"
    private const val LOSE_TEXT = "You lose!!!"
    var textOnWinPopup = "You win!!!"

    fun onWinLine(megaWin: Boolean, fieldBrick: FieldBrick?) {
        fieldBrick?.let {
            val offset = getOffsetAmount(fieldBrick)
//            xPopupWinLine.intValue = offset.getValue("x").toInt()
            yPopupWinLine.intValue = offset.getValue("y").toInt()
        }

        textWinLine.value = if (megaWin) textMegaWinLine else textDefaultWinLine
        showPopupWinLine.value = true
    }

    private fun getOffsetAmount(fieldBrick: FieldBrick): Map<String, Float> {
        val xPadding: Int = (screenSize.screenWidthPx / 2)
        val yPadding: Int = (screenSize.screenHeightPx / 2)

        val globalX = fieldBrick.globalX
        val globalY = fieldBrick.globalY - yPadding

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
        textWinLine.value = textDefaultWinLine
    }

    fun closePopupOnFinishGame() {
        showPopupOnFinishGame.value = false
    }

    fun showPopupOnFinishGame() {
        showPopupOnFinishGame.value = true
    }
}