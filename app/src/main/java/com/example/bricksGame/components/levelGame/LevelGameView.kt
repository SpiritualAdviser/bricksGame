package com.example.bricksGame.components.levelGame

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.FieldViewModel

@Composable
fun RunLevelGame(fieldViewModel: FieldViewModel = hiltViewModel()) {
    BackHandler {
        if (fieldViewModel.freeGame) {
            fieldViewModel.navigateToHome()
        } else {
            fieldViewModel.navigateToMap()
        }
    }

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





