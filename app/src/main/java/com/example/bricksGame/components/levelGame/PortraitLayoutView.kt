package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.helper.LevelPortraitBg

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
//        GridFieldBox()
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

//@Composable
//private fun GridFieldBox() {
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
//            .background(GameConfig.FIELD_BG_COLOR)
////            .border(4.dp, Color.Green)
//    ) {
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(GameConfig.ROWS),
//            userScrollEnabled = false,
//            modifier = Modifier
//                .size(
//                    FieldViewModel.brickSizePortrait * GameConfig.ROWS,
//                    FieldViewModel.brickSizePortrait * GameConfig.COLUMNS + GameConfig.BRICK_BORDER_SIZE.dp
//                )
//        ) {
//
//            items(FieldViewModel.brickOnField) {
//
//                Box(
//                    Modifier
//                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
//                        .border(
//                            GameConfig.BRICK_BORDER_SIZE.dp, it.borderColor.value,
//                            RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp)
//                        )
//                        .size(FieldViewModel.brickSizePortrait)
//                        .background(GameConfig.BRICK_BG_FIELD_COLOR)
//                        .paint(
//                            painterResource(R.drawable.bgfielbrickempty),
//                            sizeToIntrinsics = true,
//                            contentScale = ContentScale.FillBounds
//                        )
//                        .paint(
//                            painter = if (it.hasSprite.value && it.spriteSheet != null) {
//                                BitmapPainter(
//                                    image = it.spriteSheet!!,
//                                    srcOffset = IntOffset(
//                                        x = it.xSrcOffset.intValue,
//                                        y = it.ySrcOffset.intValue
//                                    ),
//                                    srcSize = IntSize(
//                                        width = it.wSrcSize.intValue,
//                                        height = it.hSrcSize.intValue
//                                    )
//                                )
//                            } else {
//                                BitmapPainter(
//                                    image = ImageBitmap.imageResource(it.assetImage.value)
//                                )
//                            },
//
//                            sizeToIntrinsics = true,
//                            contentScale = ContentScale.FillBounds
//                        )
//                        .onGloballyPositioned { coordinates ->
//                            it.setGloballyPosition(coordinates)
//                        }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun BricksBlock() {
//    val coroutine = rememberCoroutineScope()
//    Row(
//        modifier = Modifier
////            .border(4.dp, Color.Magenta),
//    ) {
//        (BricksViewModel.bricks.forEachIndexed { index, brick ->
//
//            key(brick.id) {
//
//                Box(
//                    Modifier
//                        .offset { IntOffset(brick.x.intValue, brick.y.intValue) }
//                        .size(FieldViewModel.brickSizePortrait)
//                        .background(GameConfig.BRICK_BG_COLOR)
//                        .graphicsLayer {
//                            if (AnimationsBrick.canRunTranslation.value && !brick.wasAnimated.value) {
//                                translationX = brick.translationX.value
//                            }
//                        }
//                        .paint(
//                            painterResource(brick.assetImage),
//                            sizeToIntrinsics = true,
//                            contentScale = ContentScale.FillBounds
//                        )
//                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
//                        .onGloballyPositioned { coordinates ->
//                            brick.setGloballyPosition(coordinates)
//                        }
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragStart = { AnimationsBrick.canRunTranslation.value = true },
//                                onDrag = { _, dragAmount ->
//
//                                    brick.dragging(dragAmount.x, dragAmount.y)
//                                    coroutine.launch {
//                                        CollisionBricksOnLevel.observeCenterObjects(brick)
//                                    }
//                                },
//                                onDragEnd = {
//                                    coroutine.launch {
//                                        brick.takeAPlaces()
//                                    }
//                                },
//                                onDragCancel = { },
//                            )
//                        }
//                )
//                Spacer(Modifier.size(10.dp))
//            }
//            AnimationsBrick.InitAnimationTranslationX(brick)
//            AnimationsBrick.runAnimationTranslation(brick, index)
//        })
//    }
//}
//
//@Composable
//private fun BonusBlock() {
//    val coroutine = rememberCoroutineScope()
//    Box(
//        Modifier
//            .zIndex(FieldViewModel.zIndex.floatValue)
//    ) {
//        Row(
//            modifier = Modifier
////                .border(4.dp, Color.Magenta),
//        ) {
//            BonusViewModel.bonuses.forEach {
//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
//                        .border(
//                            GameConfig.BRICK_BORDER_SIZE.dp, color = it.activeBonusBorder.value,
//                            shape = RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp)
//                        )
//                        .size(FieldViewModel.brickSizePortrait)
//                        .background(GameConfig.FIELD_BG_COLOR)
//                ) {}
//                Spacer(Modifier.size(10.dp))
//            }
//        }
//
//        Row(
//            modifier = Modifier
//        ) {
//            BonusViewModel.bonuses.forEach {
//                key(it.id) {
//                    val currentBrick = it
//                    Box(
//                        Modifier
//                            .zIndex(it.zIndex.value)
//                            .offset { IntOffset(it.x.intValue, it.y.intValue) }
//                            .size(FieldViewModel.brickSizePortrait)
//                            .paint(
//                                painterResource(it.assetImage),
//                                alpha = it.alpha.value,
//                                sizeToIntrinsics = true,
//                                contentScale = ContentScale.FillBounds
//                            )
//                            .onGloballyPositioned { coordinates ->
//                                it.setGloballyPosition(coordinates)
//                            }
//                            .pointerInput(it) {
//                                detectDragGestures(
//                                    onDragStart = {
//                                        FieldViewModel.changeZIndex(1F)
//                                        currentBrick.changeZIndex(1F)
//                                    },
//                                    onDrag = { _, dragAmount ->
//                                        if (it.canDrag) {
//                                            it.dragging(dragAmount.x, dragAmount.y)
//                                            coroutine.launch {
//                                                CollisionBricksOnLevel.observeCenterObjects(it)
//                                            }
//                                        }
//                                    },
//                                    onDragEnd = {
//                                        if (it.canDrag) {
//                                            coroutine.launch {
//                                                it.takeAPlaces()
////                                        FieldViewModel.checkFieldOnFinishRound()
//                                            }
//                                        }
//                                        FieldViewModel.changeZIndex(0F)
//                                        it.changeZIndex(0F)
//                                    },
//                                    onDragCancel = {
//                                        FieldViewModel.changeZIndex(0F)
//                                        it.changeZIndex(0F)
//                                    },
//                                )
//                            }
//                    )
//                    Spacer(Modifier.size(10.dp))
//                }
//            }
//        }
//    }
//}
