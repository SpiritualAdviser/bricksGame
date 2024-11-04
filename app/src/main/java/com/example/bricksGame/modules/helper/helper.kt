package com.example.bricksGame.modules.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp

class Helper {

}

class ScreenSize {
    var screenWidthPx = 0
    var screenHeightPx = 0
    var screenWidthDp = 0.dp
    var screenHeightDp = 0.dp
}

@Composable
fun GetScreenSize(screenSize: ScreenSize) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics

    // Width and height of screen
    screenSize.screenWidthPx = displayMetrics.widthPixels
    screenSize.screenHeightPx = displayMetrics.heightPixels
    // Device density
    val density = displayMetrics.density

    val densityLd = LocalDensity.current
    val configuration = LocalConfiguration.current
    screenSize.screenWidthDp = with(densityLd) { configuration.screenWidthDp.dp }
    screenSize.screenHeightDp = with(densityLd) { configuration.screenHeightDp.dp }
}
