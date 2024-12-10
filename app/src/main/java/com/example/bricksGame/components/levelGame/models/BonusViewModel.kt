package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.components.levelGame.models.FieldViewModel.brickOnField
import com.example.bricksGame.ui.GameConfig
import com.example.compose.primaryContainerDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                it.activeBonusBorder.value = primaryContainerDark
            }
        }
    }

    fun setOfBonus(brick: Brick) {
        brick.alpha.value = 0.05f
        brick.canDrag = false
        brick.activeBonusBorder.value = GameConfig.BRICK_BORDER_COLOR
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

    fun onBonusHammer(brick: Brick) {
        brick.hasBonusOwnerId?.onDragEnd()
    }

    fun onBonusFire(brick: Brick) {
        val row =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.second == it.position.second }
        val winRow = mutableListOf<FieldBrick>()

        CoroutineScope(Dispatchers.IO).launch {

            row.forEach {
                it.assetImage.value = GameConfig.imagesBricksBonus[1]
                if (it.hasOwnerId != null) {
                    winRow.add(it)
                }
            }
            delay(300)
            FieldViewModel.resetLineOnWin(winRow, onBonus = true)
            row.forEach {
                it.resetFieldBrick()
            }
        }
    }

    fun onBonusIce(brick: Brick) {
        val column =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.first == it.position.first }
        val winColumn = mutableListOf<FieldBrick>()

        CoroutineScope(Dispatchers.IO).launch {
            column.forEach {
                it.assetImage.value = GameConfig.imagesBricksBonus[0]
                if (it.hasOwnerId != null) {
                    winColumn.add(it)
                }
            }
            delay(300)
            FieldViewModel.resetLineOnWin(winColumn, onBonus = true)
            column.forEach {
                it.resetFieldBrick()
            }
        }
    }

    fun onBonus(brick: Brick) {

        when (brick.position) {
            "fireBonus" -> onBonusFire(brick)
            "hammerBonus" -> onBonusHammer(brick)
            "iceBonus" -> onBonusIce(brick)
        }
        setOfBonus(brick)
    }
}


