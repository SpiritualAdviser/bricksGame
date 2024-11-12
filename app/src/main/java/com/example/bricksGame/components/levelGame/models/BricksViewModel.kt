package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel: ViewModel() {

    val width = 70.dp
    val height = 60.dp
    private val padding = 5.dp
    private val numberBricks = 3
    val widthGridSize = (width + padding) * numberBricks + padding
    val heightGridSize = height + padding

    var bricksList = mutableStateListOf<Brick>()

    fun addItem() {
        bricksList.add(
            Brick(
                width = width,
                height = height,
                id = 0,
                position = "00",
                color = getRandomColor()
            )
        )
    }

    fun deleteItem(index: Int) {
        bricksList.removeAt(index)
    }
    fun createBricksList(): Unit {

        for (i in 1..numberBricks) {
            bricksList.add(
                Brick(
                    width = width,
                    height = height,
                    id = 0,
                    position = "00",
                    color = getRandomColor()
                )
            )
        }
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }
}

data class Brick(
    val width: Dp,
    val height: Dp,
    var id: Int,
    var position: String,
    var color: Color
)







