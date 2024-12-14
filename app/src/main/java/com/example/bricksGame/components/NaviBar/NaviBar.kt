package com.example.bricksGame.components.NaviBar

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
import com.example.bricksGame.R
import com.example.bricksGame.soundController
import com.example.bricksGame.ui.GameConfig
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun ButtonNaviBar() {
    ButtonSound()
    Spacer(Modifier.size(15.dp))
    ButtonClose()
}

@Composable
fun ButtonSound() {
    IconToggleButton(
        checked = GameConfig.SOUND_MUTED, onCheckedChange = {
            soundController.soundMute()
        },
        modifier = Modifier
            .size(40.dp)
            .paint(
                painter = if (GameConfig.SOUND_MUTED) painterResource(R.drawable.play_muted)
                else painterResource(R.drawable.play),
                contentScale = ContentScale.FillWidth
            )
    ) {}
}

@Composable
fun ButtonClose() {
    IconButton(
        onClick = {
            ButtonController.navigateToHome()
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
fun ButtonNavigateHome() {
    Button(
        onClick = { ButtonController.navigateToHome() },
        modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//        modifier = Modifier
//            .size(100.dp, 80.dp)
//            .paint(
//                painter = painterResource(R.drawable.buttons_empty),
//                contentScale = ContentScale.FillWidth
//            )
    ) { Text("Menu") }
}


