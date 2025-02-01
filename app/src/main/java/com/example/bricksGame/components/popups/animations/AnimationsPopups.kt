//package com.example.bricksGame.components.popups.animations
//
//import androidx.compose.animation.core.tween
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import com.example.bricksGame.components.popups.models.PopupsViewModel
//
//object AnimationsPopups {
//
//    @Composable
//    fun RunAnimationScale() {
//
//        LaunchedEffect(Unit) {
//            PopupsViewModel.scalePopupWinLine.animateTo(
//                targetValue = 2.5f,
//                animationSpec = tween(durationMillis = 200),
//            )
//        }
//    }
//}
//
