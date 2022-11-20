package org.asghari.guardiannews.data.models


import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("response")
    val response: Response
)