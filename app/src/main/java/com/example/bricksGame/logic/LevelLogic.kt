package com.example.bricksGame.logic

/*
import com.example.bricksGame.components.map.models.MapModel.currentLevel
*/
//import com.example.bricksGame.components.players.models.PlayerViewModel.updatePlayerOnLevelWin
import android.util.Log
import com.example.bricksGame.components.levelGame.controller.BricksController
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.components.levelGame.controller.FieldController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelLogic @Inject constructor(
    var gameConfig: GameConfig,
//    var playerRepository: PlayerRepository,
    private var buttonController: ButtonController,
    private var levelData: LevelData,
    private var fieldController: FieldController,
    private var bricksController: BricksController,
) {

    init {
        Log.d("my", "LevelLogic_init")
    }

    val EMPTY_ID = "Color.Transparent"
    var wasWin = false

    private var activeLevel: Level? = null

    private var levelRows = mutableListOf<List<PlaceOnField>>()
    private var levelColumns = mutableListOf<List<PlaceOnField>>()

    fun onStartLevel(level: Level) {
        activeLevel = level
        activeLevel?.let {
            createLevelResources(it)
            setRowsAndColumnsOnLevel(it)
            goToLevel()
        }
        println()
//        fieldController.onOptionChange()
//        fieldController.resetData()
//        bricksControllerInstance.resetData()
//        bonusControllerInstance.setNegativeBonusOnLevelField()
//        setRowsAndColumnOnLevel()
//        fieldController.addToCollision()

    }

    private fun createLevelResources(level: Level) {
        levelData.setActiveLevel(level)

        val placesOnField = fieldController.createPlacesOnFieldList(level)
        val bricksOnLevel = bricksController.createBricksList(level)

        levelData.setPlacesOnField(placesOnField)
        levelData.setBricksList(bricksOnLevel)
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
        println()
    }

//    fun checkRoundOnBonus(winningPositions: MutableList<Pair<Int, Int>>, onBonus: Boolean) {
//        onEndRound(winningPositions, onBonus)
//    }

//    fun checkRound(brick: Brick) {
//        val columnIndex = brick.fieldBrickOnCollision?.position?.first
//        val rowIndex = brick.fieldBrickOnCollision?.position?.second
//        val winningPositions = mutableListOf<Pair<Int, Int>>()
//
//        rowIndex?.run {
//            levelRows.getOrNull(rowIndex)?.run {
//                winningPositions.addAll(getWinningPositions(this))
//            }
//        }
//
//        columnIndex?.run {
//            levelColumns.getOrNull(columnIndex)?.run {
//                winningPositions.addAll(getWinningPositions(this))
//            }
//        }
//
//        onEndRound(winningPositions)
//    }

//    private fun onEndRound(
//        winningPositions: MutableList<Pair<Int, Int>>,
//        onBonus: Boolean = false
//    ) {
//        if (winningPositions.isNotEmpty()) {
//
//            onWin(winningPositions, onBonus)
//        }
//        checkEndLevel()
//    }

//    private fun onWin(winningPositions: MutableList<Pair<Int, Int>>, onBonus: Boolean) {
//        soundController.winReel()
//        addScore(winningPositions)
//        resetLineOnWin(winningPositions, onBonus)
//    }

//    private fun getWinningPositions(bricks: List<FieldBrick>): MutableList<Pair<Int, Int>> {
//        var winIndexesOnLine = listOf<Int>()
//        var indexesOnField: MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>()
//
//        val numberWinLine = getNumberWinLine(bricks)
//        var startIndex = numberWinLine - 1
//        var endIndex = getEndIndex(bricks, startIndex, numberWinLine)
//        wasWin = false
//
//        for (index in startIndex..endIndex) {
//            if (!wasWin) {
//                bricks.getOrNull(index)?.let { brick ->
//                    if (!isClosedBrick(brick) && brick.id != EMPTY_ID) {
//                        winIndexesOnLine = runComparator(brick, bricks, numberWinLine)
//                    }
//                }
//            }
//        }
//
//        winIndexesOnLine.forEach {
//            indexesOnField.add(bricks[it].position)
//        }
//        return indexesOnField
//    }
//
//    private fun runComparator(
//        brick: FieldBrick,
//        bricks: List<FieldBrick>,
//        numberWinLine: Int,
//    ): MutableList<Int> {
//        val indexList = mutableListOf<Int>()
//
//        bricks.forEachIndexed { index, fieldBrick ->
//            if (!wasWin) {
//                if (brick.id == fieldBrick.id) {
//                    indexList.add(index)
//                } else {
//                    wasWin = checkWin(indexList, numberWinLine)
//                }
//            }
//        }
//        wasWin = checkWin(indexList, numberWinLine)
//
//        if (wasWin) {
//            getClosedFieldBricks(indexList, bricks)
//        }
//
//        return indexList
//    }
//
//    private fun checkWin(indexList: MutableList<Int>, numberWinLine: Int): Boolean {
//        return if (indexList.size >= numberWinLine) {
//            true
//        } else {
//            indexList.clear()
//            false
//        }
//    }
//
//    private fun getEndIndex(bricks: List<FieldBrick>, startIndex: Int, numberWinLine: Int): Int {
//        return if (bricks.size - numberWinLine <= startIndex) {
//            startIndex
//        } else {
//            bricks.size - numberWinLine
//        }
//    }
//
//    private fun getNumberWinLine(bricks: List<FieldBrick>): Int {
//        var winLine = 0
//        levelData.currentLevel?.numberOfBricksToWin?.let {
//            if (it == 0 || it > bricks.size) {
//                winLine = bricks.size
//            } else {
//                winLine = it
//            }
//        }
//        return winLine
//    }
//
//    private fun isClosedBrick(fieldBrick: FieldBrick): Boolean {
//        return fieldBrick.hasOwnerId == gameConfig.NEGATIVE_BONUS_LEAVES || fieldBrick.hasOwnerId == gameConfig.NEGATIVE_BONUS_ROCK
//    }
//
//    private fun getClosedFieldBricks(indexList: MutableList<Int>, bricks: List<FieldBrick>) {
//        val firstIndexBrick = indexList.first() - 1
//        val lastIndexBrick = indexList.last() + 1
//
//        bricks.getOrNull(firstIndexBrick)?.run {
//            if (isClosedBrick(bricks[firstIndexBrick])) {
//                indexList.add(firstIndexBrick)
//            }
//        }
//
//        bricks.getOrNull(lastIndexBrick)?.run {
//            if (isClosedBrick(bricks[lastIndexBrick])) {
//                indexList.add(lastIndexBrick)
//            }
//        }
//    }

//    private fun resetLineOnWin(
//        winningPositions: MutableList<Pair<Int, Int>>,
//        onBonus: Boolean = false
//    ) {
//        if (!onBonus) {
////            bonusControllerInstance.setAlpha(gameConfig.SPEED_OPEN_BONUS * winningPositions.size)
//        }
//
//        winningPositions.forEach { winPosition ->
//
//            levelData.brickOnFields.find { it.position == winPosition }?.let {
//                onResetFieldBrick(it)
//            }
//        }
//    }

//    private fun addScore(winningPositions: MutableList<Pair<Int, Int>>) {
//        var score = winningPositions.size
//        val numberWin =
//            if (gameConfig.WIN_NUMBER_LINE == 0) winningPositions.size else gameConfig.WIN_NUMBER_LINE
//
//        var overBonus = winningPositions.size - (numberWin - 1)
//
//        if (overBonus > 0) {
////            PlayerViewModel.addScore(numberWin * overBonus)
//
//            if (score > numberWin) {
//                popupOnWinLine(true, winningPositions)
//            } else {
//                popupOnWinLine(false, winningPositions)
//            }
//
//        } else {
////            PlayerViewModel.addScore(score)
//            popupOnWinLine(false, winningPositions)
//
//        }
//    }
//
//    private fun onResetFieldBrick(fieldBrick: FieldBrick) {
//        if (fieldBrick.life > 0) {
//            --fieldBrick.life
//            fieldBrick.hasBonusOwnerId = null
//        }
////        fieldController.checkNeedChangeAsset(fieldBrick)
//    }

//    private fun checkEndLevel() {
////        var stepsOnLevel = MapModel.levelStep.intValue
//        val noPlaceOnFieldGame = levelData.brickOnFields.all { it.id != EMPTY_ID }
//
//        if (gameConfig.GAME_TYPE_FREE) {
//
//            if (noPlaceOnFieldGame) {
//                CoroutineScope(Dispatchers.Main).launch {
//                    closeLevel(false)
//                }
//            }
//            return
//        }

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
//}

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

    fun goToLevel() {
        buttonController.navigateToLevelGame()
    }

    fun goToHome() {
        buttonController.navigateToHome()
    }
}