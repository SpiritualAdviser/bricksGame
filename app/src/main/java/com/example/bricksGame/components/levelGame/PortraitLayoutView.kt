package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.zIndex
import com.example.bricksGame.R
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.components.popups.WinLine
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.LevelPortraitBg
import com.example.bricksGame.logic.CollisionBricksOnLevel
import com.example.bricksGame.components.popups.WinPopup
import com.example.bricksGame.components.popups.models.PopupsViewModel
import kotlinx.coroutines.launch

@Composable
fun PortraitLayout() {
    LevelPortraitBg()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        FieldBlock()
    }
    if (PopupsViewModel.showPopupWinLine.value) {
        WinLine()
    }

    if (PopupsViewModel.showPopupOnFinishGame.value) {
        WinPopup()
    }
}

@Composable
fun FieldBlock() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BonusBlock()
        GridFieldBox()
        BricksBlock()
        Spacer(Modifier.size(30.dp))
    }
}

@Composable
private fun TopBar() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 15.dp, end = 15.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
//            .border(4.dp, Color.Magenta),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween

        ) {
            PlayerScoreBlock()
            ButtonBlock()
        }
        LevelTargetBlockPortrait()
    }
}

@Composable
private fun ButtonBlock() {
    Row(

//        modifier = Modifier
//            .border(4.dp, Color.Magenta),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonNaviBar()
    }
}

@Composable
private fun GridFieldBox() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
            .background(GameConfig.FIELD_BG_COLOR)
//            .border(4.dp, Color.Green)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(GameConfig.ROWS),
            userScrollEnabled = false,
            modifier = Modifier
                .size(
                    FieldViewModel.brickSizePortrait * GameConfig.ROWS,
                    FieldViewModel.brickSizePortrait * GameConfig.COLUMNS + GameConfig.BRICK_BORDER_SIZE.dp
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
                        .size(FieldViewModel.brickSizePortrait)
                        .background(GameConfig.BRICK_BG_FIELD_COLOR)
                        .paint(
                            painterResource(R.drawable.bgfielbrickempty),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
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

    Row(
        modifier = Modifier
//            .border(4.dp, Color.Magenta),
    ) {
        (BricksViewModel.bricks.forEach {
            key(it.id) {
                Box(
                    Modifier
                        .offset { IntOffset(it.x.intValue, it.y.intValue) }
                        .size(FieldViewModel.brickSizePortrait)
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
                                        it.takeAPlaces()
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

@Composable
private fun BonusBlock() {
    val coroutine = rememberCoroutineScope()
    Box(
        Modifier
            .zIndex(FieldViewModel.zIndex.floatValue)
    ) {
        Row(
            modifier = Modifier
//                .border(4.dp, Color.Magenta),
        ) {
            BonusViewModel.bonuses.forEach {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
                        .border(
                            GameConfig.BRICK_BORDER_SIZE.dp, color = it.activeBonusBorder.value,
                            shape = RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp)
                        )
                        .size(FieldViewModel.brickSizePortrait)
                        .background(GameConfig.FIELD_BG_COLOR)
                ) {}
                Spacer(Modifier.size(10.dp))
            }
        }

        Row(
            modifier = Modifier
        ) {
            BonusViewModel.bonuses.forEach {
                key(it.id) {
                    val currentBrick = it
                    Box(
                        Modifier
                            .zIndex(it.zIndex.value)
                            .offset { IntOffset(it.x.intValue, it.y.intValue) }
                            .size(FieldViewModel.brickSizePortrait)
                            .paint(
                                painterResource(it.assetImage),
                                alpha = it.alpha.value,
                                sizeToIntrinsics = true,
                                contentScale = ContentScale.FillBounds
                            )
                            .onGloballyPositioned { coordinates ->
                                it.setGloballyPosition(coordinates)
                            }
                            .pointerInput(it) {
                                detectDragGestures(
                                    onDragStart = {
                                        FieldViewModel.changeZIndex(1F)
                                        currentBrick.changeZIndex(1F)
                                    },
                                    onDrag = { _, dragAmount ->
                                        if (it.canDrag) {
                                            it.dragging(dragAmount.x, dragAmount.y)
                                            coroutine.launch {
                                                CollisionBricksOnLevel.observeCenterObjects(it)
                                            }
                                        }
                                    },
                                    onDragEnd = {
                                        if (it.canDrag) {
                                            coroutine.launch {
                                                it.takeAPlaces()
//                                        FieldViewModel.checkFieldOnFinishRound()
                                            }
                                        }
                                        FieldViewModel.changeZIndex(0F)
                                        it.changeZIndex(0F)
                                    },
                                    onDragCancel = {
                                        FieldViewModel.changeZIndex(0F)
                                        it.changeZIndex(0F)
                                    },
                                )
                            }
                    )
                    Spacer(Modifier.size(10.dp))
                }
            }
        }
    }
}
