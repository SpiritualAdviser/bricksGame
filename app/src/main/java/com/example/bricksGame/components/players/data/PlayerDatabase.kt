package com.example.bricksGame.components.players.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bricksGame.MainActivity

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun getDao(): PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null
        fun getInstance(application: MainActivity): PlayerDatabase {

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