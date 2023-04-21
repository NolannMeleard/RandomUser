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
class PictureTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToPicture(data: String?): Picture? {
        if (data == null) {
            return null
        }

        val picture: Type = object : TypeToken<Picture?>() {}.type
        return gson.fromJson(data, picture)
    }

    @TypeConverter
    fun pictureToString(picture: Picture?): String? {
        return gson.toJson(picture)
    }
}