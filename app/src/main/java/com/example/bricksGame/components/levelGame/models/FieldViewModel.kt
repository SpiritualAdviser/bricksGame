package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import com.example.bricksGame.ui.theme.colorsBricks
import kotlin.math.round

object FieldViewModel : ViewModel() {
    const val COLUMNS: Int = 6
    const val ROWS: Int = 5
    private const val COLUMNS_BLOCK_SHAPES = 3

    val padding = 10.dp
    val width = screenSize.screenWidthDp - padding * 2
    private val levelScore = screenSize.screenHeightDp / 100 * 20
    val height = screenSize.screenHeightDp - levelScore + padding * 2
    val border = 2.dp

    val fieldBrickWidth = round(this.width.value / ROWS).dp
    val fieldBrickHeight = round(this.height.value / (COLUMNS + COLUMNS_BLOCK_SHAPES)).dp


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
            width = fieldBrickWidth,
            height = fieldBrickHeight,
            position = Pair(positionColumn, positionRow),
            id = "$positionColumn $positionRow",
            color = colorsBricks.getValue(positionColumn),
            border = border
        )
    }

    private
    val matrixField = generateMatrix(true)

    private fun generateMatrix(
        matrixPrint: Boolean = false,


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
    val border: Dp = 0.dp,
    val borderColor: Color = Color.Black,
    var x: Dp = 0.dp,
    var y: Dp = 0.dp,
    var globalX: Dp = 0.dp,
    var globalY: Dp = 0.dp,
    var globalWidth: Dp = 0.dp,
    var globalHeight: Dp = 0.dp,
    val name: String = "FieldBricks",
    var width: Dp = 0.dp,
    var height: Dp = 0.dp,
    val id: String = "00",
    val color: Color = Color.Transparent,
    val position: Pair<Int, Int>,
) {
    fun addToCollision() {
        CollisionBricksOnLevel.addToCollision(this)
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width.dp
        this.globalHeight = coordinates.size.height.dp
        this.globalX = coordinates.positionInWindow().x.dp
        this.globalY = coordinates.positionInWindow().y.dp
    }
}