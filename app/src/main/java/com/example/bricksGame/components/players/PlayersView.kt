package com.example.bricksGame.components.players

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.ui.data.Player
import com.example.bricksGame.ui.helper.ButtonController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerView() {

    val orientation = LocalConfiguration.current.orientation
    val snackState = remember { SnackbarHostState() }
    val CoroutineScope = rememberCoroutineScope()

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Image(
            painter = painterResource(HomeScreenViewModel.imageBgLandscape),
            contentDescription = "levelBg",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(HomeScreenViewModel.imageBgPortrait),
            contentDescription = "levelBg",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = { ButtonController.navigateHome() },
            modifier = Modifier
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Menu") }

        PlayersList()
        Spacer(Modifier.size(10.dp))
        AddPlayer()

        IconButton(
            onClick = {
                if (true) {
                    PlayerViewModel.addPlayer()
                    CoroutineScope.launch {

                        snackState.showSnackbar("The Player is added")
                    }
                } else {
                    CoroutineScope.launch {
                        snackState.showSnackbar("The name of Player can not be empty")
                    }
                }
            },
            modifier = Modifier
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("Create player") }

        IconButton(
            onClick = {
//                PlayerViewModel.update()
            },
            modifier = Modifier
                .size(100.dp, 80.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillWidth
                )
        ) { Text("get player") }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(
            hostState = snackState,
        )
    }
}

@Composable
fun PlayersList() {
    val playersList = PlayerViewModel.playersList.collectAsState(initial = emptyList<Player>())

    Text("Players", fontSize = 20.sp, color = Color.White)
    LazyColumn(
        Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.3f)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.DarkGray)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)

    ) {

        items(items = playersList.value) {
            PlayerCard(it)
        }
    }
}

@Composable
fun PlayerCard(player: Player) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                PlayerViewModel.addActivePlayer(player)
            })
            .height(30.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "player",
                tint = if (player.isActive) {
                    Color.Green
                } else {
                    Color.Black
                }
            )
            Text(player.playerName)
        }
        Row {
            Text("record: ${player.achievements}")
            Text("score: ${player.score}")
        }
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            IconButton(onClick = { PlayerViewModel.delete(player) }) {
                Icon(Icons.Filled.Delete, contentDescription = "delete player")
            }
        }
    }
}

@Composable
fun AddPlayer() {

    Text("Enter player name", fontSize = 20.sp, color = Color.White)

    OutlinedTextField(
        value = PlayerViewModel.nameNewPlayer.value,
        onValueChange = {
            PlayerViewModel.nameNewPlayer.value = it
        },

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF816161),
            unfocusedTextColor = Color(0xFF868686),
            focusedContainerColor = Color(0xFFCEB3B3),
            focusedTextColor = Color(0xff222222),
            focusedIndicatorColor = Color.Green,
            unfocusedIndicatorColor = Color.DarkGray
        ),

        textStyle = TextStyle(
            fontSize = 20.sp,
        ),
    )
}


