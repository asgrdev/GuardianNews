package org.asghari.guardiannews.data.local

import androidx.room.*
import org.asghari.guardiannews.data.local.model.NewsItem
import org.asghari.guardiannews.data.models.Result

@Dao
abstract class  NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend  fun InsertNewsItem(newsItem: NewsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract  suspend fun InsertAll(newsItems:List<NewsItem>)

    @Delete
    abstract  suspend fun DeleteNewsItem(newsItem: NewsItem)

    @Query("SELECT * FROM NewsItems WHERE bodyText LIKE '%'||(:query)||'%' OR webTitle LIKE '%'||(:query)||'%'  LIMIT :page , :itemCount")
    abstract   suspend fun GetNewsItemsByQuery(query:String?,page:Int=0,itemCount:Int=10) :List<NewsItem>

    @Query("DELETE FROM NewsItems WHERE bodyText LIKE '%'||(:query)||'%' OR webTitle LIKE '%'||(:query)||'%' ")
    abstract   suspend fun DeleteNewsItemByQuery(query:String?)


}