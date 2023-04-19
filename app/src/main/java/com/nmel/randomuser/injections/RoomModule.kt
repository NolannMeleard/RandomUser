package com.nmel.randomuser.injections

import android.content.Context
import androidx.room.Room
import com.nmel.randomuser.storage.RandomUserDatabase
import com.nmel.user.storage.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): RandomUserDatabase {
        return Room.databaseBuilder(appContext, RandomUserDatabase::class.java, "randomuser-db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(database: RandomUserDatabase): UsersDao {
        return database.usersDao()
    }
}