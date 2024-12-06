package com.example.bricksGame.components.players

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.R
import com.example.bricksGame.components.players.models.PlayerViewModel

@Composable
fun PlayerScore() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.score_icon),
            contentDescription = "scoreIcon",
            Modifier.size(40.dp)
        )
        Spacer(Modifier.size(10.dp))
        Text(
            text = PlayerViewModel.playerScore.intValue.toString(),
            fontSize = 25.sp,
            color = Color.White
        )
    }
}
