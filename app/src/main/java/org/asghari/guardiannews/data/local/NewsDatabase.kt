package org.asghari.guardiannews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.asghari.guardiannews.data.local.model.NewsItem

@Database(
    entities = [NewsItem::class]
    ,version =1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDao():NewsDao
}