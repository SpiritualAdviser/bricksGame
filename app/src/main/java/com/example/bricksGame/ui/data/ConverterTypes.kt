package com.example.bricksGame.ui.data

import androidx.room.TypeConverter
import com.google.gson.Gson


class ConverterTypes {
    @TypeConverter
    fun fromActiveLevelListToJSON(activeLevelList: ActiveLevelList): String {
        return Gson().toJson(activeLevelList)
    }
    @TypeConverter
    fun fromJSONToActiveLevelList(json: String): ActiveLevelList {
        return Gson().fromJson(json,ActiveLevelList::class.java)
    }
}