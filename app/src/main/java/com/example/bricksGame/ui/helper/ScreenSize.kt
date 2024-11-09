package com.example.bricksGame.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp

class ScreenSize {
    private var screenWidthPx = 0
    private var screenHeightPx = 0
    private var screenWidthDp = 0.dp
    private var screenHeightDp = 0.dp

    @Composable
    fun GetScreenSize(screenSize: ScreenSize) {
        val context = LocalContext.current
        val displayMetrics = context.resources.displayMetrics

        // Width and height of screen
        this.screenWidthPx = displayMetrics.widthPixels
        this.screenHeightPx = displayMetrics.heightPixels
        // Device density
        val density = displayMetrics.density

        val densityLd = LocalDensity.current
        val configuration = LocalConfiguration.current
        this.screenWidthDp = with(densityLd) { configuration.screenWidthDp.dp }
        this.screenHeightDp = with(densityLd) { configuration.screenHeightDp.dp }
    }
}

