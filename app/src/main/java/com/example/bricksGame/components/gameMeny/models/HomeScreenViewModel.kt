package com.example.bricksGame.components.gameMeny.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.bricksGame.R
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.helper.SoundController
import com.example.bricksGame.logic.StartLevelLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private var startLevelLogic: StartLevelLogic) :
    ViewModel() {
    @Inject
    lateinit var gameConfig: GameConfig

    @Inject
    lateinit var buttonController: ButtonController

    @Inject
    lateinit var soundController: SoundController

    val imageBgPortrait by mutableIntStateOf(R.drawable.bg_main_portrait)
    val imageBgLandscape by mutableIntStateOf(R.drawable.bg_main_landscape)

    fun startFreeGame() {
        startLevelLogic.onStartFreeGame()
    }
}


