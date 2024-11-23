package com.example.bricksGame.components.gameMeny

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.levelGame.PortraitLayout
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            ButtonController.navigateLevelGame()
        }) {
            Text("Start")
        }
    }
}




