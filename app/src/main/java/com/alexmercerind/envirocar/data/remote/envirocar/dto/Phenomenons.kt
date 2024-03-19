package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Phenomenons(
    @SerializedName("CO2 Emission (GPS-based)")
    val cO2EmissionGPSBased: CO2EmissionGPSBased,
    @SerializedName("Consumption (GPS-based)")
    val consumptionGPSBased: ConsumptionGPSBased,
    @SerializedName("GPS Accuracy")
    val gPSAccuracy: GPSAccuracy,
    @SerializedName("GPS Altitude")
    val gPSAltitude: GPSAltitude,
    @SerializedName("GPS Bearing")
    val gPSBearing: GPSBearing,
    @SerializedName("GPS Speed")
    val gPSSpeed: GPSSpeed,
    @SerializedName("Maximum GPS Acceleration")
    val maximumGPSAcceleration: MaximumGPSAcceleration,
    @SerializedName("Minimum GPS Acceleration")
    val minimumGPSAcceleration: MinimumGPSAcceleration
)