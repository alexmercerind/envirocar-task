package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Feature(
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("properties")
    val properties: Properties,
    @SerializedName("type")
    val type: String
)