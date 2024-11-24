package com.example.bricksGame.components.levelGame

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import com.example.bricksGame.R
import com.example.bricksGame.soundController

@Composable
fun RunLevelGame() {
    soundController.playLevelTheme()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkGray)),
    ) {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeLayout()
        } else {
            PortraitLayout()
        }
    }
}





