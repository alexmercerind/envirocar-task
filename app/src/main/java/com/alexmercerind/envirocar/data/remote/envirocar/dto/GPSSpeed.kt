package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GPSSpeed(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)