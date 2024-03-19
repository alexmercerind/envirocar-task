package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SensorProperties(
    @SerializedName("constructionYear")
    val constructionYear: Int,
    @SerializedName("engineDisplacement")
    val engineDisplacement: Int,
    @SerializedName("fuelType")
    val fuelType: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("vehicleType")
    val vehicleType: String,
    @SerializedName("weight")
    val weight: Int
)