package com.example.bricksGame.components.gameMeny

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bricksGame.AppNavigation
import com.example.bricksGame.Routes

class HomeScreenView() {
    private val navController: NavController = AppNavigation.getInstance().getNavController()

    @Composable
    fun Run(
        viewModelHS: HomeScreenViewModel = viewModel(),
    ) {
        val titleDescription by viewModelHS.titleDescription.observeAsState()
        Column(
            modifier = Modifier
                .background(color = Color.DarkGray)
                .fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                text = "$titleDescription"
            )
            Button(onClick = {
                viewModelHS.setNameOnHomeScreen("HomeBack")
                navController.navigate(Routes.LevelGame.route)
            }) {
                Text("Start")
            }
        }
    }
}



