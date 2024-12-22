package com.example.bricksGame.components.levelGame.models


import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel.brickOnField
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.components.levelGame.logic.LevelLogic
import com.example.bricksGame.ui.theme.primaryContainerDark
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
            if (it.alpha.value + countAlpha < 1) {
                it.alpha.value += countAlpha
            } else {
                it.alpha.value = 1f
                it.canDrag = true
                it.activeBonusBorder.value = primaryContainerDark
            }
        }
    }

    private fun setOfBonus(brick: Brick) {
        brick.alpha.value = 0.05f
        brick.canDrag = false
        brick.activeBonusBorder.value = GameConfig.BRICK_BORDER_COLOR
    }

    private fun createBonus(i: Int): Brick {
        var name = ""
        when (i) {
            0 -> name = "iceBonus"
            1 -> name = "fireBonus"
            2 -> name = "hammerBonus"
        }
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            canDrag = false,
            id = 0,
            position = "Bonus",
            name = name,
            assetImage = GameConfig.imagesBricksBonuses[i]
        )
    }

    private fun onBonusHammer(brick: Brick) {
        brick.hasBonusOwnerId?.onDragEnd()
    }

    private fun onBonusFire(brick: Brick) {
        val row =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.second == it.position.second }
        val winRow = mutableListOf<Pair<Int, Int>>()

        CoroutineScope(Dispatchers.Main).launch {

            row.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = GameConfig.imagesBricksBonuses[1]
                    winRow.add(it.position)
                }
            }
            delay(300)
            LevelLogic.checkRoundOnBonus(winRow, onBonus = true)
        }
    }

    private fun onBonusIce(brick: Brick) {
        val column =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.first == it.position.first }
        val winColumn = mutableListOf<Pair<Int, Int>>()

        CoroutineScope(Dispatchers.Main).launch {
            column.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = GameConfig.imagesBricksBonuses[0]
                    winColumn.add(it.position)
                }
            }
            delay(300)
            LevelLogic.checkRoundOnBonus(winColumn, onBonus = true)
        }
    }

    fun onBonus(brick: Brick) {

        when (brick.name) {
            "fireBonus" -> onBonusFire(brick)
            "hammerBonus" -> onBonusHammer(brick)
            "iceBonus" -> onBonusIce(brick)
        }
        setOfBonus(brick)
    }

    fun setNegativeBonusOnLevelField() {

        GameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL.forEachIndexed { index, bonus ->
            val currentBonus = GameConfig.negativeBonuses[index]

            for (number in 1..bonus) {
                val randomFieldBrick = brickOnField.random()

                randomFieldBrick.setImageOnStickBrick(currentBonus.imageFullLife)
                randomFieldBrick.id = currentBonus.imageFullLife.toString()
                randomFieldBrick.hasOwnerId = currentBonus.id
                randomFieldBrick.life = currentBonus.life
            }
        }
    }
}


