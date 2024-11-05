package com.example.bricksGame.components.levelGame

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.newGame
import com.example.bricksGame.ui.helper.buttonController

fun getBrick(): Bricks {
    var instance = Bricks.getInstance()

    if (newGame) {
        instance = Bricks.getInstance(true)
    }
    return instance
}

@Composable
fun LevelGame() {
    val brick = getBrick()
    newGame = false
    MainBox(brick)
}

@Composable
fun ScreenWithOrientation(brick: Bricks) {
    val orientation = LocalConfiguration.current.orientation
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeLayout(brick)
    } else {
        PortraitLayout(brick)
    }
}

@Composable
fun LandscapeLayout(brick: Bricks) {
    Row(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GetButtonHome()
        GetFieldBox(brick)
        GetThreeBricksBlock(brick)
    }
}

@Composable
fun PortraitLayout(brick: Bricks) {
    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        GetButtonHome()
        GetFieldBox(brick)
        GetThreeBricksBlock(brick)
    }
}

@Composable
fun MainBox(brick: Bricks) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGray)),
    ) {
        ScreenWithOrientation(brick)
    }
}

@Composable
fun GetButtonHome() {
    Button(
        onClick = { buttonController.buttonLisener("mainMeny") }) {
        Text("Home")
    }
}

@Composable
fun GetFieldBox(brick: Bricks) {
    Box(
        Modifier
            .size(
                brick.fieldBricks.fieldHeight + brick.fieldBricks.padding,
                brick.fieldBricks.fieldHeight + brick.fieldBricks.padding
            )
            .background(Color.Gray),
        contentAlignment = Alignment.Center,

        ) {
        FillBrickField(brick)
    }
}

@Composable
fun GetThreeBricksBlock(brick: Bricks) {
    LazyHorizontalGrid(
        modifier = Modifier
            .size((brick.width + 10.dp) * 3, brick.height + 10.dp)
            .border(2.dp, Color.Black)
            .padding(5.dp),
        rows = GridCells.FixedSize(brick.width),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(3) { index ->
            Box(
                Modifier
                    .background(color = brick.colorList[index])
                    .size(brick.width, brick.height)
            )
        }
    }
}

@Composable
fun FillBrickField(brick: Bricks) {
    var fieldElementIndex = 0
    Column {
        brick.matrixField.forEachIndexed { indexColumn, _ ->

            LazyRow {
                items(brick.fieldBricks.rows) { index ->
                    Box(
                        modifier = Modifier
                            .background(brick.colorsListField[++fieldElementIndex])
                            .border(width = 1.dp, color = Color.Black)
                            .size(brick.width, brick.height)
                    )
                }
            }
        }
    }
}


