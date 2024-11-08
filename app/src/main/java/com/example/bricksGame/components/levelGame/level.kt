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
import androidx.navigation.NavHostController
import com.example.bricksGame.R


class LevelGame(private val navController: NavHostController) {
    private val bricks: Bricks = Bricks.getInstance()

    @Composable
    fun Run() {
        MainBox()
    }

    @Composable
    private fun MainBox() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkGray)),
        ) {
            ScreenWithOrientation()
        }
    }

    @Composable
    private fun ScreenWithOrientation() {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeLayout()
        } else {
            PortraitLayout()
        }
    }

    @Composable
    private fun LandscapeLayout() {
        Row(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetButtonHome()
            GetFieldBox()
            GetThreeBricksBlock()
        }
    }

    @Composable
    private fun PortraitLayout() {
        Column(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GetButtonHome()
            GetFieldBox()
            GetThreeBricksBlock()
        }
    }

    @Composable
    private fun GetButtonHome() {
        Button(
            onClick = { navController.navigate("HomeScreen") }) {
            Text("Home")
        }
    }

    @Composable
    private fun GetFieldBox() {
        Box(
            Modifier
                .size(
                    bricks.fieldBricks.fieldHeight + bricks.fieldBricks.padding,
                    bricks.fieldBricks.fieldHeight + bricks.fieldBricks.padding
                )
                .background(Color.Gray),
            contentAlignment = Alignment.Center,

            ) {
            FillBrickField()
        }
    }

    @Composable
    private fun GetThreeBricksBlock() {
        LazyHorizontalGrid(
            modifier = Modifier
                .size((bricks.width + 10.dp) * 3, bricks.height + 10.dp)
                .border(2.dp, Color.Black)
                .padding(5.dp),
            rows = GridCells.FixedSize(bricks.width),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(3) { index ->
                Box(
                    Modifier
                        .background(color = bricks.colorList[index])
                        .size(bricks.width, bricks.height)
                )
            }
        }
    }

    @Composable
    private fun FillBrickField() {
        var fieldElementIndex = 0
        Column {
            bricks.matrixField.forEach { _ ->

                LazyRow {
                    items(bricks.fieldBricks.rows) {
                        Box(
                            modifier = Modifier
                                .background(bricks.colorsListField[++fieldElementIndex])
                                .border(width = 1.dp, color = Color.Black)
                                .size(bricks.width, bricks.height)
                        )
                    }
                }
            }
        }
    }
}


