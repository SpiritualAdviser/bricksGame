package com.example.bricksGame.components.NaviBar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
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
            ButtonController.navigateHome()
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


