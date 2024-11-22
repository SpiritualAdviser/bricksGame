package com.example.bricksGame.ui

import com.example.bricksGame.R

object GameConfig {

    /**
     * options for game designer Field Game
     * Is set COLUMNS and ROWS on game
     */
    private const val COLUMNS: Int = 7
    private const val ROWS: Int = 6

    /**
     * options MAX_BRICKS is count dragging bricks for game on button block
     */
    private const val MAX_BRICKS = 3

    /**
     * options for padding FieldGame in Dp
     */
    private const val PADDING = 10

    /**
     * options for image assets brick on game
     * Warning!! COLUMNS == imagesBricks size +1 brick
     */
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
}