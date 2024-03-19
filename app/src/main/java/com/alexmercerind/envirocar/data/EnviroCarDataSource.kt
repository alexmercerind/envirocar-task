package com.alexmercerind.envirocar.data

import com.alexmercerind.envirocar.data.remote.envirocar.EnviroCarService
import javax.inject.Inject

/**
 * EnviroCarDataSource
 */
class EnviroCarDataSource @Inject constructor(private val service: EnviroCarService) {
    fun track(
        username: String,
        trackId: String,
        user: String,
        token: String
    ) = service.track(username, trackId, user, token)
}
