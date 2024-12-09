package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.components.NaviBar.ButtonSound
import com.example.bricksGame.ui.MainMenuBg
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun RunHomeScreen() {

    MainMenuBg()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        ButtonSound()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { ButtonController.navigateLevelGame() },
            modifier = Modifier
                .size(100.dp, 60.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Start") }

        IconButton(
            onClick = { ButtonController.navigatePlayers() },
            modifier = Modifier
                .size(100.dp, 60.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Players") }

        IconButton(
            onClick = { ButtonController.navigateOptions() },
            modifier = Modifier
                .size(100.dp, 60.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Options") }
    }
}




