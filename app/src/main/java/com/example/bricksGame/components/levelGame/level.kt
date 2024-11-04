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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bricksGame.R
import com.example.bricksGame.Routes


@Composable
fun LevelGame(navController: NavHostController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGray)),
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier.padding(10.dp),

                onClick = { navController.navigate(Routes.MainMeny.route) }) {
                Text("Home")
            }

            Box(
                Modifier
                    .size(200.dp)
                    .background(colorResource(R.color.teal_700))
            )
        }
    }
}