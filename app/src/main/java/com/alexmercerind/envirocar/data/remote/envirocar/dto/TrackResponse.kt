package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TrackResponse(
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("properties")
    val properties: TrackProperties,
    @SerializedName("type")
    val type: String
)