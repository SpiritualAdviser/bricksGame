package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    val width = 70.dp
    val height = 60.dp
    private val padding = 5.dp
    private val maxBricks = 3
    val widthGridSize = (width + padding) * maxBricks + padding
    val heightGridSize = height + padding

    var bricksList = mutableStateListOf<Brick>()

    fun addItem() {
        if (bricksList.size < maxBricks) {
            bricksList.add(
                Brick(
                    width = width,
                    height = height,
                    id = bricksList.size,
                    position = (bricksList.size).toString(),
                    color = getRandomColor()
                )
            )
        }
    }

    fun deleteItem(index: Int) {
        if (bricksList.size != 0) {
            bricksList.removeAt(index)
        }
    }

    fun createBricksList(): Unit {
        if (bricksList.size < maxBricks) {

            for (i in bricksList.size until maxBricks) {
                bricksList.add(
                    Brick(
                        width = width,
                        height = height,
                        id = i,
                        position = i.toString(),
                        color = getRandomColor()
                    )
                )
            }
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







