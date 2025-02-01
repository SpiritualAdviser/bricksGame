package com.example.bricksGame.components.gameMeny.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ButtonController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var gameConfig: GameConfig

    @Inject
    lateinit var buttonController: ButtonController

    val imageBgPortrait by mutableIntStateOf(R.drawable.bg_main_portrait)
    val imageBgLandscape by mutableIntStateOf(R.drawable.bg_main_landscape)
}


