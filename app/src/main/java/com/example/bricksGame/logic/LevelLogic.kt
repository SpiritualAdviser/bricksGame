package com.example.bricksGame.logic

import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.helper.SoundController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelLogic @Inject constructor(
    private var levelData: LevelData,
    private var gameObjectBuilder: GameObjectBuilder,
    private var soundController: SoundController,
    private var buttonController: ButtonController
) {

    private var activeLevel: Level? = null
    private var levelRows = mutableListOf<List<PlaceOnField>>()
    private var levelColumns = mutableListOf<List<PlaceOnField>>()
    private var wasWin = false

    fun onStartLevel(level: Level) {
        activeLevel = level
        setRowsAndColumnsOnLevel(level)
    }

    private fun setRowsAndColumnsOnLevel(level: Level) {
        var column: List<PlaceOnField>
        var row: List<PlaceOnField>
        levelRows.clear()
        levelColumns.clear()

        for (index in 0 until level.fieldRow) {
            row = levelData.getPlacesOnFields().filter { index == it.position.second }
            levelRows.add(row)
        }

        for (index in 0 until level.fieldColumn) {
            column = levelData.getPlacesOnFields().filter { index == it.position.first }
            levelColumns.add(column)
        }
    }

    fun checkRound(gameObj: GameObjects, placeOnField: PlaceOnField) {

        val columnIndex = placeOnField.position.first
        val rowIndex = placeOnField.position.second

        val wonPlaces = mutableListOf<PlaceOnField>()

        when (val brick = placeOnField.slot.value) {
            is GameObjects.Brick -> {
                levelRows.getOrNull(rowIndex)?.let { row ->
                    wonPlaces.addAll(getWinningPlaces(row, brick, placeOnField))
                }

                levelColumns.getOrNull(columnIndex)?.let { column ->
                    wonPlaces.addAll(getWinningPlaces(column, brick, placeOnField))
                }
            }

            else -> return
        }

        onEndRound(wonPlaces)
    }

    private fun getWinningPlaces(
        linePlaces: List<PlaceOnField>,
        comparedSlot: GameObjects.Brick,
        placeOnField: PlaceOnField
    ): MutableList<PlaceOnField> {
        val wonPlaces = mutableListOf<PlaceOnField>()
        val closedPlaces = mutableListOf<PlaceOnField>()
        val startIndex = linePlaces.indexOfFirst { it.position == placeOnField.position }
        val numberWinLine = getNumberWinLine(linePlaces)

        goAhead(linePlaces, startIndex, comparedSlot, wonPlaces, closedPlaces, numberWinLine)
        goBack(linePlaces, startIndex, comparedSlot, wonPlaces, closedPlaces, numberWinLine)

        if (wonPlaces.size < numberWinLine) {
            wonPlaces.clear()
        } else {
            wonPlaces.addAll(closedPlaces)
            wasWin = true
        }
        return wonPlaces
    }

    private fun goAhead(
        linePlaces: List<PlaceOnField>,
        startIndex: Int,
        comparedSlot: GameObjects.Brick,
        wonPlaces: MutableList<PlaceOnField>,
        closedPlaces: MutableList<PlaceOnField>,
        numberWinLine: Int
    ) {
        for (index in startIndex until linePlaces.size) {

            when (val slot = linePlaces[index].slot.value) {
                is GameObjects.Brick -> {
                    if (slot.baseModel.assetImage == comparedSlot.baseModel.assetImage) {
                        wonPlaces.add(linePlaces[index])
                    } else {
                        if (wonPlaces.size < numberWinLine) {
                            wonPlaces.clear()
                        }
                        break
                    }
                }

                is GameObjects.Leaves -> {
                    closedPlaces.add(linePlaces[index])
                    break
                }

                is GameObjects.Rock -> {
                    closedPlaces.add(linePlaces[index])
                    break
                }

                is GameObjects.Bonus -> break
                is GameObjects.Empty -> break
            }
        }
        wonPlaces.size
        println()
    }

    private fun goBack(
        linePlaces: List<PlaceOnField>,
        startIndex: Int,
        comparedSlot: GameObjects.Brick,
        wonPlaces: MutableList<PlaceOnField>,
        closedPlaces: MutableList<PlaceOnField>,
        numberWinLine: Int
    ) {
        for (index in startIndex - 1 downTo 0) {
            when (val slot = linePlaces[index].slot.value) {
                is GameObjects.Brick -> {

                    if (slot.baseModel.assetImage == comparedSlot.baseModel.assetImage) {
                        wonPlaces.add(linePlaces[index])
                    } else {
                        if (wonPlaces.size < numberWinLine) {
                            wonPlaces.clear()
                        }
                        break
                    }
                }

                is GameObjects.Leaves -> {
                    closedPlaces.add(linePlaces[index])
                    break
                }

                is GameObjects.Rock -> {
                    closedPlaces.add(linePlaces[index])
                    break
                }

                is GameObjects.Bonus -> break
                is GameObjects.Empty -> break
            }
        }
        wonPlaces.size
        println()
    }

    private fun getNumberWinLine(linePlaces: List<PlaceOnField>): Int {
        var winLine = 0
        activeLevel?.numberOfBricksToWin?.let {
            winLine = if (it == 0 || it > linePlaces.size) {
                linePlaces.size
            } else {
                it
            }
        }
        return winLine
    }

    private fun onEndRound(wonPlaces: MutableList<PlaceOnField>) {
        if (wasWin) {
            onWin(wonPlaces)
        }
        wasWin = false
        checkEndLevel()
    }

    private fun onWin(wonPlaces: MutableList<PlaceOnField>) {
        soundController.winReel()
        wonPlaces.forEach { place ->
            var life = 1

            when (val slot = place.slot.value) {
                is GameObjects.Bonus -> return
                is GameObjects.Brick -> resetPlace(place)
                is GameObjects.Empty -> return
                is GameObjects.Leaves -> {
                    life = --slot.baseModel.life
                    when (life) {
                        0 -> {
                            slot.baseModel.startAnimation("destroy", false, place, needReset = true)
                            soundController.rustleOfLeaves()
                        }

                        else -> return
                    }
                }

                is GameObjects.Rock -> {
                    life = --slot.baseModel.life
                    when (life) {
                        0 -> {
                            slot.baseModel.startAnimation("destroy", false, place, needReset = true)
                            soundController.stoneDestroy()
                        }

                        1 -> {
                            slot.baseModel.startAnimation("crash")
                            soundController.stoneCrack()
                        }

                        else -> return
                    }
                }
            }
        }
    }

    private fun resetPlace(place: PlaceOnField) {
        val emptyPlace = gameObjectBuilder.getEmptyPlace()
        place.slot.value = emptyPlace
    }

    private fun checkEndLevel() {
//        var stepsOnLevel = MapModel.levelStep.intValue
        val fieldGameIsFull =
            levelData.getPlacesOnFields().all { it.slot.value !is GameObjects.Empty }

        if (fieldGameIsFull) {
            closeLevel()
        }

//        if (gameConfig.GAME_TYPE_FREE) {
//
//            if (noPlaceOnFieldGame) {
//                CoroutineScope(Dispatchers.Main).launch {
//                    closeLevel(false)
//                }
//            }
//            return
//        }
//
//        if (stepsOnLevel > 0 && !noPlaceOnFieldGame) {
//            if (checkWinLevelOrNot()) {
//
//                CoroutineScope(Dispatchers.Main).launch {
//                    closeLevel(checkWinLevelOrNot())
//                }
//            }
//
//        } else {
//
//            CoroutineScope(Dispatchers.Main).launch {
//                closeLevel(checkWinLevelOrNot())
//            }
//        }
    }

    fun closeLevel() {
        buttonController.navigateToHome()
    }


//    private fun checkWinLevelOrNot(): Boolean {
//        val currentLevel = currentLevel
//        return currentLevel != null && PlayerViewModel.playerScore.intValue >= currentLevel.numberOfScoreToWin
//    }

//    private fun popupOnWinLine(
//        megaWin: Boolean = false,
//        winningPositions: MutableList<Pair<Int, Int>>
//    ) {
//        var winLineSize = 0
//
//        winningPositions.forEach { position ->
//            val fieldBrick = levelData.brickOnFields.find { it.position == position }
//            fieldBrick?.let {
//                if (it.hasOwnerId != gameConfig.NEGATIVE_BONUS_LEAVES &&
//                    it.hasOwnerId != gameConfig.NEGATIVE_BONUS_ROCK
//                ) {
//                    ++winLineSize
//                }
//            }
//        }
//
//        val centerIndexPosition = winLineSize.div(2)
//        val rowDirection = winningPositions.first().first == winningPositions.last().first
//
//        val fieldBrick =
//            levelData.brickOnFields.find { it.position == winningPositions[centerIndexPosition] }
//
////        PopupsViewModel.onWinLine(megaWin, fieldBrick, winLineSize, rowDirection)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(500)
////            PopupsViewModel.closePopupOnWinLine()
//        }
//    }
//
//    private suspend fun closeLevel(onWin: Boolean) {
//        if (onWin) {
////            updatePlayerOnLevelWin()
//        }
////        PopupsViewModel.setImageOnLevelEnd(onWin)
////        delay(300)
////        PopupsViewModel.showPopupOnFinishGame()
////        delay(1800)
//
//        if (gameConfig.GAME_TYPE_FREE) {
////            fieldController.buttonController.navigateToHome()
//        } else {
////            fieldController.buttonController.navigateToMap()
//        }
//
//        delay(300)
////        PopupsViewModel.closePopupOnFinishGame()
//    }

}