package com.example.bricksGame.components.players.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bricksGame.config.Level
import kotlin.collections.mutableListOf

@Entity(tableName = "players")
class Player(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var playerName: String,
    var achievements: Int = 0,
    var isActive: Boolean = true,

    var activeLevelList: ActiveLevelList=ActiveLevelList(),
    var gameLevelList: GameLevelList
)

data class ActiveLevelList(
    var activeLevelList: List<LevelPlayer> = listOf(
        LevelPlayer(
            numberLevel = 1,
            numberLevelPasses = 0,
            isActive = true
        )
    )
)

data class GameLevelList(
    var gameLevelList: MutableList<Level> = mutableListOf()
)

class LevelPlayer(
    var numberLevel: Int,
    var numberLevelPasses: Int=0,
    var isActive: Boolean = false
)