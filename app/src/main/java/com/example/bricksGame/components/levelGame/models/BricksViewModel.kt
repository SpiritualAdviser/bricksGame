package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.data.Brick

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
        for (i in 0 until MAX_BRICKS) {

            bricksList.add(createBrick())
        }
        return bricksList
    }

    private fun getRandomImage(): Int {
        return imagesBricks.values.random()
    }

    fun removeBrick(brick: Brick) {
        FieldViewModel.setBricksOnField(brick)
        brick.freeSpace()
        _bricksList.remove(brick)
        this.checkIfNeedNewBricksList()
    }

    private fun checkIfNeedNewBricksList() {
        if (_bricksList.size == 0) {
            for (i in 0 until MAX_BRICKS) {
                _bricksList.add(createBrick())
            }
        }
    }

    private fun createBrick(): Brick {
        return Brick(
            x = mutableIntStateOf(0),
            y = mutableIntStateOf(0),
            width = width,
            height = height,
            id = ++brickId,
            position = brickId.toString(),
            assetImage = getRandomImage()
        )
    }
}








