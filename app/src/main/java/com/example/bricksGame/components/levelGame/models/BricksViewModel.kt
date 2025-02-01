//package com.example.bricksGame.components.levelGame.models
//
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.toMutableStateList
//import androidx.lifecycle.ViewModel
//import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
//import com.example.bricksGame.config.GameConfig
//import com.example.bricksGame.screenSize
//import kotlin.math.max
//
//object BricksViewModel : ViewModel() {
//
//    private var brickId = 0
//    private var _bricksList = createBricksList().toMutableStateList()
//
//    val bricks
//        get() = _bricksList
//
//    fun resetData() {
//        brickId = 0
//        _bricksList.clear()
//        _bricksList = createBricksList().toMutableStateList()
//    }
//
//    private fun createBricksList(): MutableList<Brick> {
//        val bricksList: MutableList<Brick> = mutableListOf()
//        for (i in 0 until GameConfig.MAX_BRICKS_ON_LEVEL) {
//
//            bricksList.add(createBrick())
//        }
//        return bricksList
//    }
//
//    private fun getRandomImage(): Int {
//        var maxColors: Int = 0
//        maxColors = max(GameConfig.COLUMNS, GameConfig.ROWS)
//
//        if (GameConfig.WIN_NUMBER_LINE == 0) maxColors else maxColors += 1
//
//        if (GameConfig.imagesBricks.elementAtOrNull(maxColors) == null) {
//            maxColors = GameConfig.imagesBricks.size - 1
//        }
//        return GameConfig.imagesBricks[(0..maxColors).random()]
//    }
//
//    fun removeBrick(brick: Brick) {
//        FieldViewModel.setBricksOnField(brick)
//        _bricksList.remove(brick)
//        this.checkIfNeedNewBricksList()
//    }
//
//    private fun checkIfNeedNewBricksList() {
//        if (_bricksList.size <= GameConfig.MIN_BRICKS_TO_ADD_NEXT) {
//            for (i in _bricksList.size until GameConfig.MAX_BRICKS_ON_LEVEL) {
////                soundController.cristalAdd()
//                _bricksList.add(createBrick())
//            }
//        }
//    }
//
//    private fun createBrick(): Brick {
//        return Brick(
//            x = mutableIntStateOf(0),
//            y = mutableIntStateOf(0),
//            id = ++brickId,
//            position = brickId.toString(),
//            assetImage = getRandomImage()
//        )
//    }
//}
//
//
//
//
//
//
//
//
