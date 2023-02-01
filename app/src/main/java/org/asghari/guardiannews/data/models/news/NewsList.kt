package org.asghari.guardiannews.data.models.news


import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("response")
    val response: Response
)