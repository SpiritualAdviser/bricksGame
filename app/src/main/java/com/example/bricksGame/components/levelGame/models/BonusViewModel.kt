package com.example.bricksGame.components.levelGame.models

import androidx.lifecycle.ViewModel
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.gameData.LevelData
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor(
    val gameConfig: GameConfig,
    var soundController: SoundController,
    private var levelData: LevelData,
) : ViewModel() {

    private var _bonusList = levelData._bonusList

    val bonuses
        get() = _bonusList


}


