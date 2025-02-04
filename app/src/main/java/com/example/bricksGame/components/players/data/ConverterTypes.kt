package com.example.bricksGame.components.players.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class ConverterTypes {
    @TypeConverter
    fun fromOpenLevelsToJSON(openLevels: OpenLevels): String {
        return Gson().toJson(openLevels)
    }

    @TypeConverter
    fun fromJSONTOpenLevels(json: String): OpenLevels {
        return Gson().fromJson(json, OpenLevels::class.java)
    }

//    @TypeConverter
//    fun fromGameLevelListToJSON(gameLevelList: GameLevelList): String {
//        return Gson().toJson(gameLevelList)
//    }
//
//    @TypeConverter
//    fun fromJSONToGameLevelList(json: String): GameLevelList {
//        return Gson().fromJson(json, GameLevelList::class.java)
//    }
}