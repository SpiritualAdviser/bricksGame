package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.LevelLogic
import com.example.bricksGame.ui.theme.primaryContainerDark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor(
    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
    var soundController: SoundController,
    private var levelData: LevelData,
    private var levelLogic: LevelLogic

) : ViewModel() {

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
        brick.alpha.value = 0.02f
        brick.canDrag = false
        brick.activeBonusBorder.value = gameConfig.BRICK_BORDER_COLOR
    }

    private fun createBonus(i: Int): Brick {
        var name = ""
        when (i) {
            0 -> name = "iceBonus"
            1 -> name = "fireBonus"
            2 -> name = "hammerBonus"
        }
        val newBrick = Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            canDrag = false,
            id = 0,
            position = "Bonus",
            name = name,
            assetImage = gameConfig.imagesBricksBonuses[i],
            gameConfig = gameConfig,
            screenSize = screenSize,
            soundController = soundController,
            levelLogic = levelLogic

        )
        newBrick.borderColor = gameConfig.BRICK_BORDER_COLOR
        newBrick.activeBonusBorder = mutableStateOf(gameConfig.BRICK_BORDER_COLOR)
        return newBrick
    }

    private fun onBonusHammer(brick: Brick) {
        brick.hasBonusOwnerId?.onDragEnd()
    }

    private fun onBonusFire(brick: Brick) {
        val row =
            levelData.brickOnFields.filter { brick.hasBonusOwnerId?.position?.second == it.position.second }
        val winRow = mutableListOf<Pair<Int, Int>>()

        CoroutineScope(Dispatchers.Main).launch {

            row.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = gameConfig.imagesBricksBonuses[1]
                    winRow.add(it.position)
                }
            }
            delay(300)
            levelLogic.checkRoundOnBonus(winRow, onBonus = true)
        }
    }

    private fun onBonusIce(brick: Brick) {
        val column =
            levelData.brickOnFields.filter { brick.hasBonusOwnerId?.position?.first == it.position.first }
        val winColumn = mutableListOf<Pair<Int, Int>>()

        CoroutineScope(Dispatchers.Main).launch {
            column.forEach {

                if (it.hasOwnerId != null) {
                    it.assetImage.value = gameConfig.imagesBricksBonuses[0]
                    winColumn.add(it.position)
                }
            }
            delay(300)
            levelLogic.checkRoundOnBonus(winColumn, onBonus = true)
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

        gameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL.forEachIndexed { index, bonus ->

            val currentBonus = gameConfig.negativeBonuses[index]
            var breakerWhileLoop = 0

            for (number in 1..bonus) {
                var randomFieldBrick = getPlaceOnField()

                while (randomFieldBrick.hasOwnerId != null || breakerWhileLoop < levelData.brickOnFields.size) {
                    randomFieldBrick = getPlaceOnField()
                    ++breakerWhileLoop
                }
                val spriteName = currentBonus.spriteName
                if (spriteName != null) {

                    randomFieldBrick.setSpriteAnimations(spriteName)
                } else {
                    randomFieldBrick.setImageOnStickBrick(currentBonus.imageFullLife)
                }

                randomFieldBrick.id = currentBonus.imageFullLife.toString()
                randomFieldBrick.hasOwnerId = currentBonus.id
                randomFieldBrick.life = currentBonus.life
            }
        }
    }

    private fun getPlaceOnField(): FieldBrick {
        return levelData.brickOnFields.random()
    }

}


