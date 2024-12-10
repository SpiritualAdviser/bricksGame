package com.example.bricksGame.components.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bricksGame.components.NaviBar.ButtonSound
import com.example.bricksGame.components.options.models.OptionsViewModel
import com.example.bricksGame.ui.MainMenuBg
import com.example.bricksGame.ui.helper.ButtonController
import com.example.compose.backgroundDark
import com.example.compose.onPrimaryContainerLight
import com.example.compose.onPrimaryLight

@Composable
fun Options() {

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
        Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { ButtonController.navigateHome() },
            modifier = Modifier.shadow(10.dp, spotColor = Color.Black.copy(alpha = 1f))
//                .size(100.dp, 80.dp)
//                .paint(
//                    painter = painterResource(R.drawable.buttons_empty),
//                    contentScale = ContentScale.FillWidth
//                )
        ) { Text("Menu") }

        LazyColumn(
            Modifier
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(5.dp)),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Win lines" , color = onPrimaryLight)
            }
            item {
                FullRange()
            }
            item {
                ThreeInRow()
            }
            item {
                FourInRow()
            }
            item {
                Text("Field scheme" , color = onPrimaryLight)
            }
            item {
                FieldScheme3x4()
            }
            item {
                FieldScheme4x5()
            }
            item {
                FieldScheme5x6()
            }
            item {
                FieldScheme6x7()
            }
            item {
                FieldScheme7x8()
            }
            item {
                FieldScheme8x9()
            }
        }
    }
}

@Composable
fun ThreeInRow() {
    Card(
        onClick = { OptionsViewModel.activeCard("ThreeInRow") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.threeInRowCardColor.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Three in row", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FourInRow() {
    Card(
        onClick = { OptionsViewModel.activeCard("FourInRow") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fourInRowCardColor.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Four in row", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FullRange() {
    Card(
        onClick = { OptionsViewModel.activeCard("FullRange") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fullRangeCardColor.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Full range", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FieldScheme3x4() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme3x4") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme3x4.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 3x4", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FieldScheme4x5() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme4x5") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme4x5.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 4x5", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FieldScheme5x6() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme5x6") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme5x6.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 5x6", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FieldScheme6x7() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme6x7") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme6x7.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 6x7", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable
fun FieldScheme7x8() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme7x8") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme7x8.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 7x8", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}

@Composable

fun FieldScheme8x9() {
    Card(
        onClick = { OptionsViewModel.setFieldScheme("FieldScheme8x9") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(OptionsViewModel.fieldScheme8x9.value),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Field 8x9", textAlign = TextAlign.Center,  color = backgroundDark)
        }
    }
}