package org.asghari.guardiannews.data.local.model


import com.google.gson.annotations.SerializedName

data class ActiveSponsorship(
    @SerializedName("aboutLink")
    val aboutLink: String,
    @SerializedName("highContrastSponsorLogo")
    val highContrastSponsorLogo: String,
    @SerializedName("highContrastSponsorLogoDimensions")
    val highContrastSponsorLogoDimensions: HighContrastSponsorLogoDimensions,
    @SerializedName("sponsorLink")
    val sponsorLink: String,
    @SerializedName("sponsorLogo")
    val sponsorLogo: String,
    @SerializedName("sponsorLogoDimensions")
    val sponsorLogoDimensions: SponsorLogoDimensions,
    @SerializedName("sponsorName")
    val sponsorName: String,
    @SerializedName("sponsorshipType")
    val sponsorshipType: String,
    @SerializedName("validFrom")
    val validFrom: String,
    @SerializedName("validTo")
    val validTo: String
)