package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.bricksGame.R
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun RunHomeScreen() {
    Image(
        painter = painterResource(id = R.drawable.bg_level_dor),
        contentDescription = "levelBg",
        modifier = Modifier.fillMaxHeight(),
        contentScale = ContentScale.Crop
    )

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




