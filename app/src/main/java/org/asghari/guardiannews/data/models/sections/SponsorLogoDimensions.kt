package org.asghari.guardiannews.data.models.sections


import com.google.gson.annotations.SerializedName

data class SponsorLogoDimensions(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)