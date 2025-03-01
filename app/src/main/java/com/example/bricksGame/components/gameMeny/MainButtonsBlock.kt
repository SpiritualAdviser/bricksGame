package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.localization.Localization
import com.example.bricksGame.ui.theme.buttonText

@Composable
fun MainButtonsBlock(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToMap() },
            modifier = Modifier
                .size(110.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) {
            Text(
                Localization.adventure.value,
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = {
                homeScreenViewModel.startFreeGame()
            },
            modifier = Modifier
                .size(110.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) {
            Text(
                Localization.survival.value,
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToPlayers() },
            modifier = Modifier
                .size(110.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) {
            Text(
                Localization.players.value,
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToRecords() },
            modifier = Modifier
                .size(110.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) {
            Text(
                Localization.records.value,
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = { homeScreenViewModel.buttonController.navigateToInfo() },
            modifier = Modifier
                .size(110.dp, 43.dp)
                .paint(
                    painter = painterResource(R.drawable.buttons_empty),
                    contentScale = ContentScale.Fit
                )
        ) {
            Text(
                Localization.info.value,
                fontSize = 13.sp,
                color = buttonText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
