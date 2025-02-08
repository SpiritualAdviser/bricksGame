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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
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
        FieldOnLevel()
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
private fun FieldOnLevel(fieldViewModel: FieldViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(fieldViewModel.placeCorner.dp))
            .background(fieldViewModel.fieldBgColor)
//            .border(4.dp, Color.Green)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(fieldViewModel.fieldRows),
            userScrollEnabled = false,
            modifier = Modifier
                .size(
                    fieldViewModel.fieldWidth.value,
                    fieldViewModel.fieldHeight.value
                )
        ) {

            items(fieldViewModel.placesOnField) { placeOnField ->

                Box(
                    Modifier
                        .clip(RoundedCornerShape(fieldViewModel.placeCorner.dp))
                        .border(
                            fieldViewModel.placeBorderSize,
                            fieldViewModel.placeBorderColor.value,
                            RoundedCornerShape(fieldViewModel.placeCorner.dp)
                        )
                        .size(fieldViewModel.placeSizeOnField.value)
                        .background(fieldViewModel.placeBgColor)
                        .paint(
                            painter = fieldViewModel.getBitmapPainter(placeOnField.slot.value),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                    //                        .onGloballyPositioned { coordinates ->
//                            it.setGloballyPosition(coordinates)
                )
            }
        }
    }
}


@Composable
private fun BricksBlock(bricksViewModel: BricksViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier
//            .border(4.dp, Color.Magenta),
    ) {
        (bricksViewModel.bricks.forEachIndexed { _, brick ->

            key(brick.baseModel.id) {

                Box(
                    Modifier
                        .zIndex(brick.baseModel.zIndex.value)
                        .offset { IntOffset(brick.cords.x.intValue, brick.cords.y.intValue) }
                        .size(bricksViewModel.brickSize.value)
                        .background(bricksViewModel.brickBgColor)
//                        .graphicsLayer {
//                            if (AnimationsBrick.canRunTranslation.value && !brick.animation.wasAnimated.value) {
//                                translationX = brick.animation.translationX.value
//                            }
//                        }
                        .paint(
                            painterResource(brick.baseModel.assetImage),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(bricksViewModel.brickCorner))
//                        .onGloballyPositioned { coordinates ->
//                            brick.setGloballyPosition(coordinates)
//                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
//                                onDragStart = { AnimationsBrick.canRunTranslation.value = true },
                                onDrag = { _, dragAmount ->
                                    bricksViewModel.dragging(brick, dragAmount)
                                },
                                onDragEnd = {
                                    bricksViewModel.onDragEnd(brick)
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


//@Composable
//private fun BonusBlock(
//    bonusViewModel: BonusViewModel = hiltViewModel(),
//    fieldViewModel: FieldViewModel = hiltViewModel()
//) {
//    val coroutine = rememberCoroutineScope()
//    Box(
//        Modifier
//            .zIndex(fieldViewModel.zIndex.floatValue)
//    ) {
//        Row(
//            modifier = Modifier
////                .border(4.dp, Color.Magenta),
//        ) {
//            bonusViewModel.bonuses.forEach {
//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(bonusViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp))
//                        .border(
//                            bonusViewModel.gameConfig.BRICK_BORDER_SIZE.dp,
//                            color = it.activeBonusBorder.value,
//                            shape = RoundedCornerShape(bonusViewModel.gameConfig.BRICK_ROUNDED_CORNER.dp)
//                        )
//                        .size(fieldViewModel.brickSizePortrait)
//                        .background(bonusViewModel.gameConfig.FIELD_BG_COLOR)
//                ) {}
//                Spacer(Modifier.size(10.dp))
//            }
//        }
//
//        Row(
//            modifier = Modifier
//        ) {
//            bonusViewModel.bonuses.forEach {
//                key(it.id) {
//                    val currentBrick = it
//                    Box(
//                        Modifier
//                            .zIndex(it.zIndex.value)
//                            .offset { IntOffset(it.x.intValue, it.y.intValue) }
//                            .size(fieldViewModel.brickSizePortrait)
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
//                                        fieldViewModel.changeZIndex(1F)
//                                        currentBrick.changeZIndex(1F)
//                                    },
//                                    onDrag = { _, dragAmount ->
//                                        if (it.canDrag) {
//                                            it.dragging(dragAmount.x, dragAmount.y)
//                                            coroutine.launch {
//                                                fieldViewModel.collisionBricksOnLevel.observeCenterObjects(
//                                                    it
//                                                )
//                                            }
//                                        }
//                                    },
//                                    onDragEnd = {
//                                        if (it.canDrag) {
//                                            coroutine.launch {
//                                                it.takeAPlaces()
//                                            }
//                                        }
//                                        fieldViewModel.changeZIndex(0F)
//                                        it.changeZIndex(0F)
//                                    },
//                                    onDragCancel = {
//                                        fieldViewModel.changeZIndex(0F)
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
