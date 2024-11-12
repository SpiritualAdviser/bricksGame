package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

object FieldViewModel : ViewModel() {

    val width = 380.dp
    val height = 390.dp
    val padding = 10.dp
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
        matrix[0][0] = 1
        matrix[0][2] = 2

        matrix[1][1] = 4
        matrix[1][4] = 3

        matrix[2][1] = 4
        matrix[2][3] = 3

        matrix[4][4] = 4
        matrix[4][3] = 3

        matrix[5][0] = 4
        matrix[5][4] = 3

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