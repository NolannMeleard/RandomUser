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
class LoginTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToLogin(data: String?): Login? {
        if (data == null) {
            return null
        }

        val login: Type = object : TypeToken<Login?>() {}.type
        return gson.fromJson(data, login)
    }

    @TypeConverter
    fun loginToString(login: Login?): String? {
        return gson.toJson(login)
    }
}