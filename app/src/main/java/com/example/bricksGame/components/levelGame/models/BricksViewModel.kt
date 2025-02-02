package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class BricksViewModel @Inject constructor(
    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
    var soundController: SoundController,

    ) : ViewModel() {

    private var brickId = 0
    private var _bricksList = createBricksList().toMutableStateList()

    val bricks
        get() = _bricksList

    fun resetData() {
        brickId = 0
        _bricksList.clear()
        _bricksList = createBricksList().toMutableStateList()
    }

    private fun createBricksList(): MutableList<Brick> {
        val bricksList: MutableList<Brick> = mutableListOf()
        for (i in 0 until gameConfig.MAX_BRICKS_ON_LEVEL) {

            bricksList.add(createBrick())
        }
        return bricksList
    }

    private fun getRandomImage(): Int {
        var maxColors: Int = 0
        maxColors = max(gameConfig.COLUMNS, gameConfig.ROWS)

        if (gameConfig.WIN_NUMBER_LINE == 0) maxColors else maxColors += 1

        if (gameConfig.imagesBricks.elementAtOrNull(maxColors) == null) {
            maxColors = gameConfig.imagesBricks.size - 1
        }
        return gameConfig.imagesBricks[(0..maxColors).random()]
    }

    fun removeBrick(brick: Brick) {
//        FieldViewModel.setBricksOnField(brick)
        _bricksList.remove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        if (_bricksList.size <= gameConfig.MIN_BRICKS_TO_ADD_NEXT) {
            for (i in _bricksList.size until gameConfig.MAX_BRICKS_ON_LEVEL) {
                soundController.pushCristal()
                _bricksList.add(createBrick())
            }
        }
    }

    private fun createBrick(): Brick {
        val newBrick = Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = ++brickId,
            position = brickId.toString(),
            assetImage = getRandomImage(),
        )
        newBrick.gameConfig = gameConfig
        newBrick.screenSize = screenSize
        newBrick.soundController = soundController

        newBrick.borderColor = gameConfig.BRICK_BORDER_COLOR
        newBrick.activeBonusBorder = mutableStateOf(gameConfig.BRICK_BORDER_COLOR)
        return newBrick
    }
}








