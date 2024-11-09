package com.example.bricksGame.components.levelGame

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bricksGame.AppNavigation
import com.example.bricksGame.R
import com.example.bricksGame.Routes
import com.example.bricksGame.ui.theme.colorsBricks


class LevelGameView() {
    private val navController: NavController = AppNavigation.getInstance().getNavController()

    private lateinit var viewModelFLB: FieldLevelBlockViewModel
    private lateinit var viewModelBricks: BricksViewModel

    @Composable
    fun Run() {
        viewModelFLB = viewModel()
        viewModelBricks = viewModel()
        MainBox()
    }

    @Composable
    private fun MainBox() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkGray)),
        ) {
            ScreenWithOrientation()
        }
    }

    @Composable
    private fun ScreenWithOrientation() {
        val orientation = LocalConfiguration.current.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LandscapeLayout()
        } else {
            PortraitLayout()
        }
    }

    @Composable
    private fun LandscapeLayout() {
        Row(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonHome()
            FieldBox()
            BricksBlock()
        }
    }

    @Composable
    private fun PortraitLayout() {
        Column(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            ButtonHome()
            FieldBox()
            BricksBlock()
        }
    }

    @Composable
    private fun ButtonHome() {
        Button(
            onClick = {
                navController.navigate(Routes.HomeScreen.route) {
                    popUpTo(Routes.HomeScreen.route)
                    launchSingleTop = true
                }
            }) {
            Text("Home")
        }
    }

    @Composable
    private fun BricksBlock() {

        LazyHorizontalGrid(
            modifier = Modifier
                .size(
                    (viewModelBricks.width + viewModelBricks.padding) * 3 + viewModelBricks.padding,
                    viewModelBricks.height + viewModelBricks.padding
                )
                .border(2.dp, Color.Black)
                .padding(viewModelBricks.padding),
            rows = GridCells.FixedSize(viewModelBricks.width),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.spacedBy(viewModelBricks.padding)
        ) {
            items(3) { index ->
                Box(
                    Modifier
                        .background(color = viewModelBricks.colorList[index])
                        .size(viewModelBricks.width, viewModelBricks.height)
                )
            }
        }
    }

    @Composable
    private fun FieldBox() {
        Box(
            Modifier
                .size(
                    viewModelFLB.width + viewModelFLB.padding,
                    viewModelFLB.height + viewModelFLB.padding
                )
                .background(Color.Gray),
            contentAlignment = Alignment.Center,

            ) {
            GridFieldBox()
        }
    }

    @Composable
    private fun GridFieldBox() {
        LazyVerticalGrid(
            columns = GridCells.FixedSize(viewModelBricks.width),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(viewModelFLB.width, viewModelFLB.height)

        ) {
            items(viewModelFLB.colorList.size) { index ->
                Box(
                    Modifier
                        .background(color = viewModelFLB.colorList[index])
                        .size(viewModelBricks.width, viewModelBricks.height)
                        .border(1.dp, Color.Black)
                )
            }
        }
    }
}


