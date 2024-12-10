package com.example.bricksGame.components.options.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.GameConfig

object OptionsViewModel : ViewModel() {

    var threeInRowCardColor = mutableStateOf(Color.LightGray)
    var fullRangeCardColor = mutableStateOf(Color.LightGray)
    var fieldScheme3x4 = mutableStateOf(Color.LightGray)
    var fieldScheme4x5 = mutableStateOf(Color.LightGray)
    var fieldScheme5x6 = mutableStateOf(Color.LightGray)
    var fieldScheme6x7 = mutableStateOf(Color.LightGray)
    var fieldScheme7x8 = mutableStateOf(Color.LightGray)
    var fieldScheme8x9 = mutableStateOf(Color.LightGray)
    val fieldSchemesList =
        listOf(
            fieldScheme3x4,
            fieldScheme4x5,
            fieldScheme5x6,
            fieldScheme6x7,
            fieldScheme7x8,
            fieldScheme8x9
        )

    fun setOption() {
        activeCard()
        val fieldSchemeSelector = "FieldScheme${GameConfig.ROWS}x${GameConfig.COLUMNS}"
        setFieldScheme(fieldSchemeSelector)
    }

    fun activeCard(nameCard: String = "") {
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

    fun setFieldScheme(scheme: String = "") {
        fieldSchemesList.forEach { it.value = Color.LightGray }
        when (scheme) {
            "FieldScheme3x4" -> {
                GameConfig.ROWS = 3
                GameConfig.COLUMNS = 4
                fieldScheme3x4.value = Color.Green
            }

            "FieldScheme4x5" -> {
                GameConfig.ROWS = 4
                GameConfig.COLUMNS = 5
                fieldScheme4x5.value = Color.Green
            }

            "FieldScheme5x6" -> {
                GameConfig.ROWS = 5
                GameConfig.COLUMNS = 6
                fieldScheme5x6.value = Color.Green
            }

            "FieldScheme6x7" -> {
                GameConfig.ROWS = 6
                GameConfig.COLUMNS = 7
                fieldScheme6x7.value = Color.Green
            }

            "FieldScheme7x8" -> {
                GameConfig.ROWS = 7
                GameConfig.COLUMNS = 8
                fieldScheme7x8.value = Color.Green
            }

            "FieldScheme8x9" -> {
                GameConfig.ROWS = 8
                GameConfig.COLUMNS = 9
                fieldScheme8x9.value = Color.Green
            }
        }
        FieldViewModel.onOptionChange()
    }
}