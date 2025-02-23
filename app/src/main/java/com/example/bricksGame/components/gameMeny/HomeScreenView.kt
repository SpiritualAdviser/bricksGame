package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.R
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel
import com.example.bricksGame.components.naviBar.ButtonSound
import com.example.bricksGame.helper.MainMenuBg
import com.example.bricksGame.ui.theme.gameVersionText

@Composable
fun RunHomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
//    FocusableSample()

    MainMenuBg()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End

    ) {
        LanguageManu()
        ButtonSound()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4F),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.logo),
                modifier = Modifier.size(260.dp, 130.dp),
                contentDescription = "logo"
            )
//          LogoSprite()
//            Logo()
        }

        MainButtonsBlock()
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd


    ) {
        Text("Game version ${homeScreenViewModel.gameConfig.GAME_VERSION}", color = gameVersionText)
    }

}

//@Composable
//fun FocusableSample() {
//    // initialize focus reference to be able to request focus programmatically
//    val focusRequester = remember { FocusRequester() }
//    // MutableInteractionSource to track changes of the component's interactions (like "focused")
//    val interactionSource = remember { MutableInteractionSource() }
//
//    // text below will change when we focus it via button click
//    val isFocused = interactionSource.collectIsFocusedAsState().value
//    val text =
//        if (isFocused) {
//            "Focused! tap anywhere to free the focus"
//        } else {
//            "Bring focus to me by tapping the button below!"
//        }
//    Column {
//        // this Text will change it's text parameter depending on the presence of a focus
//        Text(
//            text = text,
//            modifier =
//            Modifier
//                // add focusRequester modifier before the focusable (or even in the parent)
//                .focusRequester(focusRequester)
//                .focusable(interactionSource = interactionSource)
//        )
//        Button(onClick = { focusRequester.requestFocus() }) {
//            Text("Bring focus to the text above")
//        }
//    }
//}




