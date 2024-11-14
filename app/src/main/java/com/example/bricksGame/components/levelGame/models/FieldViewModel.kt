package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.ui.helper.ScreenSize
import com.example.bricksGame.ui.theme.colorsBricks

object FieldViewModel : ViewModel() {
    private const val COLUMNS: Int = 6
    private const val ROWS: Int = 5

    private val padding = 10.dp
    val width = screenSize.screenWidthDp - padding * 2
    private val levelScore = screenSize.screenHeightDp / 100 * 20
    val height = screenSize.screenHeightDp - levelScore + padding * 2

    val brickOnField = createBricksList()

    private fun createBricksList() {

        val count = COLUMNS + ROWS


        val fieldBrick = FieldBrick()


    }

    private
    val matrixField = generateMatrix(true)

    val colorList = getColorList(COLUMNS * ROWS)

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
        val matrix = Array(ROWS) { Array(COLUMNS) { counter } }

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

data class FieldBrick(
    val name: String = "Field",
    val width: Dp = 0.dp,
    val height: Dp = 0.dp,
    val id: Int = 0,
    val position: Int = 0,
)
