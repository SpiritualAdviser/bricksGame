package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.ui.GameConfig

object BonusViewModel : ViewModel() {

    private var _bonusList = createBonusList().toMutableStateList()

    val bonuses
        get() = _bonusList

    private fun createBonusList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()
        bricksList.add(createBonus(0))
        bricksList.add(createBonus(1))

        return bricksList
    }

    private fun createBonus(i: Int): Brick {
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = i,
            position = if (i == 0) {
                "fireBonus"
            } else {
                "hammerBonus"
            },
            name = "Bonus",
            assetImage = GameConfig.imagesBricksBonus[i]
        )
    }
}


