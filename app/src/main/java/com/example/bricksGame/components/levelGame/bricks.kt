package com.example.bricksGame.components.levelGame

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.theme.colors

open class Bricks {

    companion object {
        private var instance: Bricks? = null

        fun getInstance(needNewInstance: Boolean = false): Bricks {
            if (instance == null || needNewInstance)
                synchronized(Bricks::class.java) {

                    if (instance == null) {

                        println()
                        instance = Bricks()
                    }
                    if (needNewInstance) {
                        println()
                        instance = Bricks()
                    }

                }
            return requireNotNull(instance)
        }
    }

    val width = 80.dp
    val height = 60.dp
    val colorList = getColorlist(3)
    val fieldBricks = FieldBricks()
    val colorsListField = this.getColorlist(fieldBricks.columns * fieldBricks.rows)
    val matrixField = generateMatrix(fieldBricks.columns, fieldBricks.rows, true)

    inner class FieldBricks {
        val fieldWidth = 400.dp
        val fieldHeight = 400.dp
        val columns = (fieldHeight / height).toInt()
        val rows = (fieldWidth / width).toInt()
        val padding = 5.dp
    }

    fun getColorlist(numberItems: Int): MutableList<Color> {
        val listResult: MutableList<Color> = mutableListOf()
        for (i in 0..numberItems) {
            listResult.add(getRandomCollor())
        }
        return listResult
    }

    private fun getRandomCollor(): Color {
        val currentColor = colors.random()
        return currentColor
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
}


//fun main() {
//    val brick = Bricks()
//}

//
//fun gets(){
//    val brick = Bricks()
//    val fieldBricks = FieldBricks()
//    val matrixField = generateMatrix(fieldBricks.columns, fieldBricks.rows, true)
//}




