package com.example.bricksGame.components.options

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.ui.MainMenuBg
import com.example.bricksGame.ui.helper.ButtonController

@Composable
fun Options() {
    MainMenuBg()
    Column {
        IconButton(
            onClick = { ButtonController.navigateHome() },
            modifier = Modifier
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Menu") }
    }
}