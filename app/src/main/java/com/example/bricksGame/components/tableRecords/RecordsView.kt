package com.example.bricksGame.components.tableRecords

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.tableRecords.model.RecordsViewModel
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.internet.PlayerAchievement
import com.example.bricksGame.ui.theme.activePlayerBgCard
import com.example.bricksGame.ui.theme.errorContainerDarkMediumContrast
import com.example.bricksGame.ui.theme.onPrimaryLight
import com.example.bricksGame.ui.theme.outlineVariantDark
import com.example.bricksGame.ui.theme.playerTextDark
import com.example.bricksGame.ui.theme.playersBgBlock

@Composable
fun Records() {
    MainMenuBg()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        ButtonNaviBar()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecordsList()
    }
}

@Composable
fun RecordsList(recordsViewModel: RecordsViewModel = hiltViewModel()) {

    Text("Records", fontSize = 22.sp, color = onPrimaryLight)
    Spacer(Modifier.size(4.dp))
    LazyColumn(
        Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.7f)
            .clip(RoundedCornerShape(5.dp))
            .background(playersBgBlock)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = recordsViewModel.playerRecords) { index, item ->

            PlayerRecordCard(item, index)
        }
    }
}

@Composable
fun PlayerRecordCard(playerAchievement: PlayerAchievement, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(activePlayerBgCard)
            .padding(7.dp)
        ,

        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
            Text("$index")
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                Icons.Filled.Face,
                contentDescription = "player",
            )
            Text(playerAchievement.name, fontSize = 14.sp, color = playerTextDark)
        }
        Row {
            Text(
                "achiev: ${playerAchievement.achievements}",
                fontSize = 14.sp,
                color = playerTextDark
            )
        }
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Filled.Star, contentDescription = "Star",
                    tint = if (playerAchievement.active) {
                        errorContainerDarkMediumContrast
                    } else {
                        outlineVariantDark
                    }
                )
            }
        }
    }
}