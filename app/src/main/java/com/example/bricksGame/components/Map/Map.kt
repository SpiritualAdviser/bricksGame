package com.example.bricksGame.components.Map

import com.example.bricksGame.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.components.Map.models.MapModel
import com.example.bricksGame.ui.Level
import com.example.bricksGame.ui.MainMenuBg


@Composable
fun Map() {
    MainMenuBg()
    Box(
        Modifier.fillMaxSize(),

        ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
//                .background(onBackgroundLight),

            columns = GridCells.Adaptive(minSize = 50.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(MapModel.levelList) {
                LevelOnMap(it)
            }
        }
    }
}

@Composable
fun LevelOnMap(level: Level) {
    IconButton(
        onClick = { },
        modifier = Modifier
            .size(50.dp, 60.dp)
            .paint(
                painter = painterResource(R.drawable.map_levels_bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Text("${level.numberLevel}", fontSize = 20.sp)
    }
}