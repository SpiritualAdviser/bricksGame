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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.helper.ButtonController
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import kotlinx.coroutines.launch

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
private fun FieldBox() {
    Column(
        Modifier
            .size(
                FieldViewModel.width,
                FieldViewModel.height
            )
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GridFieldBox()
        BricksBlock()
    }
}

@Composable
private fun GridFieldBox() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(FieldViewModel.ROWS),
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
//            .size(FieldViewModel.width, FieldViewModel.height)
            .padding(FieldViewModel.padding)

    ) {
        items(FieldViewModel.brickOnField) {

            Box(
                Modifier
                    .size(it.width, it.height)
                    .background(it.color)
                    .border(it.border, it.borderColor.value)
                    .onGloballyPositioned { coordinates ->
                        it.setGloballyPosition(coordinates)
                    }
            )
        }
    }
}

@Composable
private fun BricksBlock() {
    val coroutineScope = rememberCoroutineScope()
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        (BricksViewModel.bricks.forEach {
            Box(
                Modifier
                    .offset(it.x, it.y)
                    .size(it.width, it.height)
                    .background(it.color)
                    .onGloballyPositioned { coordinates ->
                        it.setGloballyPosition(coordinates)
                    }

                    .pointerInput(Unit) {


                        detectDragGestures(
                            onDragStart = { },
                            onDrag = { _, dragAmount ->
                                it.dragging(dragAmount.x, dragAmount.y)
                                coroutineScope.launch {
                                    CollisionBricksOnLevel.observeCenterObjects(it)
                                }
                            },
                            onDragEnd = {
                                coroutineScope.launch {
                                    it.stickPosition()
                                }
                            },
                            onDragCancel = { },
                        )

                    }
            )
            Spacer(modifier = Modifier.size(8.dp))
        })
    }
}