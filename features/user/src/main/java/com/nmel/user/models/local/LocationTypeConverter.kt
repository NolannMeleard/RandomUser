package com.nmel.user.models.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
class LocationTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToLocation(data: String?): Location? {
        if (data == null) {
            return null
        }

        val location: Type = object : TypeToken<Location?>() {}.type
        return gson.fromJson(data, location)
    }

    @TypeConverter
    fun locationToString(location: Location?): String? {
        return gson.toJson(location)
    }
}