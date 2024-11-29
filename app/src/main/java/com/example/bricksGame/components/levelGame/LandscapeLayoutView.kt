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
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.levelGame.models.PlayerViewModel
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.ButtonController
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
            modifier = Modifier.fillMaxWidth(),
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

    Row(
        Modifier
            .fillMaxSize()
    ) {
        TopBar()
        FieldBox()
    }
}

@Composable
private fun TopBar() {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(GameConfig.PADDING_BG_FIELD.dp),
    ) {
        ButtonBlock()
        Spacer(Modifier.size(10.dp))
        PlayerScore()
    }
}

@Composable
private fun ButtonBlock() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { ButtonController.navigateHome() },

            modifier = Modifier
                .size(50.dp)
                .paint(
                    painter = painterResource(R.drawable.close),
                    contentScale = ContentScale.FillWidth
                )
        )
        {}
        Spacer(Modifier.size(10.dp))
        IconToggleButton(
            checked = GameConfig.SOUND_MUTED, onCheckedChange = {
                soundController.soundMute()
            },
            modifier = Modifier
                .size(60.dp)
                .paint(
                    painter = if (GameConfig.SOUND_MUTED) painterResource(R.drawable.play_muted)
                    else painterResource(R.drawable.play),
                    contentScale = ContentScale.FillWidth
                )
        ) {}
    }
}

@Composable
private fun PlayerScore() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.score_icon),
            contentDescription = "scoreIcon",
            Modifier.size(40.dp)
        )
        Spacer(Modifier.size(10.dp))
        Text(
            text = "PlayerViewModel.playerScore.toString()",
            fontSize = 25.sp,
            color = Color.White
        )
    }
}

@Composable
private fun FieldBox() {

    Row(
        Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        GridFieldBox()
        BricksBlock()
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
        horizontalAlignment = Alignment.CenterHorizontally

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