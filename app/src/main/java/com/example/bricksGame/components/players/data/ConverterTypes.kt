//package com.example.bricksGame.components.players.data
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//
//class ConverterTypes {
//    @TypeConverter
//    fun fromActiveLevelListToJSON(activeLevelList: ActiveLevelList): String {
//        return Gson().toJson(activeLevelList)
//    }
//
//    @TypeConverter
//    fun fromJSONToActiveLevelList(json: String): ActiveLevelList {
//        return Gson().fromJson(json, ActiveLevelList::class.java)
//    }
//
//    @TypeConverter
//    fun fromGameLevelListToJSON(gameLevelList: GameLevelList): String {
//        return Gson().toJson(gameLevelList)
//    }
//
//    @TypeConverter
//    fun fromJSONToGameLevelList(json: String): GameLevelList {
//        return Gson().fromJson(json, GameLevelList::class.java)
//    }
//}