package com.example.bricksGame.components.levelGame


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.ui.helper.AppNavigation
import com.example.bricksGame.R
import com.example.bricksGame.ui.helper.Routes
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.helper.ButtonController


@Composable
fun RunLevelGame() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGray)),
    ) {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeLayout()
        } else {
            PortraitLayout()
        }
    }
}

@Composable
private fun LandscapeLayout() {
    Row(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonHome()
        FieldBox()
        BricksBlock()
    }
}

@Composable
private fun PortraitLayout() {
    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        ButtonHome()
        FieldBox()
        BricksBlock()
    }
}

@Composable
private fun ButtonHome() {
    Button(onClick = {
        ButtonController.navigateHome()
    }) {
        Text("Home")
    }
}

@Composable

private fun BricksBlock() {

    val orientation = LocalConfiguration.current.orientation
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Column() {
            (BricksViewModel.bricks.forEach {
                Box(
                    Modifier
                        .offset(it.y, it.x)
                        .size(it.width, it.height)
                        .background(it.color)
                        .pointerInput(Unit) {
                            detectDragGestures { _, dragAmount ->
                                it.y += dragAmount.x.toDp()
                                it.x += dragAmount.y.toDp()
                            }
                        }
                )
                Spacer(modifier = Modifier.size(8.dp))
            })
        }
    } else {
        Row() {
            (BricksViewModel.bricks.forEach {
                Box(
                    Modifier
                        .offset(it.x, it.y)
                        .size(it.width, it.height)
                        .background(it.color)
                        .pointerInput(Unit) {
                            detectDragGestures { _, dragAmount ->
                                it.x += dragAmount.x.toDp()
                                it.y += dragAmount.y.toDp()
                            }
                        }
                )
                Spacer(modifier = Modifier.size(8.dp))
            })
        }
    }
}

@Composable
private fun FieldBox() {
    Box(
        Modifier
            .size(
                FieldViewModel.width + FieldViewModel.padding,
                FieldViewModel.height + FieldViewModel.padding
            )
            .background(Color.Gray),
        contentAlignment = Alignment.Center,

        ) {
        GridFieldBox()
    }
}

@Composable
private fun GridFieldBox() {

    LazyVerticalGrid(
        columns = GridCells.FixedSize(BricksViewModel.width),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.size(FieldViewModel.width, FieldViewModel.height)

    ) {
        items(FieldViewModel.colorList.size) { index ->
            Box(
                Modifier
                    .background(color = FieldViewModel.colorList[index])
                    .size(BricksViewModel.width, BricksViewModel.height)
                    .border(1.dp, Color.Black)
            )
        }
    }
}



