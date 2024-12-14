package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.Map.models.MapModel
import com.example.compose.onPrimaryLight

@Composable
fun LevelTargetBlockPortrait() {
    Row(
        modifier = Modifier.fillMaxWidth(0.9F),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BlockLevelTarget()
    }
}

@Composable
fun LevelTargetBlockLandscape() {
    Column(
        Modifier.offset(18.dp)
    ) {
        BlockLevelTarget()
    }
}

@Composable
fun BlockLevelTarget() {
    Text(text = "Target: ${MapModel.levelTarget.value}", color = onPrimaryLight)
    Text("winLine: ${MapModel.levelWinLine}", color = onPrimaryLight)
    Text("Time: ${MapModel.levelTime.intValue}", color = onPrimaryLight)
}