package org.asghari.guardiannews.data.models


import com.google.gson.annotations.SerializedName
import org.asghari.guardiannews.data.local.model.NewsItem

data class Response(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("orderBy")
    val orderBy: String,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("startIndex")
    val startIndex: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("userTier")
    val userTier: String
)