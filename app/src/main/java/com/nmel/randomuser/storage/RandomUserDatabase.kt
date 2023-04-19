package com.nmel.randomuser.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nmel.user.models.local.DobTypeConverter
import com.nmel.user.models.local.LocationTypeConverter
import com.nmel.user.models.local.LoginTypeConverter
import com.nmel.user.models.local.PictureTypeConverter
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserNameTypeConverter
import com.nmel.user.storage.UsersDao

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DobTypeConverter::class,
    LocationTypeConverter::class,
    LoginTypeConverter::class,
    PictureTypeConverter::class,
    UserNameTypeConverter::class
)
abstract class RandomUserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}