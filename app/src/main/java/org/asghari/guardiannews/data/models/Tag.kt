package org.asghari.guardiannews.data.models


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("bylineImageUrl")
    val bylineImageUrl: String,
    @SerializedName("bylineLargeImageUrl")
    val bylineLargeImageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("emailAddress")
    val emailAddress: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("references")
    val references: List<Any>,
    @SerializedName("sectionId")
    val sectionId: String,
    @SerializedName("sectionName")
    val sectionName: String,
    @SerializedName("twitterHandle")
    val twitterHandle: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String
)