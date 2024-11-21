package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.helper.ButtonController
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import kotlinx.coroutines.launch

@Composable
fun PortraitLayout() {
    Image(
        painter = painterResource(id = R.drawable.bg_level),
        contentDescription = "levelBg",
        modifier = Modifier.fillMaxHeight(),
        contentScale = ContentScale.Crop
    )

    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {

        RestartGame()
        FieldBox()
    }
}

@Composable
private fun RestartGame() {
    Button(onClick = {
        ButtonController.navigateHome()
    }) {
        Text("restart the game")
    }
}

@Composable
private fun FieldBox() {

    Column(
        Modifier
            .size(
                FieldViewModel.width,
                FieldViewModel.height
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GridFieldBox()
        BricksBlock()
    }
}

@Composable
private fun GridFieldBox() {
    Box(
        modifier = Modifier
            .size(FieldViewModel.withBg, FieldViewModel.heightBg)
            .paint(
                painter = painterResource(R.drawable.bgfieldallmosy),
                sizeToIntrinsics = true,
                contentScale = ContentScale.FillBounds
            )
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(FieldViewModel.ROWS),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(FieldViewModel.bgWPadding, FieldViewModel.bgHPadding)

        ) {

            items(FieldViewModel.brickOnField) {

                Box(
                    Modifier
                        .size(it.width, it.height)
                        .paint(
                            painterResource(it.assetImage.value),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .border(it.border, it.borderColor.value)
                        .onGloballyPositioned { coordinates ->
                            it.setGloballyPosition(coordinates)
                        }
                )
            }
        }
    }
}

@Composable
private fun BricksBlock() {
    val coroutine = rememberCoroutineScope()
    Row(
        Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        ) {
        (BricksViewModel.bricks.forEach {
            key(it.id) {
                Box(
                    Modifier
                        .offset { IntOffset(it.x.intValue, it.y.intValue) }
                        .size(it.width, it.height)
                        .paint(
                            painterResource(it.assetImage),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .border(it.border, it.borderColor)
                        .onGloballyPositioned { coordinates ->
                            it.setGloballyPosition(coordinates)
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { },
                                onDrag = { _, dragAmount ->
                                    it.dragging(dragAmount.x, dragAmount.y)
                                    coroutine.launch {
                                        CollisionBricksOnLevel.observeCenterObjects(it)
                                    }
                                },
                                onDragEnd = {
                                    coroutine.launch {
                                        it.stickPosition()
                                        FieldViewModel.checkFieldOnFinishRound()
                                    }
                                },
                                onDragCancel = { },
                            )
                        }
                )
                Spacer(Modifier.size(10.dp))
            }
        })
    }
}