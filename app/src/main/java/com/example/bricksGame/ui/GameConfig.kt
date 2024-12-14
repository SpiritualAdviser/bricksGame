package com.example.bricksGame.ui

import android.database.sqlite.SQLiteDatabase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.compose.errorLight

object GameConfig : ViewModel() {
    lateinit var gameData: SQLiteDatabase

    /**
     * options for game designer Field Game
     * Is set COLUMNS and ROWS on game
     */

    var ROWS: Int = 6
    var COLUMNS: Int = 7

    /**
    The number indicates how many crystals in a row the same color will win.
    0- full row and column are played.
    3- Three crystals in a row -win
     */
    var WIN_NUMBER_LINE: Int = 0

    /**
     * options MAX_BRICKS is count dragging bricks for game on button block
     */
    var MAX_BRICKS_ON_LEVEL = 3
    const val MIN_BRICKS_TO_ADD_NEXT = 0
    const val MAX_BRICKS_SIZE = 60

    /**
     * options for padding FieldGame in Dp
     */
    const val PADDING_FIELD = 35

    /**
     * options BRICK_BORDER_SIZE for brick border size in Dp
     *  options BRICK_BORDER_COLOR ond BRICK_BORDER_HOVER_COLOR for brick border color
     *  options BRICK_ROUNDED_CORNER for brick corner size
     */

    val BRICK_BG_COLOR = Color.Transparent
    val BRICK_BG_FIELD_COLOR = Color(0x943E3A39)
    val FIELD_BG_COLOR = Color(0xC8181717)
    const val BRICK_BORDER_SIZE = 1
    val BRICK_BORDER_COLOR = Color.Black
    val BRICK_BORDER_HOVER_COLOR = errorLight
    const val BRICK_ROUNDED_CORNER = 5

    /**
     * options for image assets brick on game
     * Warning!! COLUMNS == imagesBricks size +1 brick
     */

    var SPEED_OPEN_BONUS = 0.1f
    val imagesBricks = listOf(

        R.drawable.red_brick,
        R.drawable.blue_brick,
        R.drawable.green_brick,
        R.drawable.purple_brick,
        R.drawable.orange_brick,
        R.drawable.dark_blue_brick,
        R.drawable.pink_brick,
        R.drawable.bronze_brick,
        R.drawable.gold_brick,
        R.drawable.dark_brick,
    )

    val imagesBricksBonus = listOf(
        R.drawable.ice_bonus,
        R.drawable.fire_bonus,
        R.drawable.hammer_bonus,
    )

    var SOUND_MUTED by mutableStateOf(false)
    var GAME_TYPE_FREE = true
}