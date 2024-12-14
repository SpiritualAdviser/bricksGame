package com.example.bricksGame.ui

class LevelsConfig {

    val gameLevels: List<Level> = listOf(

        Level(
            number = 1,
            fieldGameRow = 2,
            fieldGameColumn = 2,
            numberOfScoreToWin = 20,
            numberOfBricksToWin = 0
        ),
        Level(
            number = 2,
            fieldGameRow = 2,
            fieldGameColumn = 3,
            numberOfScoreToWin = 30,
            numberOfBricksToWin = 2
        ),
        Level(
            number = 3,
            fieldGameRow = 3,
            fieldGameColumn = 3,
            numberOfScoreToWin = 40,
            numberOfBricksToWin = 0
        ),
        Level(
            number = 4,
            fieldGameRow = 3,
            fieldGameColumn = 4,
            numberOfScoreToWin = 50,
            numberOfBricksToWin = 3
        ),
        Level(
            number = 5,
            fieldGameRow = 4,
            fieldGameColumn = 4,
            numberOfScoreToWin = 60,
            numberOfBricksToWin = 0
        ),
        Level(
            number = 6,
            fieldGameRow = 4,
            fieldGameColumn = 5,
            numberOfScoreToWin = 70,
            numberOfBricksToWin = 4
        ),
        Level(
            number = 7,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            numberOfScoreToWin = 80,
            numberOfBricksToWin = 4
        ),
        Level(
            number = 8,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            numberOfScoreToWin = 90,
            numberOfBricksToWin = 0
        ),
        Level(
            number = 9,
            fieldGameRow = 6,
            fieldGameColumn = 7,
            numberOfScoreToWin = 100,
            numberOfBricksToWin = 4
        ),
        Level(
            number = 10,
            fieldGameRow = 7,
            fieldGameColumn = 8,
            numberOfScoreToWin = 110,
            numberOfBricksToWin = 0
        )
    )
}

data class Level(
    var number: Int,
    var fieldGameRow: Int,
    var fieldGameColumn: Int,
    var numberOfBricksToWin: Int = 0,

    var numberOfScoreToWin: Int,
    var levelTime: Int = 100,
    var bonusFillSpeed: Float = 0.01F,


    var numberLevelPasses: Int = 0,
    var isActive: Boolean = false,
)




