package com.nmel.randomuser.injections

import com.nmel.user.network.interfaces.ApiUsersInterface
import com.nmel.user.network.repository.UsersRepository
import com.nmel.user.storage.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Singleton
    @Provides
    fun providesUsersProvider(api: ApiUsersInterface, usersDao: UsersDao): UsersRepository = UsersRepository(api, usersDao)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiUsersInterface = retrofit.create(ApiUsersInterface::class.java)
}