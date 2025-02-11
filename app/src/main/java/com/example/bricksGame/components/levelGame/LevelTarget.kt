package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.levelGame.models.FieldViewModel
import com.example.bricksGame.ui.theme.onPrimaryLight

@Composable
fun LevelTargetBlockPortrait() {
    Row(
        modifier = Modifier.fillMaxWidth(1F),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BlockLevelTarget()
    }
}

@Composable
fun LevelTargetBlockLandscape() {
    Column {
        BlockLevelTarget()
    }
}

@Composable
fun BlockLevelTarget(fieldViewModel: FieldViewModel = hiltViewModel()) {
    Text(text = "Target: ${fieldViewModel.levelTarget.intValue}", color = onPrimaryLight)
    Text("winLine: ${fieldViewModel.levelWinLine.intValue}", color = onPrimaryLight)
    Text("Step: ${fieldViewModel.levelStep.intValue}", color = onPrimaryLight)
}