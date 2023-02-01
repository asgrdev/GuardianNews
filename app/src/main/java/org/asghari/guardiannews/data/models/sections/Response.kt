package org.asghari.guardiannews.data.models.sections


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("userTier")
    val userTier: String
)