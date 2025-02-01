package com.example.bricksGame.config

import android.database.sqlite.SQLiteDatabase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.bricksGame.R
import com.example.bricksGame.ui.theme.errorLight
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameConfig @Inject constructor() {
    lateinit var gameData: SQLiteDatabase
    val GAME_VERSION = "a-1.2"
    val CHEAT = false

    /**
     * options for game designer Field Game
     *
     * Is set COLUMNS and ROWS on game
     */

    var ROWS: Int = 5
    var COLUMNS: Int = 6

    /**
    The number indicates how many crystals in a row the same color will win.
    0- full row and column are played.
    3- Three crystals in a row -win
     */
    var WIN_NUMBER_LINE by mutableIntStateOf(0)

    var MIN_WIN_NUMBER_LINE: Int = 3

    val MAX_SCORE_ON_GAME = 300
    val MIN_SCORE_ON_GAME = 20

    /**
     * options MAX_BRICKS is count dragging bricks for game on button block
     */
    val MAX_LEVELS_ON_GAME = 200

    val MAX_LINE_FIELD_ON_GAME = 9
    val MIN_LINE_FIELD_ON_GAME = 3

    var MAX_BRICKS_ON_LEVEL = 3
    var MAX_NEGATIVE_BRICKS_ON_LEVEL = listOf<Int>(0, 0)
    var MIN_BRICKS_TO_ADD_NEXT = 0
    val MAX_BRICKS_SIZE = 55

    /**
     * options for padding FieldGame in Dp
     */
    val PADDING_FIELD = 30

    /**
     * options BRICK_BORDER_SIZE for brick border size in Dp
     *  options BRICK_BORDER_COLOR ond BRICK_BORDER_HOVER_COLOR for brick border color
     *  options BRICK_ROUNDED_CORNER for brick corner size
     */

    val BRICK_BG_COLOR = Color.Transparent
    val BRICK_BG_FIELD_COLOR = Color(0xB92C2C2C)
    val FIELD_BG_COLOR = Color(0xCD2A2A2A)
    val BRICK_BORDER_SIZE = 1
    val BRICK_BORDER_COLOR = Color.Black
    val BRICK_BORDER_HOVER_COLOR = errorLight
    val BRICK_ROUNDED_CORNER = 4

    /**
     * options for image assets brick on game
     * Warning!! COLUMNS == imagesBricks size +1 brick
     */

    var SPEED_OPEN_BONUS = 0.01f
    var MAX_SPEED_OPEN_BONUS = 0.1f

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

    val imagesWinLine = listOf(
        R.drawable.wow,
        R.drawable.mega,
    )

    val imagesWinLevel = listOf(
        R.drawable.win,
        R.drawable.lose,
    )

    val imagesBricksBonuses = listOf(
        R.drawable.ice_bonus,
        R.drawable.fire_bonus,
        R.drawable.hammer_bonus,
    )
    val NEGATIVE_BONUS_LEAVES = 998
    val NEGATIVE_BONUS_LEAVES_LIFE = 1
    val NEGATIVE_BONUS_ROCK = 999
    val NEGATIVE_BONUS_ROCK_LIFE = 2
    val MAX_CLOSED_PERCENT_GAME_FIELD = 30

    val negativeBonuses = listOf(
        NegativeBonus(
            id = NEGATIVE_BONUS_ROCK,
            life = NEGATIVE_BONUS_ROCK_LIFE,
            imageFullLife = R.drawable.bg_close_brick,
            imageOnDamage = R.drawable.bg_close_brick_damage,
            spriteName = "bg_close_brick.json",
            animationFullLife = "idle",
            animationOnDamage = "crash",
            animationOnDestroy = "destroy",
        ),

        NegativeBonus(
            id = NEGATIVE_BONUS_LEAVES,
            life = NEGATIVE_BONUS_LEAVES_LIFE,
            imageFullLife = R.drawable.bg_close_liaves,
            imageOnDamage = R.drawable.bg_close_liaves,
            spriteName = "bg_close_leaves.json",
            animationFullLife = "idle",
            animationOnDamage = "idle",
            animationOnDestroy = "destroy",
        ),
    )

    var WIN_LINE_DESTROY_NEGATIVE_BONUS = true
    var SOUND_MUTED by mutableStateOf(false)
    var GAME_TYPE_FREE = false
}
