package org.asghari.guardiannews.data.models.sections


import com.google.gson.annotations.SerializedName

data class Sections(
    @SerializedName("response")
    val response: Response
)