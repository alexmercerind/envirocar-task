package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TrackProperties(
    @SerializedName("appVersion")
    val appVersion: String,
    @SerializedName("begin")
    val begin: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("length")
    val length: Double,
    @SerializedName("measurementProfile")
    val measurementProfile: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sensor")
    val sensor: Sensor,
    @SerializedName("status")
    val status: String,
    @SerializedName("touVersion")
    val touVersion: String,
    @SerializedName("user")
    val user: User
)