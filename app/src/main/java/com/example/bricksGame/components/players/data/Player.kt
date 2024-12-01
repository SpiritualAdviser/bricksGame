package com.example.bricksGame.components.players.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
class Player(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var playerName: String,
    var score: Int = 0,
    var achievements: Int = 0
)