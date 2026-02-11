package com.canaldothiago.mybooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.canaldothiago.mybooks.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO
    companion object {
        private lateinit var instance: BookDatabase
        const val DATABASE_NAME = "BookDB"

        fun getDatabase(context: Context): BookDatabase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }
}