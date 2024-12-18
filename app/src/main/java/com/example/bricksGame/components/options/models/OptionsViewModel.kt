package com.example.bricksGame.components.options.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.ui.theme.outlineDarkMediumContrast
import com.example.bricksGame.ui.theme.primaryLight

object OptionsViewModel : ViewModel() {

    var threeInRowCardColor = mutableStateOf(outlineDarkMediumContrast)
    var fourInRowCardColor = mutableStateOf(outlineDarkMediumContrast)
    var fullRangeCardColor = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme3x4 = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme4x5 = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme5x6 = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme6x7 = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme7x8 = mutableStateOf(outlineDarkMediumContrast)
    var fieldScheme8x9 = mutableStateOf(outlineDarkMediumContrast)
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
        activeCard("FullRange")
        val fieldSchemeSelector = "FieldScheme${GameConfig.ROWS}x${GameConfig.COLUMNS}"
        setFieldScheme(fieldSchemeSelector)
    }

    fun activeCard(nameCard: String = "") {
        threeInRowCardColor.value = outlineDarkMediumContrast
        fourInRowCardColor.value = outlineDarkMediumContrast
        fullRangeCardColor.value = outlineDarkMediumContrast
        when (nameCard) {
            "ThreeInRow" -> {
                GameConfig.WIN_NUMBER_LINE = 3
                threeInRowCardColor.value = primaryLight
            }

            "FullRange" -> {
                GameConfig.WIN_NUMBER_LINE = 0
                fullRangeCardColor.value = primaryLight
            }

            "FourInRow" -> {
                GameConfig.WIN_NUMBER_LINE = 4
                fourInRowCardColor.value = primaryLight
            }
        }
    }

    fun setFieldScheme(scheme: String = "") {
        fieldSchemesList.forEach { it.value = outlineDarkMediumContrast }
        when (scheme) {
            "FieldScheme3x4" -> {
                GameConfig.ROWS = 3
                GameConfig.COLUMNS = 4
                fieldScheme3x4.value = primaryLight
            }

            "FieldScheme4x5" -> {
                GameConfig.ROWS = 4
                GameConfig.COLUMNS = 5
                fieldScheme4x5.value = primaryLight
            }

            "FieldScheme5x6" -> {
                GameConfig.ROWS = 5
                GameConfig.COLUMNS = 6
                fieldScheme5x6.value = primaryLight
            }

            "FieldScheme6x7" -> {
                GameConfig.ROWS = 6
                GameConfig.COLUMNS = 7
                fieldScheme6x7.value = primaryLight
            }

            "FieldScheme7x8" -> {
                GameConfig.ROWS = 7
                GameConfig.COLUMNS = 8
                fieldScheme7x8.value = primaryLight
            }

            "FieldScheme8x9" -> {
                GameConfig.ROWS = 8
                GameConfig.COLUMNS = 9
                fieldScheme8x9.value = primaryLight
            }
        }
        FieldViewModel.onOptionChange()
    }

    fun setBonusSpeedFill(speed: Float) {
        GameConfig.SPEED_OPEN_BONUS = speed
    }
}