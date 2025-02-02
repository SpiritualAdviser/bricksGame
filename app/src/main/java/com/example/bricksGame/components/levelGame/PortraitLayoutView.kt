package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.helper.LevelPortraitBg
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
//    if (PopupsViewModel.showPopupWinLine.value) {
//        RunAnimationScale()
//        WinLine()
//    }
//
//    if (PopupsViewModel.showPopupOnFinishGame.value) {
//        WinPopup()
//    }
}

@Composable
fun FieldBlock() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        BonusBlock()
        GridFieldBox()
//        BricksBlock()
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
private fun GridFieldBox(fieldViewModel: FieldViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(fieldViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp))
            .background(fieldViewModel.gameConfig.FIELD_BG_COLOR)
//            .border(4.dp, Color.Green)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(fieldViewModel.gameConfig.ROWS),
            userScrollEnabled = false,
            modifier = Modifier
                .size(
                    fieldViewModel.brickSizePortrait * fieldViewModel.gameConfig.ROWS,
                    fieldViewModel.brickSizePortrait * fieldViewModel.gameConfig.COLUMNS + fieldViewModel.gameConfig.BRICK_BORDER_SIZE.dp
                )
        ) {

            items(fieldViewModel.brickOnField) {

                Box(
                    Modifier
                        .clip(RoundedCornerShape(fieldViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp))
                        .border(
                            fieldViewModel.gameConfig.BRICK_BORDER_SIZE.dp, it.borderColor.value,
                            RoundedCornerShape(fieldViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp)
                        )
                        .size(fieldViewModel.brickSizePortrait)
                        .background(fieldViewModel.gameConfig.BRICK_BG_FIELD_COLOR)
                        .paint(
                            painterResource(R.drawable.bgfielbrickempty),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .paint(
                            painter = if (it.hasSprite.value && it.spriteSheet != null) {
                                BitmapPainter(
                                    image = it.spriteSheet!!,
                                    srcOffset = IntOffset(
                                        x = it.xSrcOffset.intValue,
                                        y = it.ySrcOffset.intValue
                                    ),
                                    srcSize = IntSize(
                                        width = it.wSrcSize.intValue,
                                        height = it.hSrcSize.intValue
                                    )
                                )
                            } else {
                                BitmapPainter(
                                    image = ImageBitmap.imageResource(it.assetImage.value)
                                )
                            },

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
private fun BricksBlock(
    bricksViewModel: BricksViewModel = hiltViewModel(),
    fieldViewModel: FieldViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    Row(
        modifier = Modifier
//            .border(4.dp, Color.Magenta),
    ) {
        (bricksViewModel.bricks.forEachIndexed { index, brick ->

            key(brick.id) {

                Box(
                    Modifier
                        .offset { IntOffset(brick.x.intValue, brick.y.intValue) }
                        .size(fieldViewModel.brickSizePortrait)
                        .background(bricksViewModel.gameConfig.BRICK_BG_COLOR)
                        .graphicsLayer {
                            if (AnimationsBrick.canRunTranslation.value && !brick.wasAnimated.value) {
                                translationX = brick.translationX.value
                            }
                        }
                        .paint(
                            painterResource(brick.assetImage),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(bricksViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp))
                        .onGloballyPositioned { coordinates ->
                            brick.setGloballyPosition(coordinates)
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { AnimationsBrick.canRunTranslation.value = true },
                                onDrag = { _, dragAmount ->

                                    brick.dragging(dragAmount.x, dragAmount.y)
                                    coroutine.launch {
//                                        CollisionBricksOnLevel.observeCenterObjects(brick)
                                    }
                                },
                                onDragEnd = {
                                    coroutine.launch {
//                                        brick.takeAPlaces()
                                    }
                                },
                                onDragCancel = { },
                            )
                        }
                )
                Spacer(Modifier.size(10.dp))
            }
//            AnimationsBrick.InitAnimationTranslationX(brick)
//            AnimationsBrick.runAnimationTranslation(brick, index)
        })
    }
}

@Composable
private fun BonusBlock(
    bonusViewModel: BonusViewModel = hiltViewModel(),
    fieldViewModel: FieldViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    Box(
        Modifier
            .zIndex(fieldViewModel.zIndex.floatValue)
    ) {
        Row(
            modifier = Modifier
//                .border(4.dp, Color.Magenta),
        ) {
            bonusViewModel.bonuses.forEach {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bonusViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp))
                        .border(
                            bonusViewModel.gameConfig.BRICK_BORDER_SIZE.dp,
                            color = it.activeBonusBorder.value,
                            shape = RoundedCornerShape(bonusViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp)
                        )
                        .size(fieldViewModel.brickSizePortrait)
                        .background(bonusViewModel.gameConfig.FIELD_BG_COLOR)
                ) {}
                Spacer(Modifier.size(10.dp))
            }
        }

        Row(
            modifier = Modifier
        ) {
            bonusViewModel.bonuses.forEach {
                key(it.id) {
                    val currentBrick = it
                    Box(
                        Modifier
                            .zIndex(it.zIndex.value)
                            .offset { IntOffset(it.x.intValue, it.y.intValue) }
                            .size(fieldViewModel.brickSizePortrait)
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
                                        fieldViewModel.changeZIndex(1F)
                                        currentBrick.changeZIndex(1F)
                                    },
                                    onDrag = { _, dragAmount ->
                                        if (it.canDrag) {
                                            it.dragging(dragAmount.x, dragAmount.y)
                                            coroutine.launch {
//                                                CollisionBricksOnLevel.observeCenterObjects(it)
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
                                        fieldViewModel.changeZIndex(0F)
                                        it.changeZIndex(0F)
                                    },
                                    onDragCancel = {
                                        fieldViewModel.changeZIndex(0F)
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
