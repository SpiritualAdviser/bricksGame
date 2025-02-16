package com.example.bricksGame.components.players.data

import androidx.room.TypeConverter
import com.example.bricksGame.internet.DataPlayerRecords
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

    @TypeConverter
    fun fromDataPlayerRecordsToJSON(gameLevelList: DataPlayerRecords): String {
        return Gson().toJson(gameLevelList)
    }

    @TypeConverter
    fun fromJSONToGameDataPlayerRecords(json: String): DataPlayerRecords {
        return Gson().fromJson(json, DataPlayerRecords::class.java)
    }
}