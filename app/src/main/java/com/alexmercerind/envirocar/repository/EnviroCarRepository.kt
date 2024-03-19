package com.alexmercerind.envirocar.repository

import com.alexmercerind.envirocar.data.EnviroCarDataSource
import javax.inject.Inject

/**
 * EnviroCarRepository
 */
class EnviroCarRepository @Inject constructor(private val data: EnviroCarDataSource) {
    suspend fun track(
        username: String,
        trackId: String,
        user: String,
        token: String
    ) = data.track(username, trackId, user, token)
}
