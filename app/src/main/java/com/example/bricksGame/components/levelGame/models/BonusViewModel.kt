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
        bonusList.forEach {
            setOfBonus(it)
        }
        return bonusList
    }

    fun setAlpha(countAlpha: Float) {

        bonuses.forEach {
            if (it.alpha.value < 1) {
                it.alpha.value += countAlpha
            } else {
                it.canDrag = true
            }
        }
    }

    fun setOfBonus(brick: Brick) {
        brick.alpha.value = 0.05f
        brick.canDrag = false
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
            canDrag = false,
            id = 0,
            position = position,
            name = "Bonus",
            assetImage = GameConfig.imagesBricksBonus[i]
        )
    }
}


