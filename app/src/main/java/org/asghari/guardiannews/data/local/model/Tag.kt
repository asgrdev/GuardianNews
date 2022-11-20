package org.asghari.guardiannews.data.local.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Tags")
data class Tag(

    val apiUrl: String,
    val bio: String,
    val bylineImageUrl: String,
    val bylineLargeImageUrl: String,
    val description: String,
    val emailAddress: String,
    val firstName: String,
    @PrimaryKey
    val id: String,
    val lastName: String,
    val references: List<Any>,
    val sectionId: String,
    val sectionName: String,
    val twitterHandle: String,
    val type: String,
    val webTitle: String,
    val webUrl: String
)