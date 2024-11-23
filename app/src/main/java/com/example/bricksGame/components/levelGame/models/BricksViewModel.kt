package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Brick
import com.example.bricksGame.ui.GameConfig

object BricksViewModel : ViewModel() {

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
        for (i in 0 until GameConfig.MAX_BRICKS_ON_LEVEL) {

            bricksList.add(createBrick())
        }
        return bricksList
    }

    private fun getRandomImage(): Int {
        return GameConfig.imagesBricks.values.random()
    }

    fun removeBrick(brick: Brick) {
        FieldViewModel.setBricksOnField(brick)
        brick.freeSpace()
        _bricksList.remove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        if (_bricksList.size <= GameConfig.MIN_BRICKS_TO_ADD_NEXT) {
            for (i in _bricksList.size until GameConfig.MAX_BRICKS_ON_LEVEL) {
                _bricksList.add(createBrick())
            }
        }
    }

    private fun createBrick(): Brick {
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            id = ++brickId,
            position = brickId.toString(),
            assetImage = getRandomImage()
        )
    }
}








