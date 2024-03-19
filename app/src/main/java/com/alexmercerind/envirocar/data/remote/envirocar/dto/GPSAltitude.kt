package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GPSAltitude(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)