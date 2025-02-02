package com.example.bricksGame.gameData

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.levelGame.models.FieldBrick
import com.example.bricksGame.config.Level
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelData @Inject constructor(
) {
    lateinit var brickOnFields: MutableList<FieldBrick>

    var currentLevel: Level? = null

    val EMPTY_ID = "Color.Transparent"
    var zIndex = mutableFloatStateOf(0F)

    var brickSizePortrait = 0.dp
    var brickSizeLandscape = 0.dp
    var fieldMAxWidthSize = 0.dp

    fun setBrickOnField(brickOnFieldInner: MutableList<FieldBrick>) {
        brickOnFields = brickOnFieldInner
    }
}