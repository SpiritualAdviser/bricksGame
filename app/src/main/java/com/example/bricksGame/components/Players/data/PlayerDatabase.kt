package com.example.bricksGame.components.Players.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null
        fun getInstance(application: Application): PlayerDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        PlayerDatabase::class.java,
                        "player_database"

                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}