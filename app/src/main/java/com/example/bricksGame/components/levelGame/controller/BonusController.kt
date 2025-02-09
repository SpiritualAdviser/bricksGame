package com.example.bricksGame.components.levelGame.controller

import android.content.Context
import android.util.Log
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.lifecycle.viewModelScope
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.Cords
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.logic.CollisionOnLevel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

class BonusController @Inject constructor(
//    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
//    var soundController: SoundController,
//    private var levelData: LevelData,
//    private var levelLogic: LevelLogic,
//    private var bricksController: BricksController,
    private var collisionOnLevel: CollisionOnLevel,
    @ApplicationContext val context: Context
) {

    init {
        Log.d("my", "BonusController_init")
    }

    fun createBonusList(): MutableList<GameObjects.Bonus> {
        val bonusList: MutableList<GameObjects.Bonus> = mutableListOf()
        bonusList.add(createBonus(0))
        bonusList.add(createBonus(1))
        bonusList.add(createBonus(2))
        return bonusList
    }

    private fun createBonus(i: Int): GameObjects.Bonus {
        val newBonus = GameObjects.Bonus(baseModel = BaseModel(context = context), Cords())
        newBonus.baseModel.assetImage = gameConfig.imagesBricksBonuses[i]

        return newBonus
    }

    fun observeCenterObjects(bonus: GameObjects) {
        collisionOnLevel.observeCenterObjects(bonus)
    }

    fun onDragEnd() {
        collisionOnLevel.outOfField()
    }

//    fun setAlpha(countAlpha: Float) {
//
//        levelData._bonusList.forEach {
//            if (it.alpha.value + countAlpha < 1) {
//                it.alpha.value += countAlpha
//            } else {
//                it.alpha.value = 1f
//                it.canDrag = true
//                it.activeBonusBorder.value = primaryContainerDark
//            }
//        }
//    }

//    private fun setOfBonus(brick: Brick) {
//        brick.alpha.value = 0.02f
//        brick.canDrag = false
//        brick.activeBonusBorder.value = gameConfig.BRICK_BORDER_COLOR
//    }

//    private fun onBonusHammer(brick: Brick) {
//        brick.hasBonusOwnerId?.onDragEnd()
//    }
//
//    private fun onBonusFire(brick: Brick) {
//        val row =
//            levelData.brickOnFields.filter { brick.hasBonusOwnerId?.position?.second == it.position.second }
//        val winRow = mutableListOf<Pair<Int, Int>>()
//
//        CoroutineScope(Dispatchers.Main).launch {
//
//            row.forEach {
//
//                if (it.hasOwnerId != null) {
//                    it.assetImage.value = gameConfig.imagesBricksBonuses[1]
//                    winRow.add(it.position)
//                }
//            }
//            delay(300)
////            levelLogic.checkRoundOnBonus(winRow, onBonus = true)
//        }
//    }
//
//    private fun onBonusIce(brick: Brick) {
//        val column =
//            levelData.brickOnFields.filter { brick.hasBonusOwnerId?.position?.first == it.position.first }
//        val winColumn = mutableListOf<Pair<Int, Int>>()
//
//        CoroutineScope(Dispatchers.Main).launch {
//            column.forEach {
//
//                if (it.hasOwnerId != null) {
//                    it.assetImage.value = gameConfig.imagesBricksBonuses[0]
//                    winColumn.add(it.position)
//                }
//            }
//            delay(300)
////            levelLogic.checkRoundOnBonus(winColumn, onBonus = true)
//        }
//    }
//
//    fun onBonus(brick: Brick) {
//
//        when (brick.name) {
//            "fireBonus" -> onBonusFire(brick)
//            "hammerBonus" -> onBonusHammer(brick)
//            "iceBonus" -> onBonusIce(brick)
//        }
//        setOfBonus(brick)
//    }
//
//    fun setNegativeBonusOnLevelField() {
//
//        gameConfig.MAX_NEGATIVE_BRICKS_ON_LEVEL.forEachIndexed { index, bonus ->
//
//            val currentBonus = gameConfig.negativeBonuses[index]
//            var breakerWhileLoop = 0
//
//            for (number in 1..bonus) {
//                var randomFieldBrick = getPlaceOnField()
//
//                while (randomFieldBrick.hasOwnerId != null || breakerWhileLoop < levelData.brickOnFields.size) {
//                    randomFieldBrick = getPlaceOnField()
//                    ++breakerWhileLoop
//                }
//                val spriteName = currentBonus.spriteName
//                if (spriteName != null) {
//
//                    randomFieldBrick.setSpriteAnimations(spriteName)
//                } else {
//                    randomFieldBrick.setImageOnStickBrick(currentBonus.imageFullLife)
//                }
//
//                randomFieldBrick.id = currentBonus.imageFullLife.toString()
//                randomFieldBrick.hasOwnerId = currentBonus.id
//                randomFieldBrick.life = currentBonus.life
//            }
//        }
//    }
//
//    private fun getPlaceOnField(): FieldBrick {
//        return levelData.brickOnFields.random()
//    }
}