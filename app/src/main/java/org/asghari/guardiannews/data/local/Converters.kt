package org.asghari.guardiannews.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.asghari.guardiannews.data.models.Tag
import org.asghari.guardiannews.data.models.Fields

class Converters {

    @TypeConverter
    fun listToJson(value: List<Tag>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Tag>::class.java).toList()

    @TypeConverter
    fun  ToJson(value: List<org.asghari.guardiannews.data.local.model.Tag>?) = Gson().toJson(value)

    @TypeConverter
    fun  ToList(value: String) = Gson().fromJson(value, Array<org.asghari.guardiannews.data.local.model.Tag>::class.java).toList()

    @TypeConverter
    fun fieldToJson(value:Fields?) = Gson().toJson(value)

    @TypeConverter
    fun jsonTofield(value: String) = Gson().fromJson(value, Fields::class.java)


    @TypeConverter
    fun tagToJson(value:Tag) = Gson().toJson(value)

    @TypeConverter
    fun jsonTotag(value: String) = Gson().fromJson(value, Tag::class.java)

    @TypeConverter
    fun  ToJson(value:org.asghari.guardiannews.data.local.model.Tag) = Gson().toJson(value)

    @TypeConverter
    fun  Totag(value: String) = Gson().fromJson(value, org.asghari.guardiannews.data.local.model.Tag::class.java)

}