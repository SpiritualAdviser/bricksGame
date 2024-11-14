package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.ui.theme.colorsBricks
import kotlin.math.round

object FieldViewModel : ViewModel() {
    const val COLUMNS: Int = 6
    const val ROWS: Int = 5
    private const val COLUMNS_BLOCK_SHAPES = 3

    private val padding = 10.dp
    val width = screenSize.screenWidthDp - padding * 2
    private val levelScore = screenSize.screenHeightDp / 100 * 20
    val height = screenSize.screenHeightDp - levelScore + padding * 2

    val brickOnField = createBricksList()

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = COLUMNS * ROWS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % 5 == 0) {
                ++positionColumn
                positionRow = 0
            }

            bricksList.add(createBrick(positionColumn, positionRow))
            ++positionRow
        }
        println(bricksList.toString())
        return bricksList
    }

    private fun createBrick(positionColumn: Int, positionRow: Int): FieldBrick {
        return FieldBrick(
            width = round(this.width.value / ROWS).dp,
            height = round(this.height.value / (COLUMNS + COLUMNS_BLOCK_SHAPES)).dp,
            position = Pair(positionColumn, positionRow),
            id = "$positionColumn $positionRow",
            color = colorsBricks.getValue(positionColumn)
        )
    }

    private
    val matrixField = generateMatrix(true)

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
    val name: String = "FieldBricks",
    val width: Dp = 0.dp,
    val height: Dp = 0.dp,
    val id: String = "00",
    val color: Color = Color.Transparent,
    val position: Pair<Int, Int>,
)
