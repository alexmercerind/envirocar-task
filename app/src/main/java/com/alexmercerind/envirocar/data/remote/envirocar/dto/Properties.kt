package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Properties(
    @SerializedName("id")
    val id: String,
    @SerializedName("phenomenons")
    val phenomenons: Phenomenons,
    @SerializedName("time")
    val time: String
)