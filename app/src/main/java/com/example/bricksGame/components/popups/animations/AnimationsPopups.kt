package com.example.bricksGame.components.popups.animations

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.popups.models.PopupsViewModel
import kotlinx.coroutines.delay

@Composable
fun RunAnimationScale(popupsViewModel: PopupsViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        popupsViewModel.scalePopupWinLine.animateTo(
            initialVelocity = 1F,
            targetValue = 2.5f,
            animationSpec = tween(durationMillis = 200),

        )
        delay(300)
        popupsViewModel.scalePopupWinLine.snapTo(1F)
        popupsViewModel.closePopupOnWinLine()
    }
}


