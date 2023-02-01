package org.asghari.guardiannews.data.local.model


import com.google.gson.annotations.SerializedName

data class Sections(
    @SerializedName("response")
    val response: Response
)