package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.naviBar.ButtonSound
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.ui.theme.gameVersionText

@Composable
fun RunHomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {

    MainMenuBg()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        ButtonSound()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4F),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.logo),
                modifier = Modifier.size(260.dp, 130.dp),
                contentDescription = "logo"
            )
//          LogoSprite()
//            Logo()
        }

        MainButtonsBlock()
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd


    ) {
        Text("Game version ${homeScreenViewModel.gameConfig.GAME_VERSION}", color = gameVersionText)
    }

}




