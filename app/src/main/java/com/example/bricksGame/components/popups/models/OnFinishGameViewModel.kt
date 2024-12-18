package com.example.bricksGame.components.popups.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

object OnFinishGameViewModel : ViewModel() {

    var showPopupOnFinishGame = mutableStateOf(false)
    private const val WIN_TEXT = "You win!!!"
    private const val LOSE_TEXT = "You lose!!!"
    var textOnWinPopup = "You win!!!"

    fun setTextOnLevel(wasWin: Boolean) {
        when {
            wasWin -> textOnWinPopup = WIN_TEXT
            else -> textOnWinPopup = LOSE_TEXT
        }
    }

    fun closePopupOnFinishGame() {
        showPopupOnFinishGame.value = false
    }

    fun showPopupOnFinishGame() {
        showPopupOnFinishGame.value = true
    }
}