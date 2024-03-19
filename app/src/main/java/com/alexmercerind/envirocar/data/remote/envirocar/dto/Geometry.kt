package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Geometry(
    @SerializedName("coordinates")
    val coordinates: List<Double>,
    @SerializedName("type")
    val type: String
)