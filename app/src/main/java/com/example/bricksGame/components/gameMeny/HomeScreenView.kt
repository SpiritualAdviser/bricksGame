package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.naviBar.ButtonSound
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.ui.theme.gameVersionText

@Composable
fun RunHomeScreen() {

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
//            Image(
//                bitmap = ImageBitmap.imageResource(R.drawable.logo),
//                modifier = Modifier.size(260.dp, 130.dp),
//                contentDescription = "logo"
//            )
            Logo()
        }

        MainButtonsBlock()
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd


    ) {
        Text("Game version ${GameConfig.GAME_VERSION}", color = gameVersionText)
    }

}




