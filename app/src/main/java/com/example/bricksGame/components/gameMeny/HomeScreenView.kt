package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun RunHomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home"
        )
        Button(onClick = {
            ButtonController.navigateLevelGame()
        }) {
            Text("Start")
        }
    }
}




