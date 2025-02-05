package com.example.bricksGame.components.levelGame

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
import androidx.compose.ui.graphics.Color
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.components.popups.WinLine
import com.example.bricksGame.components.popups.WinPopup
import com.example.bricksGame.components.popups.animations.AnimationsPopups.RunAnimationScale
import com.example.bricksGame.components.popups.models.PopupsViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.LevelLandscapeBg
import com.example.bricksGame.logic.CollisionBricksOnLevel
import kotlinx.coroutines.launch

@Composable
fun LandscapeLayout() {

    LevelLandscapeBg()
    ButtonBlock()
    LeftBar()
    GridFieldBox()
//    BonusBlock()
//    BricksBlock()

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
private fun LeftBar() {
    Column(
        Modifier
            .fillMaxSize()
            .offset(15.dp, 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PlayerScoreBlock()
        LevelTargetBlockLandscape()
    }
}

@Composable
private fun ButtonBlock() {

    Row(
        Modifier
            .fillMaxSize()
            .offset((-35).dp, 30.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonNaviBar()
    }
}

@Composable
private fun GridFieldBox(fieldViewModel: FieldViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(fieldViewModel.brickCorner.dp))
            .background(fieldViewModel.fieldBgColor)
//            .border(4.dp, Color.Green)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(fieldViewModel.fieldRows),
            horizontalArrangement = Arrangement.Center,
            userScrollEnabled = false,
            modifier = Modifier
                .size(
                    fieldViewModel.fieldWidthLandscape,
                    fieldViewModel.fieldHeightLandscape
                )
        ) {

            items(fieldViewModel.brickOnField) {

                Box(
                    Modifier
                        .clip(RoundedCornerShape(fieldViewModel.brickCorner.dp))
                        .border(
                            fieldViewModel.brickBorderSize, it.borderColor.value,
                            RoundedCornerShape(fieldViewModel.brickCorner.dp)
                        )
                        .size(fieldViewModel.brickSizeLandscape)
                        .background(fieldViewModel.brickBgColor)
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

//@Composable
//private fun BricksBlock() {
//    val coroutine = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .offset((FieldViewModel.brickSizeLandscape * (GameConfig.ROWS + 2)) / 2),
////            .border(4.dp, Color.Magenta),
//        verticalArrangement = Arrangement.Center
//
//    ) {
//        (BricksViewModel.bricks.forEachIndexed { index, brick ->
//
//            key(brick.id) {
//                Box(
//                    Modifier
//                        .offset { IntOffset(brick.x.intValue, brick.y.intValue) }
//                        .size(FieldViewModel.brickSizeLandscape)
//                        .background(GameConfig.BRICK_BG_COLOR)
//                        .graphicsLayer {
//                            if (AnimationsBrick.canRunTranslation.value && !brick.wasAnimated.value) {
//                                translationY = brick.translationY.value
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
//            AnimationsBrick.InitAnimationTranslationY(brick)
//            AnimationsBrick.runAnimationTranslation(brick, index)
//        })
//    }
//}
//
//@Composable
//private fun BonusBlock() {
//    val coroutine = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .offset(x = -((FieldViewModel.brickSizeLandscape * (GameConfig.ROWS + 2)) / 2))
////            .border(4.dp, Color.Magenta),
//    ) {
//        BonusViewModel.bonuses.forEach {
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp))
//                    .border(
//                        GameConfig.BRICK_BORDER_SIZE.dp, color = it.activeBonusBorder.value,
//                        shape = RoundedCornerShape(GameConfig.BRICK_ROUNDED_CORNER.dp)
//                    )
//                    .size(FieldViewModel.brickSizeLandscape)
//                    .background(GameConfig.FIELD_BG_COLOR)
//            ) {}
//            Spacer(Modifier.size(10.dp))
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .offset(x = -((FieldViewModel.brickSizeLandscape * (GameConfig.ROWS + 2)) / 2))
////            .border(4.dp, Color.Magenta),
//    ) {
//        (BonusViewModel.bonuses.forEach {
//            key(it.id) {
//                Box(
//                    Modifier
//                        .offset { IntOffset(it.x.intValue, it.y.intValue) }
//                        .size(FieldViewModel.brickSizeLandscape)
//                        .paint(
//                            painterResource(it.assetImage),
//                            alpha = it.alpha.value,
//                            sizeToIntrinsics = true,
//                            contentScale = ContentScale.FillBounds
//                        )
//                        .onGloballyPositioned { coordinates ->
//                            it.setGloballyPosition(coordinates)
//                        }
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragStart = { },
//                                onDrag = { _, dragAmount ->
//                                    if (it.canDrag) {
//                                        it.dragging(dragAmount.x, dragAmount.y)
//                                        coroutine.launch {
//                                            CollisionBricksOnLevel.observeCenterObjects(it)
//                                        }
//                                    }
//                                },
//                                onDragEnd = {
//                                    if (it.canDrag) {
//                                        coroutine.launch {
//                                            it.takeAPlaces()
////                                        FieldViewModel.checkFieldOnFinishRound()
//                                        }
//                                    }
//                                },
//                                onDragCancel = { },
//                            )
//                        }
//                )
//                Spacer(Modifier.size(10.dp))
//            }
//        })
//    }
//}