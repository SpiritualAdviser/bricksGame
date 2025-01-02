package com.example.bricksGame.config

import com.example.bricksGame.logic.LevelBuilder

object LevelsConfig {

    var levelGameList = mutableListOf<Level>()

    fun setLevelListOnCreatePlayer() {
        levelGameList.clear()
        LevelBuilder().getLevelGameList(levelGameList)
    }

    val gameFreeLevel: Level =
        Level(
            numberLevel = 1,
            fieldGameRow = 7,
            fieldGameColumn = 7,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 2,
            negativeBonuses = listOf<Int>(5, 5),
            bonusFillSpeed = 0.05f,
            numberOfScoreToWin = 0,
            levelMaxStep = 0
        )

    val gameFixLevels: List<Level> = listOf(

        Level(
            numberLevel = 1,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            additionalBrick = 3,
            lastBrickToAdd = 0,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(0, 0),
            bonusFillSpeed = 0.1f,
            numberOfScoreToWin = 40,
            levelMaxStep = 150
        ),
        Level(
            numberLevel = 2,
            fieldGameRow = 4,
            fieldGameColumn = 5,
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
            fieldGameRow = 5,
            fieldGameColumn = 6,
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
            fieldGameRow = 5,
            fieldGameColumn = 6,
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
            fieldGameRow = 5,
            fieldGameColumn = 5,
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
            fieldGameRow = 4,
            fieldGameColumn = 5,
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
            fieldGameRow = 5,
            fieldGameColumn = 6,
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
            fieldGameRow = 5,
            fieldGameColumn = 6,
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
            fieldGameRow = 6,
            fieldGameColumn = 7,
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
            fieldGameRow = 7,
            fieldGameColumn = 7,
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
            fieldGameRow = 6,
            fieldGameColumn = 7,
            additionalBrick = 4,
            numberOfBricksToWin = 4,
            negativeBonuses = listOf<Int>(3, 2),
            bonusFillSpeed = 0.01f,
            numberOfScoreToWin = 110,
            levelMaxStep = 130
        ),
        Level(
            numberLevel = 12,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            additionalBrick = 4,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(2, 1),
            bonusFillSpeed = 0.03f,
            numberOfScoreToWin = 60,
            levelMaxStep = 80
        ),

        Level(
            numberLevel = 13,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            additionalBrick = 4,
            numberOfBricksToWin = 3,
            negativeBonuses = listOf<Int>(1, 2),
            bonusFillSpeed = 0.02f,
            numberOfScoreToWin = 70,
            levelMaxStep = 85
        ),
        Level(
            numberLevel = 14,
            fieldGameRow = 5,
            fieldGameColumn = 5,
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





