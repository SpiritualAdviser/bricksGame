//package com.example.bricksGame.components.players.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//
////@VisibleForTesting
////internal val MIGRATION_1_2 = object : Migration(1, 2) {
////
////    override fun migrate(database: SupportSQLiteDatabase) {
////        database.execSQL("ALTER TABLE Player ADD COLUMN activeLevelList INTEGER DEFAULT 0 NOT NULL");
////    }
////}
//
//
//@Database(
//    entities = [Player::class], version = 1
//)
//
//@TypeConverters(ConverterTypes::class)
//abstract class PlayerDatabase : RoomDatabase() {
//
//    abstract fun getDao(): PlayerDAO
//
//    companion object {
//        @Volatile
//        private var INSTANCE: PlayerDatabase? = null
//        fun getDatabase(context: Context): PlayerDatabase {
//
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context,
//                        PlayerDatabase::class.java,
//                        "player_database"
//
//                    )
////                        .addMigrations(MIGRATION_1_2)
////                        .allowMainThreadQueries()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}