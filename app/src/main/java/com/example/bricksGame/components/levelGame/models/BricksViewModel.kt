package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.data.Brick

object BricksViewModel : ViewModel() {

    private val imagesBricks: Map<Int, Int> = mapOf(

        0 to R.drawable.red_brick,
        1 to R.drawable.blue_brick,
        2 to R.drawable.green_brick,
        3 to R.drawable.purple_brick,
        4 to R.drawable.orange_brick,
        5 to R.drawable.pink_brick,
        6 to R.drawable.dark_blue_brick,
        7 to R.drawable.yellow_brick,

//        5 to R.drawable.bronze_brick,
//        6 to R.drawable.dark_brick,
//        7 to R.drawable.gold_brick,
    )

    private val width = FieldViewModel.fieldBrickWidth
    private val height = FieldViewModel.fieldBrickHeight

    private const val MAX_BRICKS = 3
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








