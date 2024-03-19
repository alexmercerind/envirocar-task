package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Sensor(
    @SerializedName("properties")
    val properties: SensorProperties,
    @SerializedName("type")
    val type: String
)