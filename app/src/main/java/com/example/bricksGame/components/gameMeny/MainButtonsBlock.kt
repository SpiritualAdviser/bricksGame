package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.map.models.MapModel
import com.example.bricksGame.ui.theme.buttonText


@Composable
fun MainButtonsBlock(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    mapModel: MapModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToMap() },
            modifier = Modifier
                .size(100.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Adventure", fontSize = 13.sp, color = buttonText, fontWeight = FontWeight.Bold) }

        IconButton(
            onClick = {
                homeScreenViewModel.buttonController.navigateFreeGame(mapModel)
            },
            modifier = Modifier
                .size(100.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Free game", fontSize = 13.sp, color = buttonText, fontWeight = FontWeight.Bold) }

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToPlayers() },
            modifier = Modifier
                .size(100.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Players", fontSize = 13.sp, color = buttonText, fontWeight = FontWeight.Bold) }

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToInfo() },
            modifier = Modifier
                .size(100.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) { Text("Info", fontSize = 13.sp, color = buttonText, fontWeight = FontWeight.Bold) }
    }
}
