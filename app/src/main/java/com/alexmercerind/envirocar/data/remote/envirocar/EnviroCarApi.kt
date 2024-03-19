package com.alexmercerind.envirocar.data.remote.envirocar

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

abstract class EnviroCarApi {
    companion object {
        val instance by lazy { retrofit.create(EnviroCarService::class.java) }

        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
            Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .build()
        }

        private const val BASE_URL = "https://envirocar.org/api/stable/"
    }
}
