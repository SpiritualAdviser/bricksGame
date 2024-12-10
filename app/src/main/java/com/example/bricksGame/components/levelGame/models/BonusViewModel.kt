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
        val bonusList: MutableList<Brick> = mutableListOf()
        bonusList.add(createBonus(0))
        bonusList.add(createBonus(1))
        bonusList.add(createBonus(2))

        return bonusList
    }

    private fun createBonus(i: Int): Brick {
        var position = ""
        when (i) {

            0 -> position = "iceBonus"
            1 -> position = "fireBonus"
            2 -> position = "hammerBonus"

        }
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = i,
            position = position,
            name = "Bonus",
            assetImage = GameConfig.imagesBricksBonus[i]
        )
    }
}


