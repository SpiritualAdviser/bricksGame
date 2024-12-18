package com.example.bricksGame.components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bricksGame.components.popups.models.OnFinishGameViewModel
import com.example.bricksGame.ui.theme.overlayBg
import com.example.bricksGame.ui.theme.tertiaryContainerLight

@Composable
fun WinPopup() {
    Overlay()
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WinBlock()
    }
}

@Composable
fun WinBlock() {

    Text(
        text = OnFinishGameViewModel.textOnWinPopup,
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        color = tertiaryContainerLight
    )
}

@Composable
fun Overlay() {
    Box(
        Modifier
            .fillMaxSize()
            .clickable(enabled = false, onClick = {})
            .background(overlayBg)
    ) {
    }
}