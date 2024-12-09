package com.example.bricksGame

import android.database.sqlite.SQLiteDatabase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

object GameConfig : ViewModel() {
    lateinit var gameData: SQLiteDatabase

    /**
     * options for game designer Field Game
     * Is set COLUMNS and ROWS on game
     */
    const val COLUMNS: Int = 7
    const val ROWS: Int = 6

    /**
    The number indicates how many crystals in a row the same color will win.
    0- full row and column are played.
    3- Three crystals in a row -win
     */
    val WIN_NUMBER_LINE: Int = 0

    /**
     * options MAX_BRICKS is count dragging bricks for game on button block
     */
    const val MAX_BRICKS_ON_LEVEL = 4
    const val MIN_BRICKS_TO_ADD_NEXT = 2

    /**
     * options for padding FieldGame in Dp
     */
    const val PADDING_BG_FIELD = 15
    const val PADDING_FIELD = 25

    /**
     * options BRICK_BORDER_SIZE for brick border size in Dp
     *  options BRICK_BORDER_COLOR ond BRICK_BORDER_HOVER_COLOR for brick border color
     *  options BRICK_ROUNDED_CORNER for brick corner size
     */

    val BRICK_BG_COLOR = Color.Transparent
    val BRICK_BG_FIELD_COLOR = Color(0xC83E3A39)
    const val BRICK_BORDER_SIZE = 1
    val BRICK_BORDER_COLOR = Color.Black
    val BRICK_BORDER_HOVER_COLOR = Color.Red
    const val BRICK_ROUNDED_CORNER = 5

    /**
     * options for image assets brick on game
     * Warning!! COLUMNS == imagesBricks size +1 brick
     */
    val imagesBricks: Map<Int, Int> = mapOf(

        0 to R.drawable.red_brick,
        1 to R.drawable.blue_brick,
        2 to R.drawable.green_brick,
        3 to R.drawable.purple_brick,
        4 to R.drawable.orange_brick,
        5 to R.drawable.dark_blue_brick,
        6 to R.drawable.pink_brick,
        7 to R.drawable.bronze_brick,
        8 to R.drawable.gold_brick,
        9 to R.drawable.dark_brick,
    )

    var SOUND_MUTED by mutableStateOf(false)
}