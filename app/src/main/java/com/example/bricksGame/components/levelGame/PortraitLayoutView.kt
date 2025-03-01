package com.example.bricksGame.components.levelGame

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.levelGame.animations.InitAnimationTranslationX
import com.example.bricksGame.components.levelGame.animations.RunAnimationScaleOnPlace
import com.example.bricksGame.components.levelGame.models.BonusViewModel
import com.example.bricksGame.components.levelGame.models.BricksViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.popups.WinLine
import com.example.bricksGame.components.popups.WinPopup
import com.example.bricksGame.components.popups.animations.RunAnimationScale
import com.example.bricksGame.components.popups.models.PopupsViewModel
import com.example.bricksGame.helper.LevelPortraitBg

@Composable
fun PortraitLayout(popupsViewModel: PopupsViewModel = hiltViewModel()) {
    LevelPortraitBg()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        FieldBlock()
    }
    if (popupsViewModel.showPopupWinLine.value) {
        RunAnimationScale()
        WinLine()
    }

    if (popupsViewModel.showPopupOnFinishGame.value) {
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
//            .onGloballyPositioned { coordinates ->
//                fieldViewModel.setFieldSizeOnCollision(coordinates)
//            }
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
                RunAnimationScaleOnPlace(placeOnField)
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
                        .scale(placeOnField.animation.scaleAnimation.value)
                        .paint(
                            painter = fieldViewModel.getBitmapPainter(placeOnField.slot.value),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )

                        .onGloballyPositioned { coordinates ->
                            fieldViewModel.setGloballyPosition(placeOnField, coordinates)
                        }
                        .clickable { fieldViewModel.onClick(placeOnField) }
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
        (bricksViewModel.bricks.forEachIndexed { index, brick ->

            key(brick.baseModel.id) {

                Box(
                    Modifier
                        .zIndex(brick.baseModel.zIndex.value)
                        .offset { IntOffset(brick.cords.x.intValue, brick.cords.y.intValue) }
                        .size(bricksViewModel.brickSize.value)
                        .background(bricksViewModel.brickBgColor)
                        .graphicsLayer {
                            if (bricksViewModel.canRunTranslation.value && !brick.animation.wasAnimated.value) {
                                translationX = brick.animation.translationX.value
                            }
                        }
                        .paint(
                            painter = brick.baseModel.getBitmapPainter(),
                            sizeToIntrinsics = true,
                            contentScale = ContentScale.FillBounds
                        )

                        .clip(RoundedCornerShape(bricksViewModel.brickCorner))
                        .onGloballyPositioned { coordinates ->
                            bricksViewModel.setGloballyPosition(brick, coordinates)
                        }
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { bricksViewModel.canRunTranslation.value = true },
                                onDrag = { _, dragAmount ->
                                    bricksViewModel.dragging(brick, dragAmount)
                                },
                                onDragEnd = {
                                    bricksViewModel.onDragEnd(brick)
                                },
                                onDragCancel = { bricksViewModel.onDragCancel(brick) },
                            )
                        }
                )
                Spacer(Modifier.size(10.dp))
            }
            InitAnimationTranslationX(brick)
            bricksViewModel.runAnimationTranslation(brick, index)
        })
    }
}


@Composable
private fun BonusBlock(
    bonusViewModel: BonusViewModel = hiltViewModel(),
) {

    Box(
        Modifier
            .zIndex(bonusViewModel.zIndexBonusBlock.floatValue)
    ) {
        Row(
            modifier = Modifier
//                .border(4.dp, Color.Magenta),
        ) {
            bonusViewModel.bonuses.forEach { bonus ->
                key(bonus.baseModel.id) {
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
        }

        Row(
            modifier = Modifier
        ) {
            bonusViewModel.bonuses.forEach { bonus ->
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
                                        if (bonus.baseModel.alpha.value >= 1F) {
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
            }
        }
    }
}