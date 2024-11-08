package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

class HomeScreen(
    private var navController: NavController,
    private val nameDescription: String = "level"
) {

    @Composable
    fun Run() {
        MainBox()
    }

    @Composable
    fun MainBox(
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.DarkGray)
                .fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                text = "${nameDescription}!",
            )
            Button(onClick = { navController.navigate("LevelGame") }) {
                Text("Start")
            }
        }
    }
}