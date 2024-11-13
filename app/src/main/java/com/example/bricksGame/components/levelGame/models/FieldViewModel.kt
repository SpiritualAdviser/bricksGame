package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

object FieldViewModel : ViewModel() {

    val width = 380.dp
    val height = 380.dp
    val padding = 1.dp
    private val columns: Int = (width / BricksViewModel.width).toInt()
    private val rows: Int = (height / BricksViewModel.height).toInt()

    private val matrixField = generateMatrix(true)

    val colorList = getColorList(columns * rows)

    private fun getColorList(numberItems: Int): MutableList<Color> {
        val listResult: MutableList<Color> = mutableListOf()

        matrixField.forEachIndexed { indexColumn, array ->

            array.forEachIndexed { indexRow, value ->
                var keyColor = Color.Transparent
                if (value != 0) {
                    keyColor = colorsBricks.getValue(value)
                }
                listResult.add(keyColor)
            }
        }
        return listResult
    }

    private fun generateMatrix(
        matrixPrint: Boolean = false
    ): Array<Array<Int>> {

        val counter = 0
        val matrix = Array(rows) { Array(columns) { counter } }

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