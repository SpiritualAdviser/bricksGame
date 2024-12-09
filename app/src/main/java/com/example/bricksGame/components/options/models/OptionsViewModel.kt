package com.example.bricksGame.components.options.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.bricksGame.ui.GameConfig

object OptionsViewModel : ViewModel() {

    var threeInRowCardColor = mutableStateOf(Color.LightGray)
    var fullRangeCardColor = mutableStateOf(Color.LightGray)

    fun activeCard(nameCard: String) {
        when (nameCard) {
            "ThreeInRow" -> {
                GameConfig.WIN_NUMBER_LINE = 3
            }

            "FullRange" -> {
                GameConfig.WIN_NUMBER_LINE = 0
            }
        }

        if (GameConfig.WIN_NUMBER_LINE == 3) {
            threeInRowCardColor.value = Color.Green
            fullRangeCardColor.value = Color.LightGray
        } else {
            fullRangeCardColor.value = Color.Green
            threeInRowCardColor.value = Color.LightGray
        }
    }
}