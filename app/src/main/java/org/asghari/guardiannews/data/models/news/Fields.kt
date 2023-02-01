package org.asghari.guardiannews.data.models.news


import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("body")
    val body: String,
    @SerializedName("bodyText")
    val bodyText: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("bylineHtml")
    val bylineHtml: String,
    @SerializedName("charCount")
    val charCount: String,
    @SerializedName("firstPublicationDate")
    val firstPublicationDate: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("isInappropriateForSponsorship")
    val isInappropriateForSponsorship: String,
    @SerializedName("isLive")
    val isLive: String,
    @SerializedName("isPremoderated")
    val isPremoderated: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("lastModified")
    val lastModified: String,
    @SerializedName("legallySensitive")
    val legallySensitive: String,
    @SerializedName("main")
    val main: String,
    @SerializedName("newspaperEditionDate")
    val newspaperEditionDate: String,
    @SerializedName("newspaperPageNumber")
    val newspaperPageNumber: String,
    @SerializedName("productionOffice")
    val productionOffice: String,
    @SerializedName("publication")
    val publication: String,
    @SerializedName("shortUrl")
    val shortUrl: String,
    @SerializedName("shouldHideAdverts")
    val shouldHideAdverts: String,
    @SerializedName("shouldHideReaderRevenue")
    val shouldHideReaderRevenue: String,
    @SerializedName("showAffiliateLinks")
    val showAffiliateLinks: String,
    @SerializedName("showInRelatedContent")
    val showInRelatedContent: String,
    @SerializedName("standfirst")
    val standfirst: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("trailText")
    val trailText: String,
    @SerializedName("wordcount")
    val wordcount: String
)