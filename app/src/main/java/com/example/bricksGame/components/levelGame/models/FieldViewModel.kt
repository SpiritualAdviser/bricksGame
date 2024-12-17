package com.example.bricksGame.components.levelGame.models

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.Map.models.MapModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.components.levelGame.data.FieldBrick
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.screenSize
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.ButtonController
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel

object FieldViewModel : ViewModel() {

    const val EMPTY_ID = "Color.Transparent"
    var brickOnField = createBricksList()
    var numberOfCloseFieldBrickOnLine = 0

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp

    var fieldMAxWidthSize = if (screenSize.screenWidthDp > screenSize.screenHeightDp) {
        screenSize.screenHeightDp - GameConfig.PADDING_FIELD.dp
    } else {
        screenSize.screenWidthDp - GameConfig.PADDING_FIELD.dp
    }

    fun onOptionChange() {
        brickSizePortrait = fieldMAxWidthSize / GameConfig.ROWS
        brickSizeLandscape = fieldMAxWidthSize / GameConfig.COLUMNS

        if (brickSizePortrait > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizePortrait = GameConfig.MAX_BRICKS_SIZE.dp
        }

        if (brickSizeLandscape > GameConfig.MAX_BRICKS_SIZE.dp) {
            brickSizeLandscape = GameConfig.MAX_BRICKS_SIZE.dp
        }
    }

    fun resetData() {
        this.brickOnField.clear()
        CollisionBricksOnLevel.resetData()
        this.brickOnField = createBricksList()
    }

    private fun createBricksList(): MutableList<FieldBrick> {
        val allBrickOnField = GameConfig.ROWS * GameConfig.COLUMNS
        val bricksList: MutableList<FieldBrick> = mutableListOf()
        var positionColumn = 0
        var positionRow = 0

        for (i in 0 until allBrickOnField) {

            if (i != 0 && i % GameConfig.ROWS == 0) {
                ++positionColumn
                positionRow = 0
            }
            val fieldBrick = createBrick(positionColumn, positionRow)
            bricksList.add(fieldBrick)
            ++positionRow
        }
        println(bricksList.toString())
        return bricksList
    }

    private fun createBrick(positionColumn: Int, positionRow: Int): FieldBrick {
        return FieldBrick(
            position = Pair(positionColumn, positionRow),
        )
    }

    fun addToCollision() {
        brickOnField.forEach() {
            CollisionBricksOnLevel.addToCollision(fieldBrick = it)
        }
        runCollision()
    }

    private fun runCollision() {
        CollisionBricksOnLevel.runCollision(true)
    }

    fun setBricksOnField(brick: Brick) {
        val currentFieldBrick = brick.fieldBrickOnCollision
        currentFieldBrick?.setImageOnStickBrick(brick.assetImage)
        currentFieldBrick?.id = brick.assetImage.toString()
    }

    fun checkFieldOnFinishRound() {
        var column: List<FieldBrick>
        var row: List<FieldBrick>
        var itemsOnWin = mutableListOf<List<FieldBrick>>()

        brickOnField.forEachIndexed { index, _ ->
            if (index < GameConfig.ROWS) {
                row = brickOnField.filter { index == it.position.second }
                setWinLine(row, itemsOnWin)
            }

            if (index % GameConfig.ROWS == 0) {
                column = brickOnField.subList(index, index + GameConfig.ROWS)
                setWinLine(column, itemsOnWin)
            }
        }
        if (itemsOnWin.isNotEmpty()) {
            itemsOnWin.forEach {
                resetLineOnWin(it)
            }
            itemsOnWin.clear()
        }
    }

    private fun setWinLine(
        checkedList: List<FieldBrick>,
        itemsOnWin: MutableList<List<FieldBrick>>
    ) {
        var temporaryList = mutableListOf<FieldBrick>()
        var winList = mutableListOf<FieldBrick>()
        var winNumberBricks = GameConfig.WIN_NUMBER_LINE
        var startIndex = GameConfig.WIN_NUMBER_LINE - 1
        var endIndex = checkedList.size - GameConfig.WIN_NUMBER_LINE
        var wasWin = false

        winNumberBricks =
            if (checkedList.size < winNumberBricks || winNumberBricks == 0) checkedList.size else winNumberBricks

        startIndex =
            if (checkedList.elementAtOrNull(startIndex) != null) startIndex else checkedList.size - 1

        endIndex =
            if (startIndex <= endIndex && checkedList.elementAtOrNull(endIndex) != null) endIndex else startIndex

        numberOfCloseFieldBrickOnLine = 0

        for (i in startIndex..endIndex) {
            if (wasWin) {
                break
            }
            val currentBrick = checkedList[i]

            if (checkNeedToCompareBrick(currentBrick)) {

                checkedList.forEach {
                    if (needAddToList(currentBrick, it)) {
                        temporaryList.add(it)
                    } else {
                        if (!wasWin) {
                            wasWin = checkWin(temporaryList, winList, winNumberBricks)
                        }
                    }
                }
                if (!wasWin) {
                    wasWin = checkWin(temporaryList, winList, winNumberBricks)
                }
            }
        }
        if (winList.isNotEmpty()) {
            itemsOnWin.add(winList)
        }
    }

