package com.example.bricksGame.data.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "player")
class Player(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var playerName: String,
    @ColumnInfo(name = "score") var score: Int,
    @ColumnInfo(name = "achievements") var achievements: Int
)