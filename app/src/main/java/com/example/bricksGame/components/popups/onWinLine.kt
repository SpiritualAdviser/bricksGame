package com.example.bricksGame.components.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset

import androidx.compose.ui.unit.sp


import com.example.bricksGame.components.popups.models.PopupsViewModel
import com.example.bricksGame.ui.theme.backgroundDark


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WinLine() {
    Text(
        PopupsViewModel.textWinLine.value,
        modifier = Modifier
            .offset {
                IntOffset(
                    PopupsViewModel.xPopupWinLine.intValue,
                    PopupsViewModel.yPopupWinLine.intValue
                )
            },
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}

