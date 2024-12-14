package com.example.bricksGame.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
class Player(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var playerName: String,
    var achievements: Int = 0,
    var isActive: Boolean = false,

    var activeLevelList: ActiveLevelList
)

data class ActiveLevelList(
    var activeLevelList: List<LevelPlayer> = listOf(
        LevelPlayer(
            numberLevel = 1,
            numberLevelPasses = 0
        )
    )
)

class LevelPlayer(
    var numberLevel: Int,
    var numberLevelPasses: Int
)