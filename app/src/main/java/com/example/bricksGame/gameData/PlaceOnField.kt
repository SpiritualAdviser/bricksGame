package com.example.bricksGame.gameData

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import com.example.bricksGame.R
import com.example.bricksGame.helper.Sprite
import com.example.bricksGame.helper.SpriteAnimation


class PlaceOnField(

    val position: Pair<Int, Int>,
    var slot: MutableState<BrickType>,
)