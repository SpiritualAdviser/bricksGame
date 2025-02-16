package com.example.bricksGame.components.players.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bricksGame.config.Level
import java.util.UUID
import kotlin.collections.mutableListOf

@Entity(tableName = "players")
class Player(
    @PrimaryKey() var id: String = UUID.randomUUID().toString(),
    var playerName: String,
    var achievements: Int = 0,
    var isActive: Boolean = true,

    var levels: OpenLevels = OpenLevels(),
)

data class OpenLevels(
    var gameLevelList: MutableList<Level> = mutableListOf(),
    var openLevelList: List<LevelPlayer> = listOf(LevelPlayer())
)


class LevelPlayer(
    var numberLevel: Int = 1,
    var numberLevelPasses: Int = 0,
    var isActive: Boolean = true
)