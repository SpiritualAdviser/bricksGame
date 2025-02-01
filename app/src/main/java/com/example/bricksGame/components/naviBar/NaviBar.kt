package com.example.bricksGame.components.naviBar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel

@Composable
fun ButtonNaviBar() {
    ButtonSound()
    Spacer(Modifier.size(15.dp))
    ButtonClose()
}

@Composable
fun ButtonSound(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    IconToggleButton(
        checked = homeScreenViewModel.gameConfig.SOUND_MUTED, onCheckedChange = {
            homeScreenViewModel.soundController.soundMute()
        },
        modifier = Modifier
            .size(40.dp)
            .paint(
                painter = if (homeScreenViewModel.gameConfig.SOUND_MUTED) painterResource(R.drawable.play_muted)
                else painterResource(R.drawable.play),
                contentScale = ContentScale.FillWidth
            )
    ) {}
}

@Composable
fun ButtonClose(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    IconButton(
        onClick = {
            homeScreenViewModel.buttonController.navigateToHome()
        },

        modifier = Modifier
            .size(40.dp)
            .paint(
                painter = painterResource(R.drawable.close),
                contentScale = ContentScale.FillWidth
            )
    )
    {}
}

@Composable
fun ButtonNavigateHome(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    Button(
        onClick = {  homeScreenViewModel.buttonController.navigateToHome() },
        modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//        modifier = Modifier
//            .size(100.dp, 80.dp)
//            .paint(
//                painter = painterResource(R.drawable.buttons_empty),
//                contentScale = ContentScale.FillWidth
//            )
    ) { Text("Menu") }
}


