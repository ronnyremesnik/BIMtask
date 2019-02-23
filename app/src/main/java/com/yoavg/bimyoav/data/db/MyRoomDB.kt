package com.yoavg.bimyoav.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.Article

/**
 * Created by Yoav G on 23/02/2019.
 */

@Database(entities = [Article::class], exportSchema = false, version = 1)
abstract class MyRoomDB : RoomDatabase() {

    abstract fun getArticleDao(): ArticlesDao

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