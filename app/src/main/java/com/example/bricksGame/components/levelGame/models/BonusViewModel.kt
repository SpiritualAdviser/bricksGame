package com.example.bricksGame.components.levelGame.models


import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
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
            if (it.alpha.value + countAlpha < 1) {
                it.alpha.value += countAlpha
            } else {
                it.alpha.value = 1f
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

    fun onBonusHammer(brick: Brick) {
        brick.hasBonusOwnerId?.onDragEnd()
    }

    fun onBonusFire(brick: Brick) {
        val row =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.second == it.position.second }
        val winRow = mutableListOf<FieldBrick>()

        CoroutineScope(Dispatchers.Main).launch {

            row.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = GameConfig.imagesBricksBonuses[1]
                    winRow.add(it)
                }
            }
            delay(300)
            FieldViewModel.resetLineOnWin(winRow, onBonus = true)
        }
    }

    fun onBonusIce(brick: Brick) {
        val column =
            brickOnField.filter { brick.hasBonusOwnerId?.position?.first == it.position.first }
        val winColumn = mutableListOf<FieldBrick>()

        CoroutineScope(Dispatchers.Main).launch {
            column.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = GameConfig.imagesBricksBonuses[0]
                    winColumn.add(it)
                }
            }
            delay(300)
            FieldViewModel.resetLineOnWin(winColumn, onBonus = true)
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

        for (i in 0..GameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL) {
            val randomFieldBrick = brickOnField.random()

            val randomNegativeBonus =
                createNegativeBonus((Math.random() * GameConfig.imagesNegativeBonuses.size).toInt())

            randomFieldBrick.setImageOnStickBrick(randomNegativeBonus.assetImage)
            randomFieldBrick.id = randomNegativeBonus.assetImage.toString()
            randomFieldBrick.hasOwnerId = randomNegativeBonus.id
            randomFieldBrick.life = randomNegativeBonus.life
            println()
        }
    }

    private fun createNegativeBonus(i: Int): Brick {
        var name = ""
        var id = 0
        var life = 0
        when (i) {
            0 -> {
                name = "negativeLives"
                id = GameConfig.NEGATIVE_BONUS_LIVES
                life = GameConfig.NEGATIVE_BONUS_LIVES_LIFE
            }

            1 -> {
                name = "negativeRock"
                id = GameConfig.NEGATIVE_BONUS_ROCK
                life = GameConfig.NEGATIVE_BONUS_ROCK_LIFE
            }
        }
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            life = life,
            canDrag = false,
            id = id,
            position = "negativeBonus",
            name = name,
            assetImage = GameConfig.imagesNegativeBonuses[i]
        )
    }


}


