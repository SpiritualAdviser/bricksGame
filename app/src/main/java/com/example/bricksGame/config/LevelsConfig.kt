package com.example.bricksGame.config

import com.example.bricksGame.logic.LevelBuilder
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class LevelsConfig @Inject constructor(private var levelBuilder: LevelBuilder) {

    var levelGameList = mutableListOf<Level>()

    fun getNewLevelGameList(): MutableList<Level> {
        setLevelListOnCreatePlayer()
        return levelGameList
    }

    private fun setLevelListOnCreatePlayer() {
        levelGameList.clear()
        levelBuilder.setLevelGameList(levelGameList, gameFixLevels)
    }

    val gameFreeLevel: Level =
        Level(
            numberLevel = 1,
            fieldRow = 6,
            fieldColumn = 7,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(2, 3),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 0,
            levelMaxStep = 0
        )

    val levelsSurvival: List<Level> = listOf(
        Level(
            numberLevel = 1,
            fieldRow = 7,
            fieldColumn = 7,
            additionalBrick = 4,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 5,
            negativeBonuses = listOf<Int>(0, 0),
            bonusFillSpeed = 0.01f,
            numberOfScoreToWin = 0,
            levelMaxStep = 0
        ),
        Level(
            numberLevel = 1,
            fieldRow = 7,
            fieldColumn = 8,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 5,
            negativeBonuses = listOf<Int>(0, 0),
            bonusFillSpeed = 0.06f,
            numberOfScoreToWin = 0,
            levelMaxStep = 0
        )
    )

    private
    val gameFixLevels: List<Level> = listOf(

        Level(
            numberLevel = 1,
            fieldRow = 4,
            fieldColumn = 4,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(1, 0),
            bonusFillSpeed = 0.1f,
            numberOfScoreToWin = 30,
            levelMaxStep = 80
        ),
        Level(
            numberLevel = 2,
            fieldRow = 4,
            fieldColumn = 5,
            additionalBrick = 2,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(1, 1),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 60,
            levelMaxStep = 110
        ),
        Level(
            numberLevel = 3,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 3,
            lastBrickToAdd = 2,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(1, 1),
            bonusFillSpeed = 0.08f,
            numberOfScoreToWin = 60,
            levelMaxStep = 130
        ),
        Level(
            numberLevel = 4,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(3, 2),
            bonusFillSpeed = 0.03f,
            numberOfScoreToWin = 80,
            levelMaxStep = 100
        ),
        Level(
            numberLevel = 5,
            fieldRow = 5,
            fieldColumn = 5,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(3, 1),
            bonusFillSpeed = 0.1f,
            numberOfScoreToWin = 100,
            levelMaxStep = 140
        ),
        Level(
            numberLevel = 6,
            fieldRow = 4,
            fieldColumn = 5,
            additionalBrick = 3,
            lastBrickToAdd = 2,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(4, 2),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 70,
            levelMaxStep = 100
        ),
        Level(
            numberLevel = 7,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(3, 2),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 100,
            levelMaxStep = 140
        ),
        Level(
            numberLevel = 8,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 3,
            lastBrickToAdd = 1,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(3, 2),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 70,
            levelMaxStep = 100
        ),
        Level(
            numberLevel = 9,
            fieldRow = 6,
            fieldColumn = 7,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(2, 3),
            bonusFillSpeed = 0.01f,
            numberOfScoreToWin = 135,
            levelMaxStep = 150
        ),
        Level(
            numberLevel = 10,
            fieldRow = 7,
            fieldColumn = 7,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(5, 5),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 130,
            levelMaxStep = 125
        ),

        Level(
            numberLevel = 11,
            fieldRow = 6,
            fieldColumn = 7,
            additionalBrick = 4,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(3, 2),
            bonusFillSpeed = 0.01f,
            numberOfScoreToWin = 110,
            levelMaxStep = 130
        ),
        Level(
            numberLevel = 12,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 4,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(2, 1),
            bonusFillSpeed = 0.03f,
            numberOfScoreToWin = 60,
            levelMaxStep = 80
        ),

        Level(
            numberLevel = 13,
            fieldRow = 5,
            fieldColumn = 6,
            additionalBrick = 4,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(1, 2),
            bonusFillSpeed = 0.02f,
            numberOfScoreToWin = 70,
            levelMaxStep = 85
        ),
        Level(
            numberLevel = 14,
            fieldRow = 5,
            fieldColumn = 5,
            additionalBrick = 3,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(0, 3),
            bonusFillSpeed = 0.02f,
            numberOfScoreToWin = 60,
            levelMaxStep = 75
        ),
//        Level(
//            numberLevel = 15,
//            fieldGameRow = 5,
//            fieldGameColumn = 6,
//            additionalBrick = 4,
//            lastBrickToAdd = 2,
//            numberOfBricksToWin = 4,
//            negativeBonuses = listOf<Int>(2, 2),
//            bonusFillSpeed = 0.02f,
//            numberOfScoreToWin = 100,
//            levelMaxStep = 100
//        ),

//        15====

    )
}





