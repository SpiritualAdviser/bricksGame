package com.example.bricksGame.components.Map

import com.example.bricksGame.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.components.Map.models.MapModel
import com.example.bricksGame.components.NaviBar.ButtonNaviBar
import com.example.bricksGame.ui.Level
import com.example.bricksGame.ui.MainMenuBg


@Composable
fun Map() {
    MainMenuBg()
    Row(

        modifier = Modifier
            .fillMaxWidth()
            .offset((-18).dp, 30.dp),
//            .border(4.dp, Color.Magenta),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonNaviBar()
    }

    Box(
        Modifier.fillMaxSize(),

        ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, start = 50.dp, end = 50.dp, bottom = 50.dp),
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
        onClick = { MapModel.runLevel(level) },
        enabled = level.isActive,
        modifier = Modifier
            .size(50.dp, 60.dp)
            .paint(
                painter = painterResource(R.drawable.map_levels_bg),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                    setToSaturation(
                        if (level.isActive) 1F else 0.4F
                    )
                })
            )
    ) {
        Text("${level.numberLevel}", fontSize = 20.sp)
    }
}