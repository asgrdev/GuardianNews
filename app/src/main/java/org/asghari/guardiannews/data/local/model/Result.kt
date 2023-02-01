package org.asghari.guardiannews.data.local.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("activeSponsorships")
    val activeSponsorships: List<ActiveSponsorship>,
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("editions")
    val editions: List<Edition>,
    @SerializedName("id")
    val id: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String
)