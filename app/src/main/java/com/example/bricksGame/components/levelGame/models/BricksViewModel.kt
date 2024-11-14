package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

object BricksViewModel : ViewModel() {

    val width = 50.dp
    val height = 50.dp
    private val padding = 5.dp
    private const val MAX_BRICKS = 3
    val widthGridSize = (width + padding) * MAX_BRICKS + padding
    val heightGridSize = height + padding + 50.dp

    private val _bricksList = createBricksList().toMutableStateList()

    val bricks: MutableList<Brick>
        get() = _bricksList

    private fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()

        for (i in 0 until MAX_BRICKS) {
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
        return bricksList
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }
}

data class Brick(
    private var innerX: Dp = 0.dp,
    private var innerY: Dp = 0.dp,
    val width: Dp,
    val height: Dp,
    var id: Int,
    var position: String,
    var color: Color
) {
    var x by mutableStateOf(innerX)
    var y by mutableStateOf(innerY)
}







