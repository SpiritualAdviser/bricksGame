package com.example.bricksGame.logic

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableStateOf
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.Animation
import com.example.bricksGame.gameObjects.BaseModel
import com.example.bricksGame.gameObjects.BonusType
import com.example.bricksGame.gameObjects.Cords
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ScreenSize
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

@Singleton
class GameObjectBuilder @Inject constructor(
    @ApplicationContext val context: Context,
    private val screenSize: ScreenSize,
    private val gameConfig: GameConfig,
    private val levelData: LevelData,
) {
    private var brickId = 0L

    fun createPlacesOnFieldList(level: Level): MutableList<PlaceOnField> {
        val allPlacesOnField = level.fieldRow * level.fieldColumn
        val bricksList: MutableList<PlaceOnField> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allPlacesOnField) {

            if (i != 0 && i % level.fieldRow == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createPlace(positionColumn, positionRow)
            bricksList.add(fieldBrick)
            ++positionRow
        }
        println(bricksList.toString())
        return bricksList
    }

    fun getEmptyPlace(): GameObjects.Empty {
        return GameObjects.Empty(BaseModel(context))
    }

    private fun createPlace(positionColumn: Int, positionRow: Int): PlaceOnField {
        return PlaceOnField(
            position = Pair(positionColumn, positionRow),
            slot = mutableStateOf(GameObjects.Empty(BaseModel(context))),
            baseModel = BaseModel(context)
        )
    }

    fun createBricksList(level: Level): MutableList<GameObjects.Brick> {
        brickId = 0L
        val bricksList: MutableList<GameObjects.Brick> = mutableListOf()
        for (i in 0 until level.additionalBrick) {

            bricksList.add(createBrick(level))
        }
        return bricksList
    }

    fun updateBricksList(level: Level) {
        val size = levelData.getBricksList().size
        for (i in size until level.additionalBrick) {
            levelData.addToBricksList(createBrick(level))
        }
    }

    private fun createBrick(level: Level): GameObjects.Brick {
        val baseModel = BaseModel(context)
        baseModel.id = ++brickId
        val newBrick = GameObjects.Brick(
            baseModel = baseModel,
            cords = Cords(),
            animation = Animation(
                translationX = Animatable(initialValue = screenSize.screenWidthPx.toFloat()),
                translationY = Animatable(initialValue = screenSize.screenHeightPx.toFloat())
            )
        )

        newBrick.baseModel.assetImage = getRandomImage(level)
        return newBrick
    }

    private fun getRandomImage(level: Level): Int {
        var maxColors = max(level.fieldColumn, level.fieldRow)

        if (level.numberOfBricksToWin == 0) maxColors else maxColors += 1

        if (gameConfig.imagesBricks.elementAtOrNull(maxColors) == null) {
            maxColors = gameConfig.imagesBricks.size - 1
        }
//       maxColors = 2

        return gameConfig.imagesBricks[(0..maxColors).random()]
    }

    fun createBonusList(): MutableList<GameObjects.Bonus> {
        val bonusList: MutableList<GameObjects.Bonus> = mutableListOf()
        bonusList.add(createBonus(0))
        bonusList.add(createBonus(1))
        bonusList.add(createBonus(2))
        return bonusList
    }

    private fun createBonus(i: Int): GameObjects.Bonus {
        val newBonus =
            GameObjects.Bonus(baseModel = BaseModel(context = context), Cords(), BonusType())
        when (i) {
            0 -> {
                newBonus.bonusType.ise = true
                newBonus.baseModel.assetImage = gameConfig.imagesBricksBonuses[0]
            }

            1 -> {
                newBonus.bonusType.fire = true
                newBonus.baseModel.assetImage = gameConfig.imagesBricksBonuses[1]
            }

            2 -> {
                newBonus.bonusType.hammer = true
                newBonus.baseModel.assetImage = gameConfig.imagesBricksBonuses[2]
            }
        }

        val min = 0.001F
        val max = 0.7F
        newBonus.baseModel.alpha.value = ((Math.random() * (max - min) + min).toFloat())
        newBonus.baseModel.alpha.value=1F
        return newBonus
    }
}