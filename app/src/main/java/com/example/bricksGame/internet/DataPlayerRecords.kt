package com.example.bricksGame.internet

import kotlinx.serialization.Serializable

@Serializable
data class DataPlayerRecords(
    var players: MutableList<PlayerAchievement>
)

@Serializable
data class PlayerAchievement(
    var id: Int,
    var name: String,
    var achievements: Int = 0,
    var levels: Int,
)

