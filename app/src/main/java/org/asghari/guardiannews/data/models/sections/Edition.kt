package org.asghari.guardiannews.data.models.sections


import com.google.gson.annotations.SerializedName

data class Edition(
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String
)