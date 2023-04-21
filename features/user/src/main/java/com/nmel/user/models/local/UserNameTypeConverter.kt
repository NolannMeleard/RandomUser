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
class UserNameTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToUserName(data: String?): UserName? {
        if (data == null) {
            return null
        }

        val userName: Type = object : TypeToken<UserName?>() {}.type
        return gson.fromJson(data, userName)
    }

    @TypeConverter
    fun userNameToString(userName: UserName?): String? {
        return gson.toJson(userName)
    }
}