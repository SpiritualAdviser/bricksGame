package com.example.bricksGame.ui.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ Player::class], version = 2, autoMigrations = [
        AutoMigration(from = 1, to = 2 )
    ],
    exportSchema = true
)

@TypeConverters(ConverterTypes::class)
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