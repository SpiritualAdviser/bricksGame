package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.naviBar.ButtonSound
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.helper.ButtonController

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
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { ButtonController.navigateToMap() },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 40.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Adventure") }
        Spacer(Modifier.size(10.dp))

        Button(
            onClick = { ButtonController.navigateToLevelGame(true) },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 40.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Free game") }
        Spacer(Modifier.size(10.dp))
        Button(
            onClick = { ButtonController.navigateToPlayers() },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 40.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Players") }
        Spacer(Modifier.size(10.dp))
        Button(
            onClick = { ButtonController.navigateToOptions() },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 40.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Options") }
    }
}




