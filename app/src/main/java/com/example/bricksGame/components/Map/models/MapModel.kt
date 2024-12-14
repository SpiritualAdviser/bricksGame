package com.example.bricksGame.components.Map.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.ui.LevelsConfig

object MapModel : ViewModel() {

    val levelList = LevelsConfig.gameLevels.toMutableStateList()

    fun openLevelOnMap() {
        levelList.forEach{
            it
        }
    }

}