package com.alexmercerind.envirocar.data.remote.envirocar.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class User(
    @SerializedName("acceptedPrivacyStatementVersion")
    val acceptedPrivacyStatementVersion: String,
    @SerializedName("acceptedTermsOfUseVersion")
    val acceptedTermsOfUseVersion: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String
)