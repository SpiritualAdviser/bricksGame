package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.theme.colors

//fun main() {
//    generateMatrix(fieldBricks.columns, fieldBricks.rows, true)
//}

val brick = Bricks()
val fieldBricks = FieldBricks()
val matrixField = generateMatrix(fieldBricks.columns, fieldBricks.rows, true)

class FieldBricks {
    val width = 400.dp
    val height = 400.dp
    val columns = (height / brick.height).toInt()
    val rows = (width / brick.width).toInt()
    val padding = 5.dp
}

class Bricks {
    val width = 50.dp
    val height = 40.dp
}

@Composable
fun RunBricksComponent() {
    SetBricksContent()
}

@Composable
fun SetBricksContent() {

    Column {
        matrixField.forEach { _ ->
            LazyRow {
                items(fieldBricks.rows) {
                    Box(
                        modifier = Modifier
                            .background(getRandomCollor())
                            .border(width = 1.dp, color = Color.Black)
                            .size(brick.width, brick.height)
                    )
                }
            }
        }
    }
}

private fun generateMatrix(
    columns: Int,
    rows: Int,
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

private fun getRandomCollor(): Color {

    val currentColor = colors.random()
    return currentColor
}
