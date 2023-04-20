package com.nmel.randomuser.injections

import com.nmel.randomuser.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideRetrofitApi(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> /*Timber.d(message)*/ }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}