package com.example.bricksGame.gameData

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.bricksGame.config.Level
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelData @Inject constructor(
) {
    lateinit var brickOnFields: MutableList<FieldBrick>
    lateinit var _bricksList: SnapshotStateList<Brick>
    lateinit var _bonusList: SnapshotStateList<Brick>

    private var activeLevel: Level? = null

    val EMPTY_ID = "Color.Transparent"
    var zIndex = mutableFloatStateOf(0F)

//    var brickSizePortrait = 0.dp
//    var brickSizeLandscape = 0.dp
//    var fieldMAxWidthSize = 0.dp

    fun getActiveLevel(): Level? {
        return activeLevel
    }

    fun setBrickOnField(brickOnFieldInner: MutableList<FieldBrick>) {
        brickOnFields = brickOnFieldInner
    }

    fun setActiveLevel(level: Level) {
        activeLevel = level
    }

    fun setBricksList(toMutableStateList: SnapshotStateList<Brick>) {
        _bricksList = toMutableStateList
    }

    fun setBonusList(toMutableStateList: SnapshotStateList<Brick>) {
        _bonusList = toMutableStateList
    }
}