    fun needAddToList(currentBrick: FieldBrick, brick: FieldBrick): Boolean {
        if (GameConfig.WIN_LINE_DESTROY_NEGATIVE_BONUS && isClosedBrick(brick)
        ) {
            ++numberOfCloseFieldBrickOnLine
            return true
        } else {
            return currentBrick.id == brick.id && brick.id != EMPTY_ID
        }
    }

    fun checkNeedToCompareBrick(currentBrick: FieldBrick): Boolean {
        return if (isClosedBrick(currentBrick)) {
            false
        } else {
            currentBrick.id != EMPTY_ID
        }
    }

    fun isClosedBrick(fieldBrick: FieldBrick): Boolean {
        return fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_LIVES || fieldBrick.hasOwnerId == GameConfig.NEGATIVE_BONUS_ROCK
    }

    private fun checkWin(
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>,
        winNumberBricks: Int,
    ): Boolean {
        var isWin = false
        var sameColorList = mutableListOf<FieldBrick>()
        if (temporaryList.size >= (winNumberBricks + numberOfCloseFieldBrickOnLine)) {

            temporaryList.forEachIndexed { index, brick ->
                val start = index
                var end = index + winNumberBricks - 1

                if (!isClosedBrick(brick) && !isWin && temporaryList.getOrNull(end) != null) {

                    sameColorList = temporaryList.subList(start, end + 1)
                    val color = sameColorList[0].id

                    if (sameColorList.all { it.id == color }) {
                        needAddCloseBrickOrNot(temporaryList, winList, start, end)

                        sameColorList.forEach {
                            winList.add(it)
                        }
                        isWin = true
                    }
                }
            }

        } else {
            isWin = false
        }
        temporaryList.clear()
        return isWin
    }

    fun needAddCloseBrickOrNot(
        temporaryList: MutableList<FieldBrick>,
        winList: MutableList<FieldBrick>,
        start: Int,
        end: Int
    ) {
        val firstClose = temporaryList.getOrNull(start - 1)
        val lastClose = temporaryList.getOrNull(end + 1)

        if (firstClose != null) {
            winList.add(firstClose)
        }
        if (lastClose != null) {
            winList.add(lastClose)
        }
    }

    fun resetLineOnWin(lineList: List<FieldBrick>, onBonus: Boolean = false) {
        soundController.winReel()
        PlayerViewModel.addScore(lineList.size)
        if (!onBonus) {
            BonusViewModel.setAlpha(GameConfig.SPEED_OPEN_BONUS * lineList.size)
        }

        lineList.forEach { wonItem ->
            brickOnField.forEach {

                if (wonItem.position.toString() == it.position.toString()) {
                    resetOrNotFieldBrick(it)
                }
            }
        }
        if (!GameConfig.GAME_TYPE_FREE) {
            checkEndLevel()
        }
    }

    fun resetOrNotFieldBrick(fieldBrick: FieldBrick) {
        if (fieldBrick.life > 0) {
            fieldBrick.life -= 1
            fieldBrick.hasBonusOwnerId = null
        } else {
            fieldBrick.resetFieldBrick()
        }
        setImageOnField(fieldBrick)
    }

    fun setImageOnField(fieldBrick: FieldBrick) {

        when (fieldBrick.hasOwnerId) {
            GameConfig.NEGATIVE_BONUS_LIVES -> {
                fieldBrick.assetImage.value = GameConfig.imagesNegativeBonuses[0]
            }

            GameConfig.NEGATIVE_BONUS_ROCK -> {
                fieldBrick.assetImage.value = GameConfig.imagesNegativeBonuses[1]
            }

            else -> fieldBrick.assetImage.value = R.drawable.bgfielbrickempty
        }
    }

    fun checkEndLevel() {
        val currentLevel = MapModel.currentLevel

        if (currentLevel != null && PlayerViewModel.playerScore.intValue >= currentLevel.numberOfScoreToWin) {
            PlayerViewModel.updatePlayerOnLevelWin()

            ButtonController.navigateToMap()
        }
    }
}
