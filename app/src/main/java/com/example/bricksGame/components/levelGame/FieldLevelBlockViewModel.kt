package com.example.bricksGame.components.levelGame

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colors

class FieldLevelBlockViewModel : ViewModel() {

    private val bricksViewModel = BricksViewModel()

    val width = 380.dp
    val height = 390.dp
    val padding = 10.dp
    private val columns: Int = (width / bricksViewModel.width).toInt()
    private val rows: Int = (height / bricksViewModel.height).toInt()
    val colorList = getColorList(columns * rows)
    val matrixField = generateMatrix(true)

    private fun getColorList(numberItems: Int): MutableList<Color> {
        val listResult: MutableList<Color> = mutableListOf()
        for (i in 1..numberItems) {
            listResult.add(getRandomColor())
        }
        return listResult
    }

    private fun getRandomColor(): Color {
        val currentColor = colors.random()
        return currentColor
    }

    private fun generateMatrix(
        matrixPrint: Boolean = false
    ): Array<Array<Int>> {

        val counter = 0
        val matrix = Array(columns) { Array(rows) { counter } }

        if (matrixPrint) {
            matrix.forEach { columnValue ->

                columnValue.forEach { rowValue ->
                    print("$rowValue \t")
                }
                println()
            }
        }
        return matrix
    }
}