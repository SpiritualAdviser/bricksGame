package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.theme.colors

val brick = Bricks()
val fieldBricks = FieldBricks()

class FieldBricks {
    val width = 400.dp
    val height = 400.dp
    val columns = (width / brick.width).toInt()
    val rows = (height / brick.height).toInt()
}

class Bricks {
    val width = 50.dp
    val height = 40.dp
}

fun main() {
    generateMatrix(fieldBricks.columns, fieldBricks.rows, true)
}

fun generateMatrix(columns: Int, rows: Int, print: Boolean = false): Array<Array<Int>> {
    val counter = 0
    val matrix = Array(columns) { Array<Int>(rows) { counter } }

    if (print) {
        matrix.forEach { columnValue ->

            columnValue.forEach { rowValue ->
                print("$rowValue \t")
            }
            println()
        }
    }
    return matrix
}

@Composable
fun SetBricksContent() {

    LazyRow {
        items(5) {
            Box(
                modifier = Modifier
                    .background(getRandomCollor())
                    .border(width = 1.dp, color = Color.Black)
                    .size(brick.width, brick.height)
            )
        }
    }
}

fun getRandomCollor(): Color {

    val currentColor = colors.random()
    return currentColor
}
