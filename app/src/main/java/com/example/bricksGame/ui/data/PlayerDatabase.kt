package com.example.bricksGame.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bricksGame.MainActivity

@Database(entities = [Player::class], version = 1)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun getDao(): PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null
        fun getDatabase(context: Context): PlayerDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
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