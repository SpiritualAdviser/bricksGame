package com.example.bricksGame.components.players

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.naviBar.ButtonNaviBar
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.ui.theme.activePlayerBgCard
import com.example.bricksGame.ui.theme.activePlayerIcon
import com.example.bricksGame.ui.theme.buttonText
import com.example.bricksGame.ui.theme.focusedTextFieldBg
import com.example.bricksGame.ui.theme.onPrimaryLight
import com.example.bricksGame.ui.theme.playerBgCard
import com.example.bricksGame.ui.theme.playerTextDark
import com.example.bricksGame.ui.theme.playersBgBlock
import com.example.bricksGame.ui.theme.unfocusedTextFieldBg
import kotlinx.coroutines.launch

@Composable
fun PlayerView(playerViewModel: PlayerViewModel = hiltViewModel()) {

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
        ButtonNaviBar()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(8.dp))
        PlayersList()
        Spacer(Modifier.size(5.dp))
        AddPlayer()
        Spacer(Modifier.size(5.dp))
        IconButton(
            onClick = {
                playerViewModel.addPlayer()
                if (playerViewModel.nameNoEmpty) {
                    coroutineScope.launch {
                        snackState.showSnackbar("The Player is added")
                    }
                } else {
                    coroutineScope.launch {
                        snackState.showSnackbar("The name of Player can not be empty")
                    }
                }
            },
            modifier = Modifier
                .size(130.dp, 50.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Text(
                "Create player",
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }

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
fun PlayersList(playerViewModel: PlayerViewModel = hiltViewModel()) {
    val playersList = playerViewModel.allPlayers.collectAsState(initial = emptyList())

    Text("Players", fontSize = 22.sp, color = onPrimaryLight)
    Spacer(Modifier.size(4.dp))
    LazyColumn(
        Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.4f)
            .clip(RoundedCornerShape(5.dp))
            .background(playersBgBlock)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = playersList.value) {
            PlayerCard(it)
        }
    }
}

@Composable
fun PlayerCard(player: Player, playerViewModel: PlayerViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                playerViewModel.setActivePlayerOnGame(player)
            })
            .height(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                if (player.isActive) {
                    activePlayerBgCard
                } else {
                    playerBgCard
                }
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "player",
                tint = if (player.isActive) {
                    activePlayerIcon
                } else {
                    playerTextDark
                }
            )
            Text(
                player.playerName, fontSize = 14.sp, color = playerTextDark,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(.45F),
                overflow = TextOverflow.Ellipsis
            )
        }
        Row {
            Text("achiev: ${player.achievements}", fontSize = 14.sp, color = playerTextDark)
        }
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (player.isActive) {
                    Icon(
                        Icons.Filled.Check,
                        modifier = Modifier.offset(x = 10.dp),
                        tint = activePlayerIcon,
                        contentDescription = "activePlayer",
                    )
                }

                IconButton(onClick = { playerViewModel.delete(player) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "delete player")
                }
            }
        }
    }
}

@Composable
fun AddPlayer(playerViewModel: PlayerViewModel = hiltViewModel()) {

    Text("Enter player name", fontSize = 20.sp, color = onPrimaryLight)

    OutlinedTextField(
        value = playerViewModel.nameNewPlayer.value,
        onValueChange = {
            playerViewModel.setNameOnAddPlayer(it)
        },
        label = { Text("name") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = unfocusedTextFieldBg,
            focusedContainerColor = focusedTextFieldBg,
        ),
        textStyle = TextStyle(
            fontSize = 20.sp,
        ),
    )
}


