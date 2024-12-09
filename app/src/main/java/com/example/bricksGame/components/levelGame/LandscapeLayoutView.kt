package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.bricksGame.components.NaviBar.ButtonNaviBar
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.GameConfig
import com.example.bricksGame.ui.helper.CollisionBricksOnLevel
import kotlinx.coroutines.launch

@Composable
fun LandscapeLayout() {
    Box(
        Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_level_landscape),
            contentDescription = "levelBg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.wide_rock),
            contentDescription = "wide_rock",
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Image(
            painter = painterResource(id = R.drawable.thin_rock),
            contentDescription = "tin_rock",
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

    LeftBar()
    GridFieldBox()
    BricksBlock()
}

@Composable
private fun LeftBar() {
    Column(
        Modifier
            .fillMaxSize(),
    ) {
        ButtonBlock()
        Spacer(Modifier.size(10.dp))
        PlayerScoreBlock()
    }
}

@Composable
private fun ButtonBlock() {

    Row(
        Modifier.offset(18.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ButtonNaviBar()
    }
}

@Composable
private fun GridFieldBox() {
    Box(
        modifier = Modifier
            .padding(GameConfig.PADDING_BG_FIELD.dp)
            .paint(
                painter = painterResource(R.drawable.bgfieldallmosy),
                sizeToIntrinsics = true,
                contentScale = ContentScale.FillBounds
            )
//            .border(4.dp, Color.Magenta),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GameConfig.ROWS),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(GameConfig.PADDING_FIELD.dp)
                .size(
                    FieldViewModel.brickSizeLandscape * GameConfig.ROWS,
                    FieldViewModel.brickSizeLandscape * GameConfig.COLUMNS
                )
        ) {

            items(FieldViewModel.brickOnField) {

                Box(
                    Modifier
                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
                        .border(
                            GameConfig.BRICK_BORDER_SIZE.dp, it.borderColor.value,
                            RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp)
                        )
                        .size(FieldViewModel.brickSizeLandscape)
                        .background(GameConfig.BRICK_BG_FIELD_COLOR)
                        .padding(2.dp)
                        .paint(
                            painterResource(it.assetImage.value),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
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

    Column(
        modifier = Modifier
            .offset((FieldViewModel.brickSizeLandscape * (GameConfig.ROWS + 3)) / 2),
//            .border(4.dp, Color.Magenta),
        verticalArrangement = Arrangement.Center

    ) {
        (BricksViewModel.bricks.forEach {
            key(it.id) {
                Box(
                    Modifier
                        .offset { IntOffset(it.x.intValue, it.y.intValue) }
                        .size(FieldViewModel.brickSizeLandscape)
                        .background(GameConfig.BRICK_BG_COLOR)
                        .paint(
                            painterResource(it.assetImage),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
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