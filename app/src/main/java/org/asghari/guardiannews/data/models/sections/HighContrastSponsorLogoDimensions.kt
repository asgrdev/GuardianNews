package org.asghari.guardiannews.data.models.sections


import com.google.gson.annotations.SerializedName

data class HighContrastSponsorLogoDimensions(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)