package com.example.bricksGame.components.gameMeny

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bricksGame.R
import com.example.bricksGame.localization.Dictionary
import com.example.bricksGame.localization.Localization
import com.example.bricksGame.ui.theme.gameVersionText

@Composable
fun LanguageManu() {

    var expanded by remember { mutableStateOf(false) }

    Box {

        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .padding(5.dp)
                .paint(
                    painter = painterResource(R.drawable.language_world),
                    contentScale = ContentScale.Inside,
                ),
        ) {}
        DropdownMenu(
            containerColor = gameVersionText,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { Localization.runTranslation(Dictionary().ru) },
                text = { Text(Localization.russian.value) }
            )
            DropdownMenuItem(
                onClick = { Localization.runTranslation(Dictionary().en) },
                text = { Text(Localization.english.value) }
            )
            DropdownMenuItem(
                onClick = { Localization.runTranslation(Dictionary().de) },
                text = { Text(Localization.german.value) }
            )
        }
    }
}