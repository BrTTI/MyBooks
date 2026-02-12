package com.canaldothiago.mybooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.canaldothiago.mybooks.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO
    companion object {
        private lateinit var instance: BookDatabase
        const val DATABASE_NAME = "BookDB.db"

        fun getDatabase(context: Context): BookDatabase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME)
                            .addMigrations(Migrations.MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

    private object Migrations {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                super.migrate(db)
                db.execSQL("DELETE TABLE Book")
            }
        }
    }
}