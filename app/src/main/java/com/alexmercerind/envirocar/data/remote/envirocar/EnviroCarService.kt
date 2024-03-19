package com.alexmercerind.envirocar.data.remote.envirocar

import com.alexmercerind.envirocar.data.remote.envirocar.dto.TrackResponse

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * EnviroCarService
 */
interface EnviroCarService {
    @Headers("Accept: application/json")
    @GET("users/{username}/tracks/{trackId}")
    fun track(
        @Path("username") username: String,
        @Path("trackId") trackId: String,
        @Header("X-User") user: String,
        @Header("X-Token") token: String,
    ): TrackResponse

    companion object {
        const val BASE_URL = "https://envirocar.org/api/stable/"
    }
}
