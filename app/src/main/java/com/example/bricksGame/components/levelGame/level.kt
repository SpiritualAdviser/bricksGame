package com.example.bricksGame.components.levelGame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.ui.helper.buttonController


@Composable
fun LevelGame() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGray)),
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier.padding(10.dp),

                onClick = { buttonController.buttonLisener("mainMeny") }) {
                Text("Home")
            }

            Box(
                Modifier
                    .size(fieldBricks.width, fieldBricks.height)
                    .background(Color.Gray)
            ) {
                SetBricksContent()
            }
        }
    }
}