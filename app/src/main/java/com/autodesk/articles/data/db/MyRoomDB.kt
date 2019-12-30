package com.autodesk.articles.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Article
import com.autodesk.articles.data.Source

@Database(entities = [Article::class, Source::class], exportSchema = false, version = 1)
@TypeConverters(TimeConverters::class)
abstract class MyRoomDB : RoomDatabase() {

    abstract fun getArticleDao(): ArticlesDao

    abstract fun getSourceDao(): SourcesDao

    companion object {

        @Volatile
        private var instance: MyRoomDB? = null

        fun getInstance(context: Context): MyRoomDB {
            return instance ?: synchronized(this) {
                instance ?: buildDb(context).also { instance = it }
            }
        }

        private fun buildDb(context: Context): MyRoomDB {
            return Room.databaseBuilder(context, MyRoomDB::class.java, Constants.DB_NAME)
                .build()
        }
    }
}