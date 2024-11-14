package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun PortraitLayout() {
    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {
        ButtonHome()
        FieldBox()
//        BricksBlock()
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

@Composable
private fun FieldBox() {
    Box(
        Modifier
            .size(
                FieldViewModel.width,
                FieldViewModel.height
            )
            .background(Color.Gray),
        contentAlignment = Alignment.Center,

        ) {
//        GridFieldBox()
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