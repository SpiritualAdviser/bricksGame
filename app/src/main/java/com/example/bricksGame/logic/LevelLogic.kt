package com.example.bricksGame.logic

import com.example.bricksGame.components.players.repository.PlayerRepository
import com.example.bricksGame.components.popups.controller.PopupsController
import com.example.bricksGame.config.Level
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.gameObjects.GameObjects
import com.example.bricksGame.gameObjects.PlaceOnField
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.helper.SoundController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class LevelLogic @Inject constructor(
    private var levelData: LevelData,
    private var gameObjectBuilder: GameObjectBuilder,
    private var soundController: SoundController,
    private var buttonController: ButtonController,
    private var playerRepository: PlayerRepository,
    private var popupsController: PopupsController,
) {

    private var activeLevel: Level? = null
    private var levelRows = mutableListOf<List<PlaceOnField>>()
    private var levelColumns = mutableListOf<List<PlaceOnField>>()
    private var wasWinLine = false
    private var megaWin = false

    private var onChangeStage = false
    var canPlaySoundWin = true

    fun onStartLevel(level: Level) {
        activeLevel = level
        setRowsAndColumnsOnLevel(level)
        playerRepository.playerScore.intValue = 0
    }

    fun onChangeStageSurvivalMod(level: Level) {
        activeLevel = level
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

    fun checkRoundOnBonus(gameObj: GameObjects.Bonus, placeOnField: PlaceOnField) {

        if (gameObj.bonusType.ise) {
            val column =
                levelColumns.find { it.first().position.first == placeOnField.position.first }
            runBonusEffect(column, gameObj, placeOnField)
        }

        if (gameObj.bonusType.fire) {
            val row =
                levelRows.find { it.first().position.second == placeOnField.position.second }
            runBonusEffect(row, gameObj, placeOnField)
        }
        if (gameObj.bonusType.hammer) {
            resetPlace(placeOnField)
        }
    }

    private fun runBonusEffect(
        line: List<PlaceOnField>?,
        gameObj: GameObjects.Bonus,
        placeOnField: PlaceOnField
    ) {
        val wonPlaces = mutableListOf<PlaceOnField>()

        line?.let { isLine ->
            isLine.forEach { placeField ->

                when (placeField.slot.value) {
                    is GameObjects.Brick -> {
                        placeField.slot.value = gameObj
                        wonPlaces.add(placeField)
                    }

                    is GameObjects.Bonus -> wonPlaces.add(placeField)
                    is GameObjects.Leaves -> wonPlaces.add(placeField)
                    is GameObjects.Rock -> wonPlaces.add(placeField)
                    else -> {}
                }
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            wasWinLine = wonPlaces.isNotEmpty()
            onEndRound(wonPlaces, placeOnField)
        }
    }

    fun checkRound(
        placeOnField: PlaceOnField,
        isChangeStage: Boolean = false,
    ) {
        this.onChangeStage = isChangeStage

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

        onEndRound(wonPlaces, placeOnField)
    }

    private fun getWinningPlaces(
        linePlaces: List<PlaceOnField>,
        comparedSlot: GameObjects.Brick,
        placeOnField: PlaceOnField
    ): MutableList<PlaceOnField> {
        val wonPlaces = mutableListOf<PlaceOnField>()
        val closedPlaces = mutableListOf<PlaceOnField>()
        val startIndex = linePlaces.indexOfFirst { it.position == placeOnField.position }
        val numberWinLine = getNumberWinLine(linePlaces.size)

        goAhead(linePlaces, startIndex, comparedSlot, wonPlaces, closedPlaces, numberWinLine)
        goBack(linePlaces, startIndex, comparedSlot, wonPlaces, closedPlaces, numberWinLine)

        if (wonPlaces.size < numberWinLine) {
            wonPlaces.clear()
        } else {
            megaWin = wonPlaces.size > numberWinLine
            wonPlaces.addAll(closedPlaces)
            wasWinLine = true
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
                    val sprite = slot.baseModel.sprite
                    val comparedSprite = comparedSlot.baseModel.sprite

                    if (sprite != null && comparedSprite != null) {

                        if (sprite.imageSheet == comparedSprite.imageSheet) {
                            wonPlaces.add(linePlaces[index])
                        } else {
                            break
                        }

                    } else {

                        if (slot.baseModel.assetImage == comparedSlot.baseModel.assetImage) {
                            wonPlaces.add(linePlaces[index])
                        } else {
                            break
                        }

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
                    val sprite = slot.baseModel.sprite
                    val comparedSprite = comparedSlot.baseModel.sprite

                    if (sprite != null && comparedSprite != null) {

                        if (sprite.imageSheet == comparedSprite.imageSheet) {
                            wonPlaces.add(linePlaces[index])
                        } else {
                            break
                        }

                    } else {

                        if (slot.baseModel.assetImage == comparedSlot.baseModel.assetImage) {
                            wonPlaces.add(linePlaces[index])
                        } else {
                            break
                        }
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

    private fun getNumberWinLine(linePlaces: Int): Int {
        var winLine = 0
        activeLevel?.numberOfBricksToWin?.let {
            winLine = if (it == 0 || it > linePlaces) {
                linePlaces
            } else {
                it
            }
        }
        return winLine
    }

    private fun onEndRound(wonPlaces: MutableList<PlaceOnField>, placeOnField: PlaceOnField) {
        if (wasWinLine) {
            popupOnResetLine(placeOnField)
            onWinResetLine(wonPlaces)
            addScoreOnPlayer(wonPlaces.size)

            levelData.getBonusList().forEach { bonus ->

                activeLevel?.let {
                    val addAlpha = wonPlaces.size * it.bonusFillSpeed
                    val bonusAlpha = bonus.baseModel.alpha.value

                    if (bonusAlpha + addAlpha >= 1F) {
                        bonus.baseModel.alpha.value = 1F
                        bonus.baseModel.activeBorderColor.value = bonus.baseModel.hoverBorder
                    } else {
                        bonus.baseModel.alpha.value = bonusAlpha + addAlpha
                    }
                }
            }
        }
        wasWinLine = false

        if (levelData.freeGame) {
            if (!onChangeStage) {
                levelData.levelStep.intValue += 1
                levelData.emitSurvivalStageFlow(levelData.levelStep.intValue)
            }
        } else {
            levelData.levelStep.intValue -= 1
        }
        checkEndLevel()
    }

    private fun onWinResetLine(wonPlaces: MutableList<PlaceOnField>) {

        if (canPlaySoundWin) {
            soundController.winReel()
        }
        canPlaySoundWin = !onChangeStage

        wonPlaces.forEach { place ->
            var life = 1

            when (val slot = place.slot.value) {
                is GameObjects.Bonus -> resetPlace(place)
                is GameObjects.Brick -> {
                    val sprite = slot.baseModel.sprite

                    if (sprite != null) {

                        slot.baseModel.startAnimation("destroy", false, place, needReset = true)

                    } else {
                        resetPlace(place)
                    }
                }

                is GameObjects.Empty -> {}
                is GameObjects.Leaves -> {
                    life = --slot.baseModel.life
                    when (life) {
                        0 -> {
                            slot.baseModel.startAnimation("destroy", false, place, needReset = true)
                            soundController.rustleOfLeaves()
                        }

                        else -> {}
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

                        else -> {}
                    }
                }
            }
        }
    }

     fun resetPlace(place: PlaceOnField) {
        val emptyPlace = gameObjectBuilder.getEmptyPlace()
        place.slot.value = emptyPlace
    }

    private fun popupOnResetLine(placeOnField: PlaceOnField) {
        popupsController.onWinLine(megaWin, placeOnField)
        megaWin = false
    }

    private fun addScoreOnPlayer(size: Int) {
        val numberWinLine = getNumberWinLine(size)
        var overScore = 0

        if (size > numberWinLine) {
            overScore = (size - numberWinLine).toDouble().pow(2.0).toInt()
        }

        val score = size + overScore
        playerRepository.playerScore.intValue += score


        if (playerRepository.playerScore.intValue > playerRepository.playerAchievements.intValue) {
            playerRepository.playerAchievements.intValue = playerRepository.playerScore.intValue
            playerRepository.updateOnIncreaseAchievements()
        }

        if (levelData.freeGame) {
            return
        }

        levelData.levelTarget.intValue -= score
    }

    fun checkEndLevel() {

        val fieldGameIsFull =
            levelData.getPlacesOnFields().all { it.slot.value !is GameObjects.Empty }

        var stepOnLevel = levelData.levelStep.intValue
        val levelTarget = levelData.levelTarget.intValue
        var onLevelWin = levelTarget <= 0

        if (levelData.freeGame) {
            onLevelWin = false
            stepOnLevel=1
        }
        if (onLevelWin || fieldGameIsFull || stepOnLevel <= 0) {
            closeLevel(onLevelWin)
        }
    }

    private fun closeLevel(onLevelWin: Boolean) {

        if (onLevelWin && !levelData.freeGame) {
            activeLevel?.let {
                playerRepository.updatePlayerOnLevelWin(it)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            popupsController.showPopupOnFinishGame(onLevelWin)
            delay(1850)
            popupsController.closePopupOnFinishGame()
            delay(200)
            if (levelData.freeGame) {
                buttonController.navigateToHome()
            } else {
                buttonController.navigateToMap()
            }
        }
    }
}