//package com.example.bricksGame.components.players
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountBox
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.bricksGame.R
//import com.example.bricksGame.components.players.models.PlayerViewModel
//import com.example.bricksGame.ui.theme.primaryContainerDark
//
//@Composable
//fun PlayerScoreBlock() {
//    Column(
//        Modifier
////            .border(4.dp, Color.Magenta)
//    ) {
//        PlayerScore()
//        PlayerName()
//        PlayerAchievements()
//    }
//}
//
//@Composable
//fun PlayerScore() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Image(
//            painterResource(R.drawable.score_icon),
//            contentDescription = "scoreIcon",
//            Modifier.size(30.dp)
//        )
//        Spacer(Modifier.size(4.dp))
//        Text(
//            text = "Score: ${PlayerViewModel.playerScore.intValue}",
//            fontSize = 20.sp,
//            color = Color.White
//        )
//    }
//}
//
//@Composable
//fun PlayerName() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            Icons.Filled.AccountBox,
//            contentDescription = "Achievements",
//            modifier = Modifier.size(20.dp),
//            tint = primaryContainerDark
//        )
//        Spacer(Modifier.size(10.dp))
//        Text(
//            text = PlayerViewModel.nameActivePlayer.value,
//            fontSize = 16.sp,
//            color = Color.White
//        )
//    }
//}
//
//@Composable
//fun PlayerAchievements() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            Icons.Filled.AccountCircle,
//            contentDescription = "Achievements",
//            modifier = Modifier.size(20.dp),
//            tint = primaryContainerDark
//        )
//        Spacer(Modifier.size(10.dp))
//        Text(
//            text = "Achiv: ${PlayerViewModel.playerAchievements.intValue}",
//            fontSize = 16.sp,
//            color = Color.White
//        )
//    }
//}
