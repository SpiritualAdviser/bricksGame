package com.example.bricksGame.ui

object LevelsConfig {

    val gameLevels: List<Level> = listOf(

        Level(
            numberLevel = 1,
            fieldGameRow = 2,
            fieldGameColumn = 2,
            numberOfScoreToWin = 5,
            additionalBrick = 2,
            numberOfBricksToWin = 0,
            negativeBonusNumber = 1,
            bonusFillSpeed = 0.5f
        ),
        Level(
            numberLevel = 2,
            fieldGameRow = 2,
            fieldGameColumn = 3,
            additionalBrick = 2,
            numberOfScoreToWin = 30,
            numberOfBricksToWin = 2,
            negativeBonusNumber = 1,
            bonusFillSpeed = 0.2f
        ),
        Level(
            numberLevel = 3,
            fieldGameRow = 3,
            fieldGameColumn = 3,
            additionalBrick = 3,
            numberOfScoreToWin = 40,
            numberOfBricksToWin = 0,
            negativeBonusNumber = 2,
            bonusFillSpeed = 0.2f
        ),
        Level(
            numberLevel = 4,
            fieldGameRow = 3,
            fieldGameColumn = 4,
            additionalBrick = 3,
            numberOfScoreToWin = 10,
            numberOfBricksToWin = 3,
            negativeBonusNumber = 2,
            bonusFillSpeed = 0.1f
        ),
        Level(
            numberLevel = 5,
            fieldGameRow = 4,
            fieldGameColumn = 4,
            additionalBrick = 3,
            numberOfScoreToWin = 60,
            numberOfBricksToWin = 0,
            negativeBonusNumber = 3,
            bonusFillSpeed = 0.1f
        ),
        Level(
            numberLevel = 6,
            fieldGameRow = 4,
            fieldGameColumn = 5,
            additionalBrick = 3,
            numberOfScoreToWin = 70,
            numberOfBricksToWin = 4,
            negativeBonusNumber = 3,
            bonusFillSpeed = 0.1f
        ),
        Level(
            numberLevel = 7,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            additionalBrick = 3,
            numberOfScoreToWin = 80,
            numberOfBricksToWin = 4,
            negativeBonusNumber = 4,
            bonusFillSpeed = 0.05f
        ),
        Level(
            numberLevel = 8,
            fieldGameRow = 5,
            fieldGameColumn = 6,
            additionalBrick = 3,
            numberOfScoreToWin = 90,
            numberOfBricksToWin = 0,
            negativeBonusNumber = 4,
            bonusFillSpeed = 0.05f
        ),
        Level(
            numberLevel = 9,
            fieldGameRow = 6,
            fieldGameColumn = 7,
            additionalBrick = 4,
            numberOfScoreToWin = 100,
            numberOfBricksToWin = 4,
            negativeBonusNumber = 4,
            bonusFillSpeed = 0.01f
        ),
        Level(
            numberLevel = 10,
            fieldGameRow = 7,
            fieldGameColumn = 8,
            additionalBrick = 4,
            numberOfScoreToWin = 110,
            numberOfBricksToWin = 0,
            negativeBonusNumber = 4,
            bonusFillSpeed = 0.01f
        )
    )
}

data class Level(
    var numberLevel: Int,
    var fieldGameRow: Int,
    var fieldGameColumn: Int,
    var numberOfBricksToWin: Int = 0,

    var numberOfScoreToWin: Int,
    var levelTime: Int = 100,
    var bonusFillSpeed: Float = 0.01F,
    var additionalBrick: Int = 3,
    var negativeBonusNumber: Int = 0,

    var numberLevelPasses: Int = 0,
    var isActive: Boolean = false,
)




