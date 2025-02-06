package com.example.bricksGame.helper

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp
import com.example.bricksGame.config.Level
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenSize @Inject constructor() {
    var screenWidthPx = 0
    var screenHeightPx = 0
    var screenWidthDp = 0.dp
    var screenHeightDp = 0.dp
    var density = 0f
    var isPortraitMode: Boolean = true

    init {
        Log.d("my", "ScreenSize_init")
    }

    @Composable
    fun GetScreenSize() {
        val displayMetrics = LocalContext.current.resources.displayMetrics

        // Width and height of screen
        this.screenWidthPx = displayMetrics.widthPixels
        this.screenHeightPx = displayMetrics.heightPixels
        // Device density
        density = displayMetrics.density

        LocalDensity.current
        val configuration = LocalConfiguration.current
        this.screenWidthDp = configuration.screenWidthDp.dp
        this.screenHeightDp = configuration.screenHeightDp.dp

        isPortraitMode = screenWidthDp > screenHeightDp
    }
}



