package com.example.bricksGame.components.gameMeny

import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.levelGame.PortraitLayout
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun RunHomeScreen() {
    val orientation = LocalConfiguration.current.orientation
    val checked = remember { mutableStateOf(false) }

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
                    painter = painterResource(R.drawable.buttons_start),
                    contentScale = ContentScale.FillWidth
                )

        ) {}

        IconToggleButton(
            checked = checked.value, onCheckedChange = {
                checked.value = it
                GameConfig.SOUND_MUTED = checked.value
                println(checked.value)
            },
            modifier = Modifier
                .size(60.dp, 60.dp)
                .paint(
                    painter = if (checked.value) painterResource(R.drawable.play_muted)
                    else painterResource(R.drawable.play),
                    contentScale = ContentScale.FillWidth
                )
        ) {}
    }
}




