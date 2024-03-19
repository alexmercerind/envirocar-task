package com.alexmercerind.envirocar.di

import com.alexmercerind.envirocar.data.EnviroCarDataSource
import com.alexmercerind.envirocar.data.remote.envirocar.EnviroCarService
import com.alexmercerind.envirocar.repository.EnviroCarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * ApplicationModule
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideEnviroCarService(): EnviroCarService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE })
            .build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(EnviroCarService.BASE_URL)
            .build()
            .create(EnviroCarService::class.java)
    }

    @Provides
    @Singleton
    fun provideEnviroCarDataSource(service: EnviroCarService) = EnviroCarDataSource(service)

    @Provides
    @Singleton
    fun provideEnviroCarRepository(data: EnviroCarDataSource) = EnviroCarRepository(data)

}