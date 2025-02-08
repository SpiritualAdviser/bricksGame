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
import com.example.bricksGame.components.levelGame.animations.AnimationsBrick
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.players.PlayerScoreBlock
import com.example.bricksGame.helper.LevelLandscapeBg
import kotlinx.coroutines.launch

@Composable
fun LandscapeLayout() {

    LevelLandscapeBg()
    ButtonBlock()
    LeftBar()
    FieldOnLevel()
    BonusBlock()
    BricksBlock()

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
private fun FieldOnLevel(fieldViewModel: FieldViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(fieldViewModel.placeCorner.dp))
            .background(fieldViewModel.fieldBgColor)
            .onGloballyPositioned { coordinates ->
                fieldViewModel.setFieldSizeOnCollision(coordinates)
            }
//            .border(4.dp, Color.Green)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(fieldViewModel.fieldRows),
            horizontalArrangement = Arrangement.Center,
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
                            placeOnField.baseModel.activeBorderColor.value,
                            RoundedCornerShape(fieldViewModel.placeCorner.dp)
                        )
                        .size(fieldViewModel.placeSizeOnField.value)
                        .background(fieldViewModel.placeBgColor)
                        .paint(
                            painter = fieldViewModel.getBitmapPainter(placeOnField.slot.value),

                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .onGloballyPositioned { coordinates ->
                            fieldViewModel.setGloballyPosition(placeOnField, coordinates)
                        }
                )
            }
        }
    }
}

@Composable
private fun BricksBlock(bricksViewModel: BricksViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .offset(bricksViewModel.offsetBricksBlock),
//            .border(4.dp, Color.Magenta),
        verticalArrangement = Arrangement.Center

    ) {
        (bricksViewModel.bricks.forEachIndexed { index, brick ->

            key(brick.baseModel.id) {
                Box(
                    Modifier
                        .zIndex(brick.baseModel.zIndex.value)
                        .offset { IntOffset(brick.cords.x.intValue, brick.cords.y.intValue) }
                        .size(bricksViewModel.brickSize.value)
                        .background(bricksViewModel.brickBgColor)
//                        .graphicsLayer {
//                            if (AnimationsBrick.canRunTranslation.value && !brick.animation.wasAnimated.value) {
//                                translationY = brick.animation.translationY.value
//                            }
//                        }
                        .paint(
                            painterResource(brick.baseModel.assetImage),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )
                        .clip(RoundedCornerShape(bricksViewModel.brickCorner))
                        .onGloballyPositioned { coordinates ->
                            bricksViewModel.setGloballyPosition(brick, coordinates)
                        }
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
//            AnimationsBrick.InitAnimationTranslationY(brick)
//            AnimationsBrick.runAnimationTranslation(brick, index)
        })
    }
}

@Composable
private fun BonusBlock(bonusViewModel: BonusViewModel = hiltViewModel()) {

    Box(
        Modifier
            .offset(x = bonusViewModel.offsetBonusBlock)
            .zIndex(bonusViewModel.zIndexBonusBlock.floatValue)
    ) {

        Column(
            modifier = Modifier
//            .border(4.dp, Color.Magenta),
        ) {
            bonusViewModel.bonuses.forEach { bonus ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bonusViewModel.bonusCorner))
                        .border(
                            bonusViewModel.bonusBorderSize,
                            color = bonus.baseModel.activeBorderColor.value,
                            shape = RoundedCornerShape(bonusViewModel.bonusCorner)
                        )
                        .size(bonusViewModel.bonusSize.value)
                        .background(bonusViewModel.bonusBgColor)
                ) {}
                Spacer(Modifier.size(10.dp))
            }
        }
        Column(
            modifier = Modifier
//            .border(4.dp, Color.Magenta),
        ) {
            (bonusViewModel.bonuses.forEach { bonus ->
                key(bonus.baseModel.id) {
                    Box(
                        Modifier
                            .zIndex(bonus.baseModel.zIndex.value)
                            .offset { IntOffset(bonus.cords.x.intValue, bonus.cords.y.intValue) }
                            .size(bonusViewModel.bonusSize.value)
                            .paint(
                                painterResource(bonus.baseModel.assetImage),
                                alpha = bonus.baseModel.alpha.value,
                                sizeToIntrinsics = true,
                                contentScale = ContentScale.FillBounds
                            )
                            .onGloballyPositioned { coordinates ->
                                bonusViewModel.setGloballyPosition(bonus, coordinates)
                            }
                            .pointerInput(bonus) {
                                detectDragGestures(
                                    onDragStart = {},
                                    onDrag = { _, dragAmount ->
                                        if (bonus.cords.canDrag) {
                                            bonusViewModel.dragging(bonus, dragAmount)
                                        }
                                    },
                                    onDragEnd = {
                                        bonusViewModel.onDragEnd(bonus)
                                    },
                                    onDragCancel = {},
                                )
                            }
                    )
                    Spacer(Modifier.size(10.dp))
                }
            })
        }
    }
}