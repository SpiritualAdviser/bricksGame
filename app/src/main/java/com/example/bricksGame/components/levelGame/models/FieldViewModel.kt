package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.screenSize
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import kotlin.math.round

object FieldViewModel : ViewModel() {
    const val COLUMNS: Int = 6
    const val ROWS: Int = 5
    private const val COLUMNS_BLOCK_SHAPES = 3
    private const val MAX_FIELD_BRICKS = COLUMNS * ROWS
    val padding = 10.dp
    val width = screenSize.screenWidthDp - padding * 2
    private val levelScore = screenSize.screenHeightDp / 100 * 20
    val height = screenSize.screenHeightDp - levelScore + padding * 2
    val border = 2.dp

    val fieldBrickWidth = round(this.width.value / ROWS).dp
    val fieldBrickHeight = round(this.height.value / (COLUMNS + COLUMNS_BLOCK_SHAPES)).dp

    var brickOnField = createBricksList()

    fun resetData() {
        this.brickOnField.clear()
        CollisionBricksOnLevel.resetData()
        this.brickOnField = createBricksList()
    }

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = MAX_FIELD_BRICKS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % 5 == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createBrick(positionColumn, positionRow)
            bricksList.add(fieldBrick)

            addToCollision(fieldBrick)

            ++positionRow
        }
        runCollision()
        println(bricksList.toString())
        return bricksList
    }

    private fun createBrick(positionColumn: Int, positionRow: Int): FieldBrick {
        return FieldBrick(
            width = fieldBrickWidth,
            height = fieldBrickHeight,
            position = Pair(positionColumn, positionRow),
            id = "$positionColumn $positionRow",
            border = border
        )
    }

    private fun addToCollision(fieldBrick: FieldBrick) {
        CollisionBricksOnLevel.addToCollision(fieldBrick = fieldBrick)
    }

    private fun runCollision() {
        CollisionBricksOnLevel.runCollision(true)
    }
}
