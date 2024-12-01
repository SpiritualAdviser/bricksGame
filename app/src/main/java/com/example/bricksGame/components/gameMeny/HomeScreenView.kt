package com.example.bricksGame.components.gameMeny

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun RunHomeScreen() {
    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Image(
            painter = painterResource(HomeScreenViewModel.imageBgLandscape),
            contentDescription = "levelBg",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(HomeScreenViewModel.imageBgPortrait),
            contentDescription = "levelBg",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
    }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()

    ) {
        IconToggleButton(
            checked = GameConfig.SOUND_MUTED, onCheckedChange = {
                soundController.soundMute()
            },
            modifier = Modifier
                .size(60.dp, 60.dp)
                .align(Alignment.TopEnd)
                .paint(
                    painter = if (GameConfig.SOUND_MUTED) painterResource(R.drawable.play_muted)
                    else painterResource(R.drawable.play),
                    contentScale = ContentScale.FillWidth
                )
        ) {}
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
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Start") }

        IconButton(
            onClick = { ButtonController.navigateLevelGame() },
            modifier = Modifier
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Players") }
    }
}




