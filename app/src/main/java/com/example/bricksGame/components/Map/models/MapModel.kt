package com.example.bricksGame.components.Map.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.players.models.PlayerViewModel
import com.example.bricksGame.ui.LevelsConfig

object MapModel : ViewModel() {

    val levelList = LevelsConfig.gameLevels.toMutableStateList()

    fun openLevelOnMap() {
        val playerLevels = PlayerViewModel.activePlayer.activeLevelList.activeLevelList

        playerLevels.forEachIndexed { index, level ->
            if (level.numberLevelPasses > 0) {
                levelList[index].isActive = true
                levelList[index].numberLevelPasses = level.numberLevelPasses
            } else {
                levelList[index].isActive = false
                levelList[index].numberLevelPasses = 0
            }
        }
    }

}