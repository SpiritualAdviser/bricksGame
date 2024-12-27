package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.R


@Composable
fun MainButtonsBlock() {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        IconButton(
            onClick = { ButtonController.navigateToMap() },
            modifier = Modifier
                .size(95.dp, 42.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Adventure", fontSize = 13.sp) }

        IconButton(
            onClick = { ButtonController.navigateFreeGame() },
            modifier = Modifier
                .size(95.dp, 42.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Free game", fontSize = 13.sp) }

        IconButton(
            onClick = { ButtonController.navigateToPlayers() },
            modifier = Modifier
                .size(95.dp, 42.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Players", fontSize = 13.sp) }

        IconButton(
            onClick = { ButtonController.navigateToDescription() },
            modifier = Modifier
                .size(95.dp, 42.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Info", fontSize = 13.sp) }
    }
}
