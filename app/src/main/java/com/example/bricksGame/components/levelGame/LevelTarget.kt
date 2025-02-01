package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bricksGame.components.map.models.MapModel
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
    Column(
    ) {
        BlockLevelTarget()
    }
}

@Composable
fun BlockLevelTarget() {
    Text(text = "Target: ${MapModel.levelTarget.intValue}", color = onPrimaryLight)
    Text("winLine: ${MapModel.levelWinLine}", color = onPrimaryLight)
    Text("Step: ${MapModel.levelStep.intValue}", color = onPrimaryLight)
}