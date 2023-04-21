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
class DobTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToDob(data: String?): Dob? {
        if (data == null) {
            return null
        }

        val listType: Type = object : TypeToken<Dob?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun dobToString(dob: Dob?): String? {
        return gson.toJson(dob)
    }
}