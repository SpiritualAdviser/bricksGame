package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.levelGame.data.Player

object PlayerViewModel : ViewModel() {
    private val player = Player()
    var playerName by mutableStateOf(player.playerName)
    var playerScore by mutableIntStateOf(player.score)
    var playerAchievements by mutableIntStateOf(player.achievements)

    fun addScore(score: Int) {
        playerScore += score
    }

    fun resetData() {
        playerScore = 0
    }
}