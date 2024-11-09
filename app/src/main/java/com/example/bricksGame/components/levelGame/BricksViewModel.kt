package com.example.bricksGame.components.levelGame

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.theme.colorsBricks

open class BricksViewModel : ViewModel() {

    val width = 70.dp
    val height = 60.dp
    val padding = 5.dp
    val colorList = getColorList(3)

    private fun getColorList(numberItems: Int): MutableList<Color> {
        val listResult: MutableList<Color> = mutableListOf()

        for (i in 1..numberItems) {
            listResult.add(getRandomColor())
        }
        return listResult
    }

    private fun getRandomColor(): Color {
        val currentColor: Color = colorsBricks.values.random()
        return currentColor
    }
}





