package com.example.bricksGame.components.players

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bricksGame.components.naviBar.ButtonNavigateHome
import com.example.bricksGame.components.naviBar.ButtonSound
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.ui.theme.backgroundDark
import com.example.bricksGame.ui.theme.errorLight
import com.example.bricksGame.ui.theme.onPrimaryLight
import com.example.bricksGame.ui.theme.onSurfaceVariantLight
import com.example.bricksGame.ui.theme.outlineDarkMediumContrast
import com.example.bricksGame.ui.theme.outlineLight
import com.example.bricksGame.ui.theme.outlineVariantLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerView() {

    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonNavigateHome()

        PlayersList()
        Spacer(Modifier.size(5.dp))
        AddPlayer()

        Button(
            onClick = {
                if (true) {
                    PlayerViewModel.addPlayer()
                    coroutineScope.launch {

                        snackState.showSnackbar("The Player is added")
                    }
                } else {
                    coroutineScope.launch {
                        snackState.showSnackbar("The name of Player can not be empty")
                    }
                }
            },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 80.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Create player") }

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

    Text("Players", fontSize = 20.sp, color = onPrimaryLight)
    LazyColumn(
        Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.4f)
            .clip(RoundedCornerShape(5.dp))
            .background(onSurfaceVariantLight)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
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
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(outlineDarkMediumContrast),
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
                    errorLight
                } else {
                    backgroundDark
                }
            )
            Text(player.playerName, color = backgroundDark)
        }
        Row {
            Text("achiev..: ${player.achievements}", color = backgroundDark)
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

    Text("Enter player name", fontSize = 20.sp, color = onPrimaryLight)

    OutlinedTextField(
        value = PlayerViewModel.nameNewPlayer.value,
        onValueChange = {
            PlayerViewModel.nameNewPlayer.value = it
        },
        label = { Text("name") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = outlineLight,
            focusedContainerColor = outlineVariantLight,
        ),

        textStyle = TextStyle(
            fontSize = 20.sp,
        ),
    )
}


