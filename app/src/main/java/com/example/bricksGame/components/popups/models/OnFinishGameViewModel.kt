package com.example.bricksGame.components.popups.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

object OnFinishGameViewModel : ViewModel() {

    private const val WIN_TEXT = "You win!!!"
    private const val LOSE_TEXT = "You lose!!!"
    val textOnWinPopup = mutableStateOf(LOSE_TEXT)

    fun setWinOnLevel(wasWin: Boolean) {
        when {
            wasWin -> textOnWinPopup.value = WIN_TEXT
            else -> textOnWinPopup.value = LOSE_TEXT
        }
    }
